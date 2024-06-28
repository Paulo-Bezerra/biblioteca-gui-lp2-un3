package br.ufrn.imd.biblioteca.controller;

import br.ufrn.imd.biblioteca.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class CadastrarEmprestimoController {
  // Volta para a tela de livros.
  @FXML
  private void voltar() throws IOException {
    App.trocarTela("emprestimos");
  }
}
