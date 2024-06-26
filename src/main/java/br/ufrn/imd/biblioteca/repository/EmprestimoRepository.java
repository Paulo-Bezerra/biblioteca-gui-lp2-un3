package br.ufrn.imd.biblioteca.repository;

import br.ufrn.imd.biblioteca.model.Emprestimo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmprestimoRepository implements Serializable {
  // Maps para armazenar os empréstimos...
  private final HashMap<String, Emprestimo> mat_isbn_ER; // chave formada matrícula-ISBN.
  private final HashMap<String, ArrayList<Emprestimo>> mat_ER; // chave matrícula.
  private final HashMap<String, ArrayList<Emprestimo>> isbn_ER; // chave ISBN.

  // Construtor padrão para inicializar os maps.
  public EmprestimoRepository() {
    this.mat_isbn_ER = new HashMap<>();
    this.mat_ER = new HashMap<>();
    this.isbn_ER = new HashMap<>();
  }

  // Construtor de cópia de dados de outro EmprestimoRepository
  public EmprestimoRepository(EmprestimoRepository emprestimoRepository) {
    this.mat_isbn_ER = new HashMap<>(emprestimoRepository.mat_isbn_ER);
    this.mat_ER = new HashMap<>(emprestimoRepository.mat_ER);
    this.isbn_ER = new HashMap<>(emprestimoRepository.isbn_ER);
  }

  // Cadastra um empréstimo se o par (matrícula, ISBN) ainda não estiver registrado.
  // Retorna true se bem-sucedido.
  public boolean cadastrarEmprestimo(Emprestimo emprestimo) {
    // Verifica se já existe um empréstimo com a mesma matrícula e ISBN.
    if (existeEmprestimo(emprestimo.getMatricula(), emprestimo.getIsbn())) {
      return false;
    }

    // Adiciona o empréstimo ao mat_isbn_ER
    mat_isbn_ER.put(emprestimo.getMatricula() + "-" + emprestimo.getIsbn(), emprestimo);

    // Adiciona o empréstimo ao mat_ER
    if (mat_ER.containsKey(emprestimo.getMatricula())) {
      mat_ER.get(emprestimo.getMatricula()).add(emprestimo);
    } else { // Cria um novo ArrayList, caso o usuário não tenha realizado empréstimos.
      ArrayList<Emprestimo> emprestimosMatricula = new ArrayList<>();
      emprestimosMatricula.add(emprestimo);
      mat_ER.put(emprestimo.getMatricula(), emprestimosMatricula);
    }

    // Adiciona o empréstimo ao isbn_ER
    if (isbn_ER.containsKey(emprestimo.getIsbn())) {
      isbn_ER.get(emprestimo.getIsbn()).add(emprestimo);
    } else { // Cria um novo ArrayList, caso o usuário não tenha realizado empréstimos.
      ArrayList<Emprestimo> emprestimosIsbn = new ArrayList<>();
      emprestimosIsbn.add(emprestimo);
      isbn_ER.put(emprestimo.getIsbn(), emprestimosIsbn);
    }
    return true;
  }

  // Remove um livro pelo par matricula e ISBN. Retorna true se bem-sucedido.
  public boolean removerEmprestimo(String matricula, String isbn) {
    // Verifica se o empréstimo existe antes de tentar remover
    if (!existeEmprestimo(matricula, isbn)) {
      return false;
    }

    // Cria uma cópia do empréstimo para remover nos outros Maps.
    Emprestimo emprestimo = new Emprestimo(getEmprestimo(matricula, isbn));

    // Remove de mat_isbn_ER.
    mat_isbn_ER.remove(matricula + "-" + isbn);

    // Remove de mat_ER.
    if (mat_ER.containsKey(matricula)) {
      mat_ER.get(matricula).remove(emprestimo);
      // Remove as chaves sem empréstimos.
      if (quantidadeEmprestimoPorMatricula(matricula) == 0) {
        mat_ER.remove(matricula);
      }
    }

    // Remove de isbn_ER.
    if (isbn_ER.containsKey(isbn)) {
      isbn_ER.get(isbn).remove(emprestimo);
      // Remove as chaves sem empréstimos.
      if (quantidadeEmprestimoPorIsbn(isbn) == 0) {
        isbn_ER.remove(isbn);
      }
    }

    return true;
  }

  // Retorna a lista de todos os emprétimos cadastrados.
  public List<Emprestimo> getEmprestimos() {
    return new ArrayList<>(mat_isbn_ER.values());
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
    return mat_isbn_ER.get(matricula + "-" + isbn);
  }

  // Verifica se existe um livro com o par matrícula e ISBN fornecido.
  public boolean existeEmprestimo(String matricula, String isbn) {
    return mat_isbn_ER.containsKey(matricula + "-" + isbn);
  }

  // Retorna o número de empréstimos cadastrados.
  public int quantidadeEmprestimos() {
    return mat_isbn_ER.size();
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