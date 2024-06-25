package br.ufrn.imd.biblioteca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.dto.UsuarioDTO;
import br.ufrn.imd.biblioteca.service.OperacoesUsuarios;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class UsuariosController {
  // Elementos da interface gráfica.
  @FXML
  private TextField tfBusca;

  @FXML
  private ListView<UsuarioDTO> lvUsuarios;

  // Métodos para navegação entre telas
  @FXML
  private void cadastrarUsuario() throws IOException {
    App.trocarTela("cadastrar-usuario");
  }

  @FXML
  private void inicio() throws IOException {
    App.trocarTela("inicio");
  }

  @FXML
  private void livros() throws IOException {
    App.trocarTela("livros");
  }

  @FXML
  private void emprestimos() throws IOException {
    App.trocarTela("emprestimos");
  }

  @FXML
  private void sair() throws IOException {
    App.trocarTela("login");
  }

  // Lista todos os usuários e exibe na ListView
  @FXML
  private void listarUsarios() {
    List<UsuarioDTO> usuarios = OperacoesUsuarios.listarUsuarios();
    usuarios.sort(Comparator.comparing(UsuarioDTO::nome));
    lvUsuarios.getItems().setAll(usuarios);
  }

  // Filtra usuários pelo nome digitado no TextField de busca
  @FXML
  private void buscarUsuario() {
    if (tfBusca.getText().trim().isEmpty()) {
      return;
    }
    List<UsuarioDTO> usuarios = new ArrayList<>();
    for (UsuarioDTO usuario : OperacoesUsuarios.listarUsuarios()) {
      if (usuario.nome().matches(".*" + tfBusca.getText().trim() + ".*")) {
        usuarios.add(usuario);
      }
    }
    usuarios.sort(Comparator.comparing(UsuarioDTO::nome));
    lvUsuarios.getItems().setAll(usuarios);
  }
}