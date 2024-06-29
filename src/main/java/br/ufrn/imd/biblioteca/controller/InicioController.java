package br.ufrn.imd.biblioteca.controller;

import java.io.IOException;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.service.OperacoesEmprestimos;
import br.ufrn.imd.biblioteca.service.OperacoesUsuarios;
import br.ufrn.imd.biblioteca.service.OperacoesLivros;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InicioController {
  // Elementos da interface gráfica.
  @FXML
  private Label lbNumUsuarios;

  @FXML
  private Label lbNumLivros;

  @FXML
  private Label lbNumEmprestimos;

  @FXML
  private Label lbNumAtrasos;

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
    lbNumUsuarios.setText("Nº de usuários: " + OperacoesUsuarios.quantidadeUsuarios());
    lbNumLivros.setText("Tamanho do acervo de livros: " + OperacoesLivros.quantidadeLivros());
    lbNumEmprestimos.setText("Nº de empréstimo: " + OperacoesEmprestimos.quantidadeEmprestimos());
    lbNumAtrasos.setText("Nº  de empréstimos em atraso: " + OperacoesEmprestimos.quantidadeEmprestimosAtrados());
  }
  
}