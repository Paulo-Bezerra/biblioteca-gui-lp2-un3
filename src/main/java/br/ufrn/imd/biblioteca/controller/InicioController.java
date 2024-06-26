package br.ufrn.imd.biblioteca.controller;

import java.io.IOException;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.service.OperacoesUsuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InicioController {
  // Elementos da interface gráfica.
  @FXML
  private Label lbNumUsuarios;

  // Métodos para navegação entre telas
  @FXML
  private void usuarios() throws IOException {
    App.trocarTela("usuarios");
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

  @FXML
  void initialize() {
    lbNumUsuarios.setText("Nº de usuários: " + OperacoesUsuarios.getNumUsuarios());
  }
}