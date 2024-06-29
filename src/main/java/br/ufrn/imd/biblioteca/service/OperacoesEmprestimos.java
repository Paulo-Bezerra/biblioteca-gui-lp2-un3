package br.ufrn.imd.biblioteca.service;

import br.ufrn.imd.biblioteca.dao.BancoDAO;
import br.ufrn.imd.biblioteca.dto.EmprestimoDTO;
import br.ufrn.imd.biblioteca.model.*;
import br.ufrn.imd.biblioteca.repository.EmprestimoRepository;
import br.ufrn.imd.biblioteca.repository.LivroRepository;
import br.ufrn.imd.biblioteca.repository.UsuarioRepository;
import br.ufrn.imd.biblioteca.util.Tratamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OperacoesEmprestimos {
  // Recupera o repostório dos empréstimos contida no BancoDAO.
  private static EmprestimoRepository getER() {
    return BancoDAO.getInstance().getER();
  }

  private static UsuarioRepository getUR() {
    return BancoDAO.getInstance().getUR();
  }

  private static LivroRepository getLR() {
    return BancoDAO.getInstance().getLR();
  }

  // Cria, valida e cadastra um emprétimo. Retorna true se bem-sucedido.
  public static String cadastrarEmprestimo(String matricula, String isbn, LocalDate dataEmprestimo) {
    Livro l = getLR().getLivro(isbn);
    if (l == null) {
      return "Livro não encontrado!";
    }
    Usuario u;
    try {
      u = getUR().getUsuario(matricula);
      u.validar();
    } catch (Exception ignored) {
      return "Usuário não encotrado!";
    }

    if (possuiEmprestimosAtrados(matricula)) return u.getNome() + " possui emprestimos atradados!";

    int maxEmprestimos = maximoEmprestimos(u);
    int quantEmprestimos = getER().quantidadeEmprestimoPorMatricula(matricula);
    if (quantEmprestimos >= maxEmprestimos) return u.getNome() + " já possui o máximo de emprestimo (" + quantEmprestimos + "/" + maxEmprestimos + ")!";

    if (usuarioPossuiLivro(matricula, isbn)) return u.getNome() + " já possui um emprestimo do livro \"" + l.getTitulo() + "\"!";

    if (getER().quantidadeEmprestimoPorIsbn(isbn) == l.getEstoque()) return "O livro \"" + l.getTitulo() + "\" não está disponível!";

    LocalDate dataDevolucao = calcularDataDevolucao(dataEmprestimo, u);

    try {
      if (getER().cadastrarEmprestimo(new Emprestimo(matricula, isbn, dataEmprestimo, dataDevolucao))) {
        return "Emprestimo cadastrado com sucesso!\nPrazo para devolução: " + Tratamento.dataString(dataDevolucao) + ".";
      }
      return "Não foi possivel cadastrar o emprestimo!";
    } catch (Exception ignored) {
      return "Erro ao criar o emprestimo!";
    }
  }

 public static boolean usuarioPossuiLivro(String matricula, String isbn) {
    if (getER().quantidadeEmprestimoPorMatricula(matricula) == 0) return false;
    for (Emprestimo e : getER().getEmprestimosPorMatricula(matricula)) {
      if (e.getIsbn().equals(isbn)) return true;
    }
    return false;
  }

  public static LocalDate calcularDataDevolucao(LocalDate dataEmprestimo, Usuario usuario) {
    if (usuario == null) return null;
    if (usuario instanceof Estudante) return dataEmprestimo.plusDays(15);
    return dataEmprestimo.plusDays(30);
  }

  public static int maximoEmprestimos(Usuario usuario) {
    if (usuario == null) return 0;
    if (usuario instanceof Estudante) return 3;
    return 5;
  }

  public static boolean possuiEmprestimosAtrados(String matricula) {
    for (Emprestimo e : getER().getEmprestimosPorMatricula(matricula)) {
      if (e.getDataDevolucao().isBefore(LocalDate.now())) return true;
    }
    return false;
  }

  public static int quantidadeEmprestimosAtrados() {
    int quantAtrasos = 0;
    for (Emprestimo e : getER().getEmprestimos()) {
      if (e.getDataDevolucao().isBefore(LocalDate.now())) quantAtrasos++;
    }
    return quantAtrasos;
  }

  // Retorna a lista de todos os empréstimos ativos.
  public static List<EmprestimoDTO> listarEmprestimos() {
    ArrayList<EmprestimoDTO> emprestimos = new ArrayList<>();
    for (Emprestimo e : getER().getEmprestimos()) {
      emprestimos.add(new EmprestimoDTO(
        getUR().getUsuario(e.getMatricula()).getNome(),
        e.getMatricula(),
        getLR().getLivro(e.getIsbn()).getTitulo(),
        e.getIsbn(),
        Tratamento.dataString(e.getDataEmprestimo()),
        Tratamento.dataString(e.getDataDevolucao()))
      );
    }
    return emprestimos;
  }

  // Retorna a lista de todos os empréstimos ativos.
  public static List<EmprestimoDTO> listarEmprestimosAtrados() {
    ArrayList<EmprestimoDTO> emprestimos = new ArrayList<>();
    for (Emprestimo e : getER().getEmprestimos()) {
      if (e.getDataDevolucao().isBefore(LocalDate.now())) {
        emprestimos.add(new EmprestimoDTO(
          getUR().getUsuario(e.getMatricula()).getNome(),
          e.getMatricula(),
          getLR().getLivro(e.getIsbn()).getTitulo(),
          e.getIsbn(),
          Tratamento.dataString(e.getDataEmprestimo()),
          Tratamento.dataString(e.getDataDevolucao()))
        );
      }
    }
    return emprestimos;
  }

  public static boolean removerEmprestimo(String matricula, String isbn) {
    return isbn != null && getER().removerEmprestimo(matricula, isbn);
  }

  public static int quantidadeEmprestimos() {
    return getER().quantidadeEmprestimos();
  }

  public static Emprestimo getEmprestimo(String matricula, String isbn) {
    return getER().getEmprestimo(matricula, isbn);
  }
}
