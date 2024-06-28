package br.ufrn.imd.biblioteca.controller;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.service.OperacoesUsuarios;
import br.ufrn.imd.biblioteca.util.Alerta;
import javafx.fxml.FXML;

import java.io.IOException;

public class EmprestimosController {
  // Métodos para navegação entre telas
  @FXML
  private void cadastraEmprestimo() throws IOException {
    App.trocarTela("cadastrar-emprestimo");
  }
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