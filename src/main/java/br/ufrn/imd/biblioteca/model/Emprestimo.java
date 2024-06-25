package br.ufrn.imd.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDate;

// Representa um empréstimo de livro na biblioteca.
public class Emprestimo implements Serializable, IValidarClasse {
  private String matricula;
  private String isbn;
  private LocalDate dataEmprestimo;
  private LocalDate dataDevolucao;

  // Construtor padrão do empréstimo.
  public Emprestimo(String matricula, String isbn, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
    this.matricula = matricula;
    this.isbn = isbn;
    this.dataEmprestimo = dataEmprestimo;
    this.dataDevolucao = dataDevolucao;
  }

  // Construtor de cópia de dados de outro empréstimo.
  public Emprestimo(Emprestimo emprestimo) {
    this.matricula = emprestimo.matricula;
    this.isbn = emprestimo.isbn;
    this.dataEmprestimo = emprestimo.dataEmprestimo;
    this.dataDevolucao = emprestimo.dataDevolucao;
  }

  // Getters e setters.
  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public LocalDate getDataEmprestimo() {
    return dataEmprestimo;
  }

  public void setDataEmprestimo(LocalDate dataEmprestimo) {
    this.dataEmprestimo = dataEmprestimo;
  }

  public LocalDate getDataDevolucao() {
    return dataDevolucao;
  }

  public void setDataDevolucao(LocalDate dataDevolucao) {
    this.dataDevolucao = dataDevolucao;
  }

  // Validação do empréstimo.
  @Override
  public boolean validar() {
    return false;
  }

  // Sobrescreve equals para comparar empréstimos pela matrícula e o ISBN.
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Emprestimo other = (Emprestimo) obj;
    if (matricula == null) {
      if (other.matricula != null)
        return false;
    } else if (!matricula.equals(other.matricula))
      return false;
    if (isbn == null) {
      if (other.isbn != null)
        return false;
    } else if (!isbn.equals(other.isbn))
      return false;
    return true;
  }

  // Sobrescreve hashCode para gerar um código único baseado na matrícula e ISBN.
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
    result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
    return result;
  }
}