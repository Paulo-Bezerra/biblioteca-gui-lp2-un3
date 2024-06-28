package br.ufrn.imd.biblioteca.controller;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.service.OperacoesLivros;
import br.ufrn.imd.biblioteca.util.Alerta;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CadastrarLivroController {
  // Elementos da interface gráfica.
  @FXML
  private TextField tfTitulo;

  @FXML
  private TextField tfAutor;

  @FXML
  private TextField tfAssunto;

  @FXML
  private TextField tfISBN;

  @FXML
  private TextField tfAno;

  @FXML
  private TextField tfEstoque;

  // Método para cadastrar um livro.
  @FXML
  private void cadastraLivro() {
    if (camposLivroVazios() || tfEstoque.getText().isEmpty()) {
      Alerta.exibirAviso("Cadastro", "Preencha todos os campos!");
      return;
    }

    boolean cadastrou = OperacoesLivros.cadastrarLivro(
      tfTitulo.getText(),
      tfAutor.getText(),
      tfAssunto.getText(),
      tfISBN.getText(),
      tfAno.getText(),
      tfEstoque.getText()
    );

    if (cadastrou) {
      Alerta.exibirInformacao("Cadastro", "Livro cadastrado com sucesso!");

      // caso fique melhor limpar os campos depos de cadastrar.
      // limparCampos();
    } else {
      Alerta.exibirErro("Cadastro", "Não foi possivel cadastrar o livro!");
    }
  }

  // Verifica se há campos de usuário vazios.
  @FXML
  private boolean camposLivroVazios() {
    return tfTitulo.getText().isEmpty()
           || tfAutor.getText().isEmpty()
           || tfAssunto.getText().isEmpty()
           || tfISBN.getText().isEmpty()
           || tfAno.getText().isEmpty()
           || tfEstoque.getText().isEmpty();
  }

  // Volta para a tela de livros.
  @FXML
  private void voltar() throws IOException {
    App.trocarTela("livros");
  }

  // Limpa todos os campos de entrada
  @FXML
  private void limparCampos() {
    tfTitulo.clear();
    tfAutor.clear();
    tfAssunto.clear();
    tfISBN.clear();
    tfAno.clear();
    tfEstoque.clear();
  }
}
