package br.ufrn.imd.biblioteca.model;

import eu.hansolo.tilesfx.tools.PrettyListView;

import java.time.LocalDate;

public class Estudante extends Usuario {
  private String curso;

  public Estudante(String nome, String cpf, String matricula, LocalDate dataNascimento, String curso) {
    super(nome, cpf, matricula, dataNascimento);
    this.curso = curso;
  }

  public Estudante(Estudante estudante) {
    super(estudante);
    this.curso = estudante.curso;
  }

  public String getCurso() {
    return curso;
  }

  public void setCurso(String curso) {
    this.curso = curso;
  }

  @Override
  public boolean validar() {
    return validarUsuario() && validarCurso();
  }

  private boolean validarCurso() {
    return curso != null && !curso.isEmpty();
  }
}
