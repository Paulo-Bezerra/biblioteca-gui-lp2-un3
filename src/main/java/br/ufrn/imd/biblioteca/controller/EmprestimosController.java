package br.ufrn.imd.biblioteca.controller;

import br.ufrn.imd.biblioteca.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class EmprestimosController {
  @FXML
  private void inicio() throws IOException {
    App.trocarTela("inicio");
  }
  @FXML
  private void usuarios() throws IOException {
    App.trocarTela("usuarios");
  }
  @FXML
  private void livros() throws IOException {
    App.trocarTela("livros");
  }
  @FXML
  private void sair() throws IOException {
    App.trocarTela("login");
  }
}
