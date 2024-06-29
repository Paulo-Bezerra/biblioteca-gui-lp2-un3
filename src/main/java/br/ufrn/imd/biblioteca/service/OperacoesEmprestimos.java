package br.ufrn.imd.biblioteca.service;

import br.ufrn.imd.biblioteca.dao.BancoDAO;
import br.ufrn.imd.biblioteca.dto.EmprestimoDTO;
import br.ufrn.imd.biblioteca.dto.LivroDTO;
import br.ufrn.imd.biblioteca.model.Emprestimo;
import br.ufrn.imd.biblioteca.model.Livro;
import br.ufrn.imd.biblioteca.repository.EmprestimoRepository;
import br.ufrn.imd.biblioteca.util.Validacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OperacoesEmprestimos {
  // Recupera o repostório dos empréstimos contida no BancoDAO.
  private static EmprestimoRepository getER() {
    return BancoDAO.getInstance().getER();
  }

  // Cria, valida e cadastra um emprétimo. Retorna true se bem-sucedido.
  public static boolean cadastrarEmprestimo(String matricula, String isbn, LocalDate dataEmprestimo) {
    // Calculo da data de devolucao
    Emprestimo emprestimo = new Emprestimo(matricula, isbn, dataEmprestimo, dataDevolucao);
    return emprestimo.validar() && getER().cadastrarEmprestimo(emprestimo);

  }

  // Retorna a lista de todos os empréstimos ativos.
  public static List<EmprestimoDTO> listarEmprestimos() {
    ArrayList<EmprestimoDTO> emprestimos = new ArrayList<>();
    for (Emprestimo e : getER().getEmprestimos()) {
      emprestimos.add(new EmprestimoDTO(e.getMatricula(), e.getIsbn(), e.getDataEmprestimo(), e.getDataDevolucao()));
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
