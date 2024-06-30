package br.ufrn.imd.biblioteca.model;

import br.ufrn.imd.biblioteca.util.Validacao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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
    return validarMatricula() && validarIsbn() && validarDatas();
  }

  // Métodos privados de validação dos atributos.
  private boolean validarMatricula() {
    return matricula != null && !matricula.isEmpty() && Validacao.strNumerica(matricula);
  }

  private boolean validarIsbn() {
    return isbn != null && !isbn.isEmpty() && Validacao.strNumerica(isbn);
  }

  private boolean validarDatas() {
    return dataEmprestimo != null && dataDevolucao != null && !dataEmprestimo.isBefore(dataDevolucao);
  }

  // Sobrescreve equals para comparar empréstimos pela matrícula e o ISBN.
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Emprestimo that = (Emprestimo) o;
    return Objects.equals(matricula, that.matricula) && Objects.equals(isbn, that.isbn);
  }

  // Sobrescreve hashCode para gerar um código único baseado na matrícula e ISBN.
  @Override
  public int hashCode() {
    return Objects.hash(matricula, isbn);
  }
}