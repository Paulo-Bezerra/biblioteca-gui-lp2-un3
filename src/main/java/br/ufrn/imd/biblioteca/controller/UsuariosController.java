package br.ufrn.imd.biblioteca.controller;

import java.io.IOException;

import br.ufrn.imd.biblioteca.App;
import javafx.fxml.FXML;

public class UsuariosController {
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
}
