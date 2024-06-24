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
  private final HashMap<String, Usuario> matriculas_UR;

  public UsuarioRepository() {
    this.matriculas_UR = new HashMap<>();
  }

  public UsuarioRepository(UsuarioRepository usuarioRepository) {
    this.matriculas_UR = new HashMap<>(usuarioRepository.matriculas_UR);
  }

  public boolean adicionarUsuario(Usuario usuario) {
    if (matriculas_UR.containsKey(usuario.getMatricula())) {
      return false;
    }
    matriculas_UR.put(usuario.getMatricula(), usuario);
    return true;
  }

  public boolean removerUsuario(String matricula) {
    if (!matriculas_UR.containsKey(matricula)) {
      return false;
    }
    matriculas_UR.remove(matricula);
    return true;
  }

  public List<Usuario> getUsuarios() {
    return new ArrayList<>(matriculas_UR.values());
  }

  public Usuario getUsuarioPorMatricula(String matricula) {
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
  public boolean existeUsuario(String matricula) {
    return matriculas_UR.containsKey(matricula);
  }
}
