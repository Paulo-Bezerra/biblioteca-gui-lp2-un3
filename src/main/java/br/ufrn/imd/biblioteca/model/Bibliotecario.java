package br.ufrn.imd.biblioteca.model;

import java.time.LocalDate;

public class Bibliotecario extends Usuario {
  private String login;
  private String senha;

  public Bibliotecario(String nome, String cpf, String matricula, LocalDate dataNascimento, String login,
      String senha) {
    super(nome, cpf, matricula, dataNascimento);
    this.login = login;
    this.senha = senha;
  }

  public Bibliotecario(Bibliotecario bibliotecario) {
    super(bibliotecario);
    this.login = bibliotecario.login;
    this.senha = bibliotecario.senha;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }
}
