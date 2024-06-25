package br.ufrn.imd.biblioteca.model;

import br.ufrn.imd.biblioteca.util.Validacao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

// Define um usuário genérico da biblioteca.
public abstract class Usuario implements Serializable, IValidarClasse {
  private String nome;
  private String cpf;
  private String matricula;
  private LocalDate dataNascimento;

  // Construtor padrão do usuário.
  public Usuario(String nome, String cpf, String matricula, LocalDate dataNascimento) {
    this.nome = nome;
    this.cpf = cpf;
    this.matricula = matricula;
    this.dataNascimento = dataNascimento;
  }

  // Construtor de cópia de dados de outro usuário.
  public Usuario(Usuario usuario) {
    this.nome = usuario.getNome();
    this.cpf = usuario.getCpf();
    this.matricula = usuario.getMatricula();
    this.dataNascimento = usuario.getDataNascimento();
  }

  // Getters e setters.
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

  // Método protegido de validação dos atributos do usuário.
  protected boolean validarUsuario() {
    return validarNome() && validarCpf() && validarMatricula() && validarDataNascimento();
  }

  // Métodos privados de validação dos atributos.
  private boolean validarNome() {
    return nome != null && !nome.isEmpty();
  }

  private boolean validarCpf() {
    return cpf != null && cpf.length() == 11 && Validacao.strNumerica(cpf);
  }

  private boolean validarMatricula() {
    return matricula != null && !matricula.isEmpty() && Validacao.strNumerica(matricula);
  }

  private boolean validarDataNascimento() {
    return dataNascimento != null && !dataNascimento.isAfter(LocalDate.now());
  }

  // Sobrescreve equals e hashCode para comparar os usuários pela matrícula.
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