package br.ufrn.imd.biblioteca.dto;

// Representa um DTO (Data Transfer Object) para usuário, contendo nome e matrícula.
public record UsuarioDTO(String nome, String matricula) {
  // Sobrescreve o método toString para formatar a exibição dos dados.
  @Override
  public String toString() {
    return "Nome: " + nome + ", Matricula: " + matricula;
  }
}