package br.ufrn.imd.biblioteca.controller;

import br.ufrn.imd.biblioteca.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class LivrosController {
  @FXML
  private void inicio() throws IOException {
    App.trocarTela("inicio");
  }
  @FXML
  private void usuarios() throws IOException {
    App.trocarTela("usuarios");
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
