package br.ufrn.imd.biblioteca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.dto.LivroDTO;
import br.ufrn.imd.biblioteca.service.OperacoesLivros;
import br.ufrn.imd.biblioteca.util.Alerta;
import br.ufrn.imd.biblioteca.util.Tratamento;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class LivrosController {
  // Elementos da interface gráfica.
  @FXML
  private TextField tfBusca;

  @FXML
  private Button btDetalhes;

  @FXML
  private Button btRemover;

  @FXML
  private ListView<LivroDTO> lvLivros;

  @FXML
  private void initialize() {
    lvLivros.getSelectionModel().selectedItemProperty().addListener(
      (observado, antigoValor, novoValor) -> {
        btRemover.setDisable(novoValor == null);
        btDetalhes.setDisable(novoValor == null);
      }
    );
    lvLivros.setOnMouseClicked(mouseEvent -> {
      if (mouseEvent.getClickCount() == 2) {
        mostrarDetalhes();
      }
    });
  }

  // Filtra o livro pelo isbn digitado no TextField de busca
  @FXML
  private void buscarLivros() {
    if (tfBusca.getText().trim().isEmpty()) {
      return;
    }
    List<LivroDTO> livros = new ArrayList<>();
    for (LivroDTO livro : OperacoesLivros.listarLivros()) {
      if (Tratamento.contemString(livro.titulo(), tfBusca.getText().trim())) {
        livros.add(livro);
      }
    }
    livros.sort(Comparator.comparing(LivroDTO::titulo));
    lvLivros.getItems().setAll(livros);
  }

  // Lista todos os livros e exibe na ListView
  @FXML
  private void listarLivros() {
    List<LivroDTO> livros = OperacoesLivros.listarLivros();
    livros.sort(Comparator.comparing(LivroDTO::titulo));
    lvLivros.getItems().setAll(livros);
  }

  // Métodos para navegação entre telas
  @FXML
  private void cadastrarLivro() throws IOException {
    App.trocarTela("cadastrar-livro");
  }

  @FXML
  private void removerLivro() {
    LivroDTO livro = lvLivros.getSelectionModel().getSelectedItem();
    if (livro != null && Alerta.exibirConfirmacao("Remoção", "Remover: " + livro + ".")) {
      if (OperacoesLivros.removerLivro(livro.isbn())) {
        lvLivros.getItems().remove(livro);
        Alerta.exibirInformacao("Remoção", "Livro removido com sucesso!");
      } else {
        Alerta.exibirErro("Remoção", "Não foi possivel remover o livro!");
      }
    }
  }

  @FXML
  private void mostrarDetalhes() {
    
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