package br.ufrn.imd.biblioteca.controller;

import br.ufrn.imd.biblioteca.App;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CadastrarUsuarioController {
  @FXML
  private ToggleGroup TipoUsuario;

  @FXML
  private VBox ctBibliotecario;

  @FXML
  private VBox ctEstudante;

  @FXML
  private VBox ctProfessor;

  @FXML
  private RadioButton rbBibliotecario;

  @FXML
  private RadioButton rbEstudante;

  @FXML
  private RadioButton rbProfessor;

  @FXML
  private TextField tfCPF;

  @FXML
  private TextField tfNome;

  @FXML
  private void voltar() throws IOException {
    App.trocarTela("usuarios");
  }

  @FXML
  private void initialize() {
    TipoUsuario.selectedToggleProperty().addListener((observable, oldValue, newValue) -> atualizarVisibilidade(newValue));
  }

  @FXML
  private void atualizarVisibilidade(Toggle opcaoSelecionada) {
    if (opcaoSelecionada.equals(rbEstudante)) {
      ctEstudante.setVisible(true);
      ctProfessor.setVisible(false);
      ctBibliotecario.setVisible(false);
    } else if (opcaoSelecionada.equals(rbProfessor)) {
      ctEstudante.setVisible(false);
      ctProfessor.setVisible(true);
      ctBibliotecario.setVisible(false);
    } else if (opcaoSelecionada.equals(rbBibliotecario)) {
      ctEstudante.setVisible(false);
      ctProfessor.setVisible(false);
      ctBibliotecario.setVisible(true);
    }
  }
}