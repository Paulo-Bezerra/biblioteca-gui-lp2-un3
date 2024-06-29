package br.ufrn.imd.biblioteca.service;

import br.ufrn.imd.biblioteca.dao.BancoDAO;
import br.ufrn.imd.biblioteca.dto.UsuarioDTO;
import br.ufrn.imd.biblioteca.model.Bibliotecario;
import br.ufrn.imd.biblioteca.model.Estudante;
import br.ufrn.imd.biblioteca.model.Professor;
import br.ufrn.imd.biblioteca.model.Usuario;
import br.ufrn.imd.biblioteca.repository.EmprestimoRepository;
import br.ufrn.imd.biblioteca.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OperacoesUsuarios {
  // Recupera o repostório de usuário contida no BancoDAO.
  private static UsuarioRepository getUR() {
    return BancoDAO.getInstance().getUR();
  }

  // Cria, valida e cadastra um estudante. Retorna true se bem-sucedido.
  public static boolean cadastrarEstudante(String nome, String cpf, String matricula, LocalDate dataNascimento, String curso) {
    Estudante estudante = new Estudante(nome, cpf, matricula, dataNascimento, curso);
    return estudante.validar() && cadastarUsuario(estudante);
  }

  // Cria, valida e cadastra um professor. Retorna true se bem-sucedido.
  public static boolean cadastrarProfessor(String nome, String cpf, String matricula, LocalDate dataNascimento, String departamento) {
    Professor professor = new Professor(nome, cpf, matricula, dataNascimento, departamento);
    return professor.validar() && cadastarUsuario(professor);
  }

  // Cria, valida e cadastra um bibliotecário. Retorna true se bem-sucedido.
  public static boolean cadastrarBiliotecario(String nome, String cpf, String matricula, LocalDate dataNascimento, String login, String senha) {
    Bibliotecario bibliotecario = new Bibliotecario(nome, cpf, matricula, dataNascimento, login, senha);
    return bibliotecario.validar() && cadastarUsuario(bibliotecario);
  }

  // Cadastra um usuário no repositório. Retorna true se bem-sucedido.
  private static boolean cadastarUsuario(Usuario usuario) {
    return getUR().cadastrarUsuario(usuario);
  }
  private static EmprestimoRepository getER() {
    return BancoDAO.getInstance().getER();
  }

  // Retorna a lista de todos os usuários cadastrados.
  public static List<UsuarioDTO> listarUsuarios() {
    ArrayList<UsuarioDTO> usuariosDTO = new ArrayList<>();
    for (Usuario usuario : getUR().getUsuarios()) {
      usuariosDTO.add(new UsuarioDTO(usuario.getNome(), usuario.getMatricula()));
    }
    return usuariosDTO;
  }

  public static boolean removerUsuario(String matricula) {
    return matricula != null && getER().quantidadeEmprestimoPorMatricula(matricula) == 0 && getUR().removerUsuario(matricula);
  }

  public static int quantidadeUsuarios() {
      return getUR().quantidadeUsuarios();
  }

  public static Usuario getUsuario(String matricula) {
    return getUR().getUsuario(matricula);
  }
}