package br.ufrn.imd.biblioteca.service;

import br.ufrn.imd.biblioteca.dao.BancoDAO;
import br.ufrn.imd.biblioteca.model.Bibliotecario;
import br.ufrn.imd.biblioteca.model.Estudante;
import br.ufrn.imd.biblioteca.model.Professor;
import br.ufrn.imd.biblioteca.model.Usuario;
import br.ufrn.imd.biblioteca.repository.UsuarioRepository;

import java.time.LocalDate;

public class OperacoesUsuarios {
  private static final BancoDAO bancoDAO = BancoDAO.getInstance();

  private static UsuarioRepository getUR() {
    return bancoDAO.getUR();
  }

  public static boolean cadastrarEstudante(String nome, String cpf, String matricula, LocalDate dataNascimento, String curso) {
    Estudante estudante = new Estudante(nome, cpf, matricula, dataNascimento, curso);
    return estudante.validar() && cadastarUsuario(estudante);
  }

  public static boolean cadastrarProfessor (String nome, String cpf, String matricula, LocalDate dataNascimento, String departamento) {
    Professor professor = new Professor(nome, cpf, matricula, dataNascimento, departamento);
    return professor.validar() && cadastarUsuario(professor);
  }

  public static boolean cadastrarBiliotecario (String nome, String cpf,  String matricula, LocalDate dataNascimento, String login, String senha) {
    Bibliotecario bibliotecario = new Bibliotecario(nome, cpf, matricula, dataNascimento, login, senha);
    return bibliotecario.validar() && cadastarUsuario(bibliotecario);
  }

  private static boolean cadastarUsuario(Usuario usuario) {
    return getUR().cadastrarUsuario(usuario);
  }

  // Método para verificar se uma string contém apenas números
  private static boolean isNumeric(String str) {
    return str.matches("\\d+");
  }
}
