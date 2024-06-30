package br.ufrn.imd.biblioteca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.dto.LivroDTO;
import br.ufrn.imd.biblioteca.model.Livro;
import br.ufrn.imd.biblioteca.service.OperacoesLivros;
import br.ufrn.imd.biblioteca.util.Alerta;
import br.ufrn.imd.biblioteca.util.Tratamento;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import static javafx.scene.input.KeyCode.ENTER;

public class LivrosController {
  // Elementos da interface gráfica.
  @FXML
  private TextField tfBusca;

  @FXML
  private Button btDetalhes;

  @FXML
  private Button btRemover;

  @FXML
  private Label lbCopiado;

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

  @FXML
  private void buscarComEnter(KeyEvent tecla) {
    if (tecla.getCode() == ENTER) buscarLivro();
  }

  // Filtra o livro pelo isbn digitado no TextField de busca
  @FXML
  private void buscarLivro() {
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

  @FXML
  private void copiarIsbn(MouseEvent mouse) {
    lbCopiado.setVisible(false);

    // Verifica se o botão direito do mouse foi pressionado.
    if (!mouse.isSecondaryButtonDown()) return;

    try {
      // Obtém o isbn do item selecionado.
      String isbn = lvLivros.getSelectionModel().getSelectedItem().isbn();

      // Copia para a área de transferência.
      ClipboardContent conteudo = new ClipboardContent();
      conteudo.putString(isbn);
      Clipboard.getSystemClipboard().setContent(conteudo);

      // Mostra o label de "Copiado".
      lbCopiado.setVisible(true);

    } catch (Exception ignored) {}
  }

  // Métodos para navegação entre telas
  @FXML
  private void cadastrarLivro() throws IOException {
    App.trocarTela("cadastrar-livro");
  }

  @FXML
  private void removerLivro() {
    LivroDTO livro = lvLivros.getSelectionModel().getSelectedItem();
    if (livro == null) return;
    if (!Alerta.exibirConfirmacao("Remoção", "Remover: " + livro + "?")) {
      Alerta.exibirAviso("Remoção", "Remoção cancelada!");
      return;
    }
    if (OperacoesLivros.removerLivro(livro.isbn())) {
      lvLivros.getItems().remove(livro);
      Alerta.exibirInformacao("Remoção", "Livro removido com sucesso!");
      return;
    }
    Alerta.exibirErro("Remoção", "Não foi possivel remover o livro!");
  }

  @FXML
  private void mostrarDetalhes() {
    LivroDTO livro = lvLivros.getSelectionModel().getSelectedItem();
    Livro l = OperacoesLivros.getLivro(livro.isbn());
    if (l == null) {
      return;
    }
    String modelo = """
      Título: %s
      Autor: %s
      Assunto: %s
      ISBN: %s
      Ano: %d
      Estoque: %d
      Disponivel: %d""";

    String saida = String.format(modelo,
      l.getTitulo(),
      l.getAutor(),
      l.getAssunto(),
      l.getIsbn(),
      l.getAno(),
      l.getEstoque(),
      OperacoesLivros.quantidadeLivrosDisponivel(l.getIsbn())
    );
    Alerta.exibirInformacao("Dados do livro", saida);
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
  private void emprestimos() throws IOException {
    App.trocarTela("emprestimos");
  }

  @FXML
  private void sair() throws IOException {
    App.trocarTela("login");
  }
}