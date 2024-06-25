package br.ufrn.imd.biblioteca.model;

import java.time.LocalDate;

// Representa um professor, que é um tipo de usuário da biblioteca.
public class Professor extends Usuario {
  private String departamento;

  // Construtor padrão do professor.
  public Professor(String nome, String cpf, String matricula, LocalDate dataNascimento, String departamento) {
    super(nome, cpf, matricula, dataNascimento);
    this.departamento = departamento;
  }

  // Construtor de cópia de dados de outro professor.
  public Professor(Professor professor) {
    super(professor);
    this.departamento = professor.departamento;
  }

  // Get e set de departamento.
  public String getDepartamento() {
    return departamento;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }

  // Validação do professor.
  @Override
  public boolean validar() {
    return validarUsuario() && validarDepartamento();
  }

  // Métodos privado para validar o departamento.
  private boolean validarDepartamento() {
    return departamento != null && !departamento.isEmpty();
  }
}