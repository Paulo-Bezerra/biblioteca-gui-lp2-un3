package br.ufrn.imd.biblioteca.controller;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.dto.EmprestimoDTO;
import br.ufrn.imd.biblioteca.service.OperacoesEmprestimos;
import br.ufrn.imd.biblioteca.util.Alerta;
import br.ufrn.imd.biblioteca.util.FiltroPesquisa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.*;

import static javafx.scene.input.KeyCode.ENTER;

public class EmprestimosController {
  private static FiltroPesquisa filtroPesquisa = FiltroPesquisa.MATRICULA;
  private static List<EmprestimoDTO> listaEmprestimos = new ArrayList<>();
  private static List<EmprestimoDTO> listaAtrasos = new ArrayList<>();
  // Elementos da interface gráfica.
  @FXML
  private ToggleGroup tgFiltroPesquisa;

  @FXML
  private RadioButton rbIsbn;

  @FXML
  private RadioButton rbMatricula;
  @FXML
  private TextField tfBusca;

  @FXML
  private Button btDetalhes;

  @FXML
  private Button btRemover;

  @FXML
  private ListView<EmprestimoDTO> lvEmprestimos;

  @FXML
  private void initialize() {
    tgFiltroPesquisa.selectedToggleProperty().addListener(
      (objObservado, valorAntigo, valorNovo) -> atualizarFiltroPesquisa(valorNovo)
    );
    lvEmprestimos.getSelectionModel().selectedItemProperty().addListener(
      (observado, antigoValor, novoValor) -> {
        btRemover.setDisable(novoValor == null);
        btDetalhes.setDisable(novoValor == null);
      }
    );
    lvEmprestimos.setOnMouseClicked(mouseEvent -> {
      if (mouseEvent.getClickCount() == 2) {
        mostrarDetalhes();
      }
    });
  }

  @FXML
  private void atualizarFiltroPesquisa(Toggle novoValor) {
    filtroPesquisa = (novoValor == rbMatricula) ? FiltroPesquisa.MATRICULA : FiltroPesquisa.ISBN;
    tfBusca.setPromptText("Buscar por " + filtroPesquisa.getDescricao() + ".");
  }

  // Lista todos os emprestimos ativos e exibe na ListView
  @FXML
  private void listarEmprestimos() {
    if (listaEmprestimos.isEmpty()) listaEmprestimos = OperacoesEmprestimos.listarEmprestimos();

    lvEmprestimos.getItems().setAll(listaEmprestimos);
  }

  @FXML
  private void listarEmprestimosAtrasados() {
    if (listaAtrasos.isEmpty()) listaAtrasos = OperacoesEmprestimos.listarEmprestimosAtrados();

    lvEmprestimos.getItems().setAll(listaAtrasos);
  }

  @FXML
  private void buscarComEnter(KeyEvent tecla) {
    if (tecla.getCode() == ENTER) buscarEmprestimos();
  }

  @FXML
  private void buscarEmprestimos() {
    if (tfBusca.getText().isEmpty()) return;

    switch (filtroPesquisa) {
      case MATRICULA ->
        lvEmprestimos.getItems().setAll(OperacoesEmprestimos.listarEmprestimosPorMatricula(tfBusca.getText().trim()));
      case ISBN ->
        lvEmprestimos.getItems().setAll(OperacoesEmprestimos.listarEmprestimosPorIsbn(tfBusca.getText().trim()));
    }
  }

  // Métodos para navegação entre telas
  @FXML
  private void cadastraEmprestimo() throws IOException {
    App.trocarTela("cadastrar-emprestimo");
  }

  @FXML
  private void removerEmprestimo() {
    EmprestimoDTO emprestimo = lvEmprestimos.getSelectionModel().getSelectedItem();
    if (emprestimo != null && Alerta.exibirConfirmacao("Devolução", "Devolver: " + emprestimo.titulo() +
      "\nData do empréstimo: " + emprestimo.dataEmprestimo() + "\nPrazo: " + emprestimo.dataDevolucao())) {
      if (OperacoesEmprestimos.removerEmprestimo(emprestimo.matricula(), emprestimo.isbn())) {
        lvEmprestimos.getItems().remove(emprestimo);
        listaEmprestimos.clear();
        listaAtrasos.clear();
        Alerta.exibirInformacao("Devolução", "Devolução realizada com sucesso!");
      } else {
        Alerta.exibirErro("Devolução", "Não foi possivel realizar a devolução do livro!");
      }
    }
  }

  @FXML
  private void mostrarDetalhes() {
    EmprestimoDTO e = lvEmprestimos.getSelectionModel().getSelectedItem();
    if (e == null) {
      return;
    }

    String modelo = """
      Nome: %s
      Matrícula: %s
      Livro: %s
      ISBN: %s
      Data do empréstimo: %s
      Data da devolução: %s""";

    String saida = String.format(modelo,
      e.nome(),
      e.matricula(),
      e.titulo(),
      e.isbn(),
      e.dataEmprestimo(),
      e.dataDevolucao()
    );
    Alerta.exibirInformacao("Dados do empréstimo", saida);
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