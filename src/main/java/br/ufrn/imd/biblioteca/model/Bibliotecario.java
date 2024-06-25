package br.ufrn.imd.biblioteca.model;

import java.time.LocalDate;

// Representa um biblitecário, que é um tipo de usuário da biblioteca.
public class Bibliotecario extends Usuario {
  private String login;
  private String senha;

  // Construtor padrão do biblitecário.
  public Bibliotecario(String nome, String cpf, String matricula, LocalDate dataNascimento, String login,
                       String senha) {
    super(nome, cpf, matricula, dataNascimento);
    this.login = login;
    this.senha = senha;
  }

  // Construtor de cópia de dados de outro bibliotecário.
  public Bibliotecario(Bibliotecario bibliotecario) {
    super(bibliotecario);
    this.login = bibliotecario.login;
    this.senha = bibliotecario.senha;
  }

  // Getters e setters de login e senha.
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

  // Validação do biblitecário.
  @Override
  public boolean validar() {
    return validarUsuario() && validarLogin() && validarSenha();
  }

  // Métodos privados de validação de login e senha.
  public boolean validarLogin() {
    return login != null && !login.isEmpty();
  }

  public boolean validarSenha() {
    return senha != null && !senha.isEmpty();
  }
}