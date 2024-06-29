package br.ufrn.imd.biblioteca.controller;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.dto.EmprestimoDTO;
import br.ufrn.imd.biblioteca.model.Emprestimo;
import br.ufrn.imd.biblioteca.service.OperacoesEmprestimos;
import br.ufrn.imd.biblioteca.util.Alerta;
import br.ufrn.imd.biblioteca.util.FiltroPesquisa;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class EmprestimosController {
  private static FiltroPesquisa filtroPesquisa;
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
    if (novoValor == null) return;
    filtroPesquisa = (novoValor == rbMatricula) ? FiltroPesquisa.MATRICULA: FiltroPesquisa.ISBN;
    tfBusca.setPromptText("Buscar por " + filtroPesquisa.getDescricao() + ".");
  }

  // Lista todos os emprestimos ativos e exibe na ListView
  @FXML
  private void listarEmprestimos() {
    List<EmprestimoDTO> emprestimos = OperacoesEmprestimos.listarEmprestimos();
    emprestimos.sort(Comparator.comparing(EmprestimoDTO::matricula));
    lvEmprestimos.getItems().setAll(emprestimos);
  }

  @FXML
  private void listarEmprestimosAtrasados() {
    List<EmprestimoDTO> atrasados = OperacoesEmprestimos.listarEmprestimosAtrados();
    atrasados.sort(Comparator.comparing(EmprestimoDTO::nome));
    lvEmprestimos.getItems().setAll(atrasados);
  }
  @FXML
  private void buscarEmprestimos() {

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
        Alerta.exibirInformacao("Devolução", "Devolução realizada com sucesso!");
      } else {
        Alerta.exibirErro("Devolução", "Não foi possivel realizar a devolução do livro!");
      }
    }
  }

  @FXML
  private void mostrarDetalhes() {
    EmprestimoDTO emprestimo = lvEmprestimos.getSelectionModel().getSelectedItem();
    Emprestimo e = OperacoesEmprestimos.getEmprestimo(emprestimo.matricula(), emprestimo.isbn());
    if (e == null) {
      return;
    }
    String modelo = """
      Nome: %s
      Matrícula: %s
      Livro: %s
      ISBN: %s
      Data do empréstimo: %d
      Data da devolução: %d""";

//    String saida = String.format(modelo,
//      e.getNome(),
//      e.getMatricula(),
//      e.getTitulo(),
//      e.getIsbn(),
//      e.getDataEmprestimo(),
//      e.getDataDevolucao()
//    );
//    Alerta.exibirInformacao("Dados do empréstimo", saida);
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