package br.ufrn.imd.biblioteca.controller;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.dto.EmprestimoDTO;
import br.ufrn.imd.biblioteca.model.Emprestimo;
import br.ufrn.imd.biblioteca.service.OperacoesEmprestimos;
import br.ufrn.imd.biblioteca.service.OperacoesLivros;
import br.ufrn.imd.biblioteca.util.Alerta;
import br.ufrn.imd.biblioteca.util.Tratamento;
import br.ufrn.imd.biblioteca.util.Validacao;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CadastrarEmprestimoController {
  @FXML
  private DatePicker dtEmprestimo;

  @FXML
  private TextField tfMatricula;

  @FXML
  private TextField tfIsbn;

  @FXML
  private void initialize() {
    dtEmprestimo.focusedProperty().addListener(
      (objObservado, valorAntigo, valorNovo) -> {
        if (!valorNovo && !dtEmprestimo.getEditor().getText().isEmpty()) { // Se o foco foi perdido.
          String dataString = dtEmprestimo.getEditor().getText();
          try {
            LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            // Se chegou aqui, a data é válida.
          } catch (DateTimeParseException e) {
            // Se houve erro ao converter a data limpa o DataPicker.
            dtEmprestimo.getEditor().clear();
            // Retorna no terminal o formato aceito.
            System.out.println("Data inválida: " + dataString + "\n Usar formato: \"dd/MM/yyyy\"");
            Alerta.exibirAviso("Cadastro", "Insira uma data válida!\nUsar formato: \"dd/MM/yyyy\"");
          }
        }
      }
    );

    tfMatricula.focusedProperty().addListener(
      (objObservado, valorAntigo, valorNovo) -> {
        if (!valorNovo && !tfMatricula.getText().isEmpty()) {
          if (!Validacao.strNumerica(tfMatricula.getText())) {
            tfMatricula.clear();
            Alerta.exibirAviso("Cadastro", "A mátrícula deve conter apenas números!");
          }
        }
      }
    );

    tfIsbn.focusedProperty().addListener(
      (objObservado, valorAntigo, valorNovo) -> {
        if (!valorNovo && !tfIsbn.getText().isEmpty()) {
          if (!Validacao.strNumerica(tfIsbn.getText())) {
            tfIsbn.clear();
            Alerta.exibirAviso("Cadastro", "O ISBN deve conter apenas números!");
          }
        }
      }
    );
  }

  @FXML
  private void cadastrarEmprestimo() {
    if (camposEmprestimoVazios()) {
      Alerta.exibirAviso("Cadastro", "Preencha todos os campos!");
      return;
    }

    String saida = OperacoesEmprestimos.cadastrarEmprestimo(
      tfMatricula.getText(),
      tfIsbn.getText(),
      dtEmprestimo.getValue()
    );

    if (Tratamento.contemString(saida, "sucesso")) {
      Alerta.exibirInformacao("Cadastro", saida);
      return;
    }

    Alerta.exibirAviso("Cadastro", saida);
  }


  // Verifica se há campos de usuário vazios.
  @FXML
  private boolean camposEmprestimoVazios() {
    return tfMatricula.getText().isEmpty()
      || tfIsbn.getText().isEmpty()
      || dtEmprestimo.getValue() == null;
  }

  // Volta para a tela de livros.
  @FXML
  private void voltar() throws IOException {
    App.trocarTela("emprestimos");
  }
}
