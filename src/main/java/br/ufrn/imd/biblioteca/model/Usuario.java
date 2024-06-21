package br.ufrn.imd.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Usuario implements Serializable {
  private String nome;
  private String cpf;
  private String matricula;
  private LocalDate dataNascimento;

  public Usuario(String nome, String cpf, String matricula, LocalDate dataNascimento) {
    this.nome = nome;
    this.cpf = cpf;
    this.matricula = matricula;
    this.dataNascimento = dataNascimento;
  }

  public Usuario(Usuario usuario) {
    this.nome = usuario.getNome();
    this.cpf = usuario.getCpf();
    this.matricula = usuario.getMatricula();
    this.dataNascimento = usuario.getDataNascimento();
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Usuario usuario = (Usuario) o;
    return Objects.equals(matricula, usuario.matricula);
  }

  @Override
  public int hashCode() {
    return Objects.hash(matricula);
  }
}
