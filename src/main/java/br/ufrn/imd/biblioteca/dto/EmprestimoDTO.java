package br.ufrn.imd.biblioteca.dto;

public record EmprestimoDTO(String nome, String matricula, String titulo, String isbn, String dataEmprestimo, String dataDevolucao) {
  @Override
  public String toString() {
    return nome + ": " + titulo + ". Emprestimo: " + dataEmprestimo + ", prazo: " + dataDevolucao;
  }
}
