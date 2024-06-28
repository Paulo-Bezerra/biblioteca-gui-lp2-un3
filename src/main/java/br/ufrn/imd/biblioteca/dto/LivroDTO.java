package br.ufrn.imd.biblioteca.dto;

public record LivroDTO(String titulo, String isbn) {
  @Override
  public String toString() {
    return titulo + ", ISBN: " + isbn;
  }
}