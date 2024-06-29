package br.ufrn.imd.biblioteca.dto;

public record EmprestimoDTO(String matricula, String isbn, String dataEmprestimo, String dataDevolucao) {
  @Override
  public String toString() {
    return matricula + ", ISBN: " + isbn + ", Data do empréstimo: " + dataEmprestimo + ", Data da devolução: " + dataDevolucao;
  }
}
