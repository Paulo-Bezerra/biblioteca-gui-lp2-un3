package br.ufrn.imd.biblioteca.repository;

import br.ufrn.imd.biblioteca.model.Emprestimo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmprestimoRepository implements Serializable {
  private final HashMap<String, Emprestimo> mat_isbn_ER;
  private final HashMap<String, ArrayList<Emprestimo>> matriculas_ER;
  private final HashMap<String, ArrayList<Emprestimo>> isbns_ER;

  public EmprestimoRepository() {
    this.mat_isbn_ER = new HashMap<>();
    this.matriculas_ER = new HashMap<>();
    this.isbns_ER = new HashMap<>();
  }

  public EmprestimoRepository(EmprestimoRepository emprestimoRepository) {
    this.mat_isbn_ER = new HashMap<>(emprestimoRepository.mat_isbn_ER);
    this.matriculas_ER = new HashMap<>(emprestimoRepository.matriculas_ER);
    this.isbns_ER = new HashMap<>(emprestimoRepository.isbns_ER);
  }

  public boolean adicionaEmprestimo(Emprestimo emprestimo) {
    if (existeEmprestimo(emprestimo.getMatricula(), emprestimo.getIsbn())) {
      return false;
    }

    mat_isbn_ER.put(emprestimo.getMatricula() + "-" + emprestimo.getIsbn(), emprestimo);

    if (matriculas_ER.containsKey(emprestimo.getMatricula())) {
      matriculas_ER.get(emprestimo.getMatricula()).add(emprestimo);
    } else {
      ArrayList<Emprestimo> emprestimosMatricula = new ArrayList<>();
      emprestimosMatricula.add(emprestimo);
      matriculas_ER.put(emprestimo.getMatricula(), emprestimosMatricula);
    }
    if (isbns_ER.containsKey(emprestimo.getIsbn())) {
      isbns_ER.get(emprestimo.getIsbn()).add(emprestimo);
    } else {
      ArrayList<Emprestimo> emprestimosIsbn = new ArrayList<>();
      emprestimosIsbn.add(emprestimo);
      isbns_ER.put(emprestimo.getIsbn(), emprestimosIsbn);
    }
    return true;
  }

  public boolean removerEmprestimo(String matricula, String isbn) {
    if (!existeEmprestimo(matricula, isbn)) {
      return false;
    }

    Emprestimo emprestimo = new Emprestimo(getEmprestimo(matricula, isbn));
    mat_isbn_ER.remove(matricula + "-" + isbn);

    if (matriculas_ER.containsKey(matricula)) {
      matriculas_ER.get(matricula).remove(emprestimo);
      if (getNumEmprestimoPorMatricula(matricula) == 0) {
        matriculas_ER.remove(matricula);
      }
    }

    if (isbns_ER.containsKey(isbn)) {
      isbns_ER.get(isbn).remove(emprestimo);
      if (getNumEmprestimoPorIsbn(isbn) == 0) {
        isbns_ER.remove(isbn);
      }
    }

    return true;
  }

  public List<Emprestimo> getEmprestimos() {
    return new ArrayList<>(mat_isbn_ER.values());
  }

  public List<Emprestimo> getEmprestimosPorMatricula(String matricula) {
    return new ArrayList<>(matriculas_ER.get(matricula));
  }

  public List<Emprestimo> getEmprestimosIsbn(String isbn) {
    return new ArrayList<>(isbns_ER.get(isbn));
  }

  public Emprestimo getEmprestimo(String matricula, String isbn) {
    return mat_isbn_ER.get(matricula + "-" + isbn);
  }

  public boolean existeEmprestimo(String matricula, String isbn) {
    return mat_isbn_ER.containsKey(matricula + "-" + isbn);
  }

  public int getNumEmprestimoPorMatricula(String matricula) {
    try {
      return matriculas_ER.get(matricula).size();
    } catch (Exception e) {
      return 0;
    }
  }

  public int getNumEmprestimoPorIsbn(String isbn) {
    try {
      return isbns_ER.get(isbn).size();
    } catch (Exception e) {
      return 0;
    }
  }
}
