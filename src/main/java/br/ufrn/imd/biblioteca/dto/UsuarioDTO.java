package br.ufrn.imd.biblioteca.dto;

public record UsuarioDTO(String nome, String matricula) {
  @Override
  public String toString() {
    return "Nome: " + nome + ", Matricula: " + matricula;
  }
}
