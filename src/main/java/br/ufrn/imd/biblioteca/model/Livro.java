package br.ufrn.imd.biblioteca.model;

import br.ufrn.imd.biblioteca.util.Validacao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

// Representa um livro na biblioteca
public class Livro implements Serializable, IValidarClasse {
  private String titulo;
  private String autor;
  private String assunto;
  private String isbn;
  private int ano;
  private int estoque;

  // Construtor padrão do livro.
  public Livro(String titulo, String autor, String assunto, String isbn, int ano, int estoque) {
    this.titulo = titulo;
    this.autor = autor;
    this.assunto = assunto;
    this.isbn = isbn;
    this.ano = ano;
    this.estoque = estoque;
  }

  // Construtor de cópia de dados de outro livro.
  public Livro(Livro livro) {
    this.titulo = livro.titulo;
    this.autor = livro.autor;
    this.assunto = livro.assunto;
    this.isbn = livro.isbn;
    this.ano = livro.ano;
    this.estoque = livro.estoque;
  }

  // Getters e setters.
  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public String getAssunto() {
    return assunto;
  }

  public void setAssunto(String assunto) {
    this.assunto = assunto;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public int getEstoque() {
    return estoque;
  }

  public void setEstoque(int estoque) {
    this.estoque = estoque;
  }

  // Validação do Livro.
  @Override
  public boolean validar() {
    return validarTitulo()
      && validarAutor()
      && validarAssunto()
      && validarIsbn()
      && validarAno();
  }

  // Métodos privados de validação dos atributos.
  private boolean validarTitulo() {
    return titulo != null && !titulo.isEmpty();
  }

  private boolean validarAutor() {
    return autor != null && !autor.isEmpty();
  }

  private boolean validarAssunto() {
    return assunto != null && !assunto.isEmpty();
  }

  private boolean validarIsbn() {
    return isbn != null && !isbn.isEmpty() && Validacao.strNumerica(isbn);
  }

  private boolean validarAno() {
    return ano <= LocalDate.now().getYear();
  }

  // Sobrescreve equals e hashCode para comparar os livros pelo ISBN.
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Livro livro = (Livro) o;
    return Objects.equals(isbn, livro.isbn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isbn);
  }
}