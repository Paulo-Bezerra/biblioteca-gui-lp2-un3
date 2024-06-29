package br.ufrn.imd.biblioteca.controller;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.dto.EmprestimoDTO;
import br.ufrn.imd.biblioteca.dto.LivroDTO;
import br.ufrn.imd.biblioteca.model.Emprestimo;
import br.ufrn.imd.biblioteca.model.Livro;
import br.ufrn.imd.biblioteca.service.OperacoesEmprestimos;
import br.ufrn.imd.biblioteca.service.OperacoesUsuarios;
import br.ufrn.imd.biblioteca.service.OperacoesLivros;
import br.ufrn.imd.biblioteca.util.Alerta;
import br.ufrn.imd.biblioteca.util.Tratamento;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class EmprestimosController {
  // Elementos da interface gráfica.
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

  // Lista todos os emprestimos ativos e exibe na ListView
  @FXML
  private void listarEmprestimos() {
    List<EmprestimoDTO> emprestimos = OperacoesEmprestimos.listarEmprestimos();
    emprestimos.sort(Comparator.comparing(EmprestimoDTO::matricula));
    lvEmprestimos.getItems().setAll(emprestimos);
  }

  // Métodos para navegação entre telas
  @FXML
  private void cadastraEmprestimo() throws IOException {
    App.trocarTela("cadastrar-emprestimo");
  }

  @FXML
  private void removerEmprestimo() {
    EmprestimoDTO emprestimo = lvEmprestimos.getSelectionModel().getSelectedItem();
    if (emprestimo != null && Alerta.exibirConfirmacao("Devolução", "Devolver: " + emprestimo + ".")) {
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

    String saida = String.format(modelo,
      e.getNome(),
      e.getMatricula(),
      e.getTitulo(),
      e.getIsbn(),
      e.getDataEmprestimo(),
      e.getDataDevolucao()
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