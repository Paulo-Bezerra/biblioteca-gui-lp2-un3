package br.ufrn.imd.biblioteca.model;

import java.time.LocalDate;

// Representa um estudante, que é um tipo de usuário da biblioteca.
public class Estudante extends Usuario {
  private String curso;

  // Construtor padrão do estudante.
  public Estudante(String nome, String cpf, String matricula, LocalDate dataNascimento, String curso) {
    super(nome, cpf, matricula, dataNascimento);
    this.curso = curso;
  }

  // Construtor de cópia de dados de outro estudante.
  public Estudante(Estudante estudante) {
    super(estudante);
    this.curso = estudante.curso;
  }

  // Get e set de curso.
  public String getCurso() {
    return curso;
  }

  public void setCurso(String curso) {
    this.curso = curso;
  }

  // Validação do estudante.
  @Override
  public boolean validar() {
    return validarUsuario() && validarCurso();
  }

  // Métodos privado para validar o curso.
  private boolean validarCurso() {
    return curso != null && !curso.isEmpty();
  }
}