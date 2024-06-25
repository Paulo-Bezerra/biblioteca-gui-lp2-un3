package br.ufrn.imd.biblioteca.repository;

import br.ufrn.imd.biblioteca.model.Usuario;
import br.ufrn.imd.biblioteca.model.Estudante;
import br.ufrn.imd.biblioteca.model.Professor;
import br.ufrn.imd.biblioteca.model.Bibliotecario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UsuarioRepository implements Serializable {
  // Armazena os usuários cadastrados, usando suas matrículas como chave.
  private final HashMap<String, Usuario> matriculas_UR;

  // Construtor padrão que inicializa matriculas_UR.
  public UsuarioRepository() {
    this.matriculas_UR = new HashMap<>();
  }

  // Construtor de cópia de dados de outro UsuarioRepository.
  public UsuarioRepository(UsuarioRepository usuarioRepository) {
    this.matriculas_UR = new HashMap<>(usuarioRepository.matriculas_UR);
  }

  // Cadastra um usuário se a matrícula ainda não estiver registrada.
  // Retorna true se bem-sucedido.
  public boolean cadastrarUsuario(Usuario usuario) {
    if (matriculas_UR.containsKey(usuario.getMatricula())) {
      return false;
    }
    matriculas_UR.put(usuario.getMatricula(), usuario);
    return true;
  }

  // Remove um usuário pela matrícula. Retorna true se bem-sucedido.
  public boolean removerUsuario(String matricula) {
    if (!matriculas_UR.containsKey(matricula)) {
      return false;
    }
    matriculas_UR.remove(matricula);
    return true;
  }

  // Retorna a lista de todos os usuários cadastrados.
  public List<Usuario> getUsuarios() {
    return new ArrayList<>(matriculas_UR.values());
  }

  // Retorna um usuário pela matrícula dele.
  public Usuario getUsuario(String matricula) {
    // Trata os tipos de usuário para retornar uma cópia.
    switch (matriculas_UR.get(matricula)) {
      case Estudante estudante -> {
        return new Estudante(estudante);
      }
      case Professor professor -> {
        return new Professor(professor);
      }
      case Bibliotecario bibliotecario -> {
        return new Bibliotecario(bibliotecario);
      }
      default -> {
        return null;
      }
    }
  }

  // Verifica se existe um usuário com a matrícula fornecida.
  public boolean existeUsuario(String matricula) {
    return matriculas_UR.containsKey(matricula);
  }
}