package br.ufrn.imd.biblioteca.repository;

import br.ufrn.imd.biblioteca.model.Emprestimo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class EmprestimoRepository implements Serializable {
  // Maps para armazenar os empréstimos...
  private final HashMap<Integer, Emprestimo> ER; // chave é o hashcode de Empréstimo (matrícula, ISBN).
  private final HashMap<String, ArrayList<Emprestimo>> mat_ER; // chave matrícula.
  private final HashMap<String, ArrayList<Emprestimo>> isbn_ER; // chave ISBN.

  // Construtor padrão para inicializar os maps.
  public EmprestimoRepository() {
    this.ER = new HashMap<>();
    this.mat_ER = new HashMap<>();
    this.isbn_ER = new HashMap<>();
  }

  // Construtor de cópia de dados de outro EmprestimoRepository
  public EmprestimoRepository(EmprestimoRepository emprestimoRepository) {
    this.ER = new HashMap<>(emprestimoRepository.ER);
    this.mat_ER = new HashMap<>(emprestimoRepository.mat_ER);
    this.isbn_ER = new HashMap<>(emprestimoRepository.isbn_ER);
  }

  // Cadastra um empréstimo se o par (matrícula, ISBN) ainda não estiver registrado.
  // Retorna true se bem-sucedido.
  public boolean cadastrarEmprestimo(Emprestimo emprestimo) {
    // Verifica se já existe um empréstimo com a mesma matrícula e ISBN.
    if (existeEmprestimo(emprestimo)) {
      return false;
    }

    // Adiciona o empréstimo ao ER.
    ER.put(emprestimo.hashCode(), emprestimo);

    // Adiciona em mat_ER um novo ArrayList, caso não houver empréstimos para a matrícula.
    if (!mat_ER.containsKey(emprestimo.getMatricula())) {
      mat_ER.put(emprestimo.getMatricula(), new ArrayList<>());
    }
    // Adiciona o empréstimo em mat_ER.
    mat_ER.get(emprestimo.getMatricula()).add(emprestimo);

    // Adiciona em isbn_ER um novo ArrayList, caso não houver empréstimos do livro.
    if (!isbn_ER.containsKey(emprestimo.getIsbn())) {
      isbn_ER.put(emprestimo.getIsbn(), new ArrayList<>());
    }
    // Adiciona o empréstimo em isbn_ER.
    isbn_ER.get(emprestimo.getIsbn()).add(emprestimo);

    return true;
  }

  // Remove um empréstimo pelo par matricula e ISBN. Retorna true se bem-sucedido.
  public boolean removerEmprestimo(String matricula, String isbn) {
    // Verifica se o empréstimo existe antes de tentar remover.
    if (!existeEmprestimo(matricula, isbn)) {
      return false;
    }

    // Remove de ER. Recupera o empréstimo para excluir nos outros Maps.
    Emprestimo emprestimo = ER.remove(Objects.hash(matricula, isbn));

    // Remove de mat_ER.
    mat_ER.get(matricula).remove(emprestimo);
    // Remove as chaves sem empréstimos.
    if (quantidadeEmprestimoPorMatricula(matricula) == 0) {
      mat_ER.remove(matricula);
    }

    // Remove de isbn_ER.
    isbn_ER.get(isbn).remove(emprestimo);
    // Remove as chaves sem empréstimos.
    if (quantidadeEmprestimoPorIsbn(isbn) == 0) {
      isbn_ER.remove(isbn);
    }

    return true;
  }

  // Atualiza o empréstimo associado à matrícula e ao ISBN do empréstimo fornecido.
  public boolean atualizarEmprestimo(Emprestimo emprestimo) {
    if (!existeEmprestimo(emprestimo)) {
      return false;
    }

    // Atualiza o empréstimo em ER.
    ER.put(emprestimo.hashCode(), emprestimo);

    // Adiciona o empréstimo ao mat_ER.
    mat_ER.get(emprestimo.getMatricula()).remove(emprestimo);
    mat_ER.get(emprestimo.getMatricula()).add(emprestimo);

    // Adiciona o empréstimo ao isbn_ER.
    isbn_ER.get(emprestimo.getIsbn()).remove(emprestimo);
    isbn_ER.get(emprestimo.getIsbn()).add(emprestimo);

    return true;
  }

  // Retorna a lista de todos os emprétimos cadastrados.
  public List<Emprestimo> getEmprestimos() {
    return new ArrayList<>(ER.values());
  }

  // Retorna uma lista com todos os empréstimos associados à matrícula fornecida.
  public List<Emprestimo> getEmprestimosPorMatricula(String matricula) {
    return new ArrayList<>(mat_ER.get(matricula));
  }

  // Retorna uma lista com todos os empréstimos associados ao ISBN fornecido.
  public List<Emprestimo> getEmprestimosIsbn(String isbn) {
    return new ArrayList<>(isbn_ER.get(isbn));
  }

  // Retorna o empréstimo associado ao par matrícula e ISBN fornecido.
  public Emprestimo getEmprestimo(String matricula, String isbn) {
    return ER.get(Objects.hash(matricula, isbn));
  }

  // Verifica se existe um emprestimo com hashcode do empréstimo fornecido.
  public boolean existeEmprestimo(Emprestimo emprestimo) {
    return ER.containsKey(emprestimo.hashCode());
  }

  // Verifica se existe um empréstimo com o par matrícula e ISBN fornecido.
  public boolean existeEmprestimo(String matricula, String isbn) {
    return ER.containsKey(Objects.hash(matricula, isbn));
  }



  // Retorna o número de empréstimos cadastrados.
  public int quantidadeEmprestimos() {
    return ER.size();
  }

  // Retorna o número de empréstimos associados à matrícula fornecida.
  public int quantidadeEmprestimoPorMatricula(String matricula) {
    try {
      return mat_ER.get(matricula).size();
    } catch (Exception e) {
      return 0;
    }
  }

  // Retorna o número de empréstimos associados ao ISBN fornecido.
  public int quantidadeEmprestimoPorIsbn(String isbn) {
    try {
      return isbn_ER.get(isbn).size();
    } catch (Exception e) {
      return 0;
    }
  }
}