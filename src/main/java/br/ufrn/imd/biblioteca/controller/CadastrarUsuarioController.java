package br.ufrn.imd.biblioteca.controller;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.service.OperacoesUsuarios;
import br.ufrn.imd.biblioteca.util.Alerta;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CadastrarUsuarioController {
  @FXML
  private TextField tfNome;

  @FXML
  private TextField tfCPF;

  @FXML
  private TextField tfMatricula;

  @FXML
  private DatePicker dtNascimento;

  @FXML
  private ToggleGroup TipoUsuario;

  @FXML
  private RadioButton rbEstudante;

  @FXML
  private RadioButton rbProfessor;

  @FXML
  private RadioButton rbBibliotecario;

  @FXML
  private VBox ctEstudante;

  @FXML
  private TextField tfCurso;

  @FXML
  private VBox ctProfessor;

  @FXML
  private TextField tfDepartamento;

  @FXML
  private VBox ctBibliotecario;

  @FXML
  private TextField tfLogin;

  @FXML
  private PasswordField pfSenha;

  @FXML
  private void initialize() {
    TipoUsuario.selectedToggleProperty().addListener(
      (objObservado, valorAntigo, valorNovo) -> atualizarVisibilidade(valorNovo)
    );

    // caso precise verificar o radiobutton assim que entrar na tela
    // atualizarVisibilidade(TipoUsuario.getSelectedToggle());
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

  @FXML
  private void voltar() throws IOException {
    App.trocarTela("usuarios");
  }


  @FXML
  private void cadastraEstudante() {
    if (camposUsuarioVazios() || tfCurso.getText().isEmpty()) {
      Alerta.exibirAlerta("Cadastro", "Preencha todos os campos!");
      return;
    }
    boolean cadastrou = OperacoesUsuarios.cadastrarEstudante(
      tfNome.getText(),
      tfCPF.getText(),
      tfMatricula.getText(),
      dtNascimento.getValue(),
      tfCurso.getText()
    );

    if (cadastrou) {
      Alerta.exibirAlerta("Cadastro", "Estundante cadastrado com sucesso!");

      // caso fique melhor limpar os campos depos de cadastrar.
      // limparCampos();
    } else {
      Alerta.exibirAlerta("Cadastro", "Não foi possivel cadastrar o estudante!");
    }
  }

  @FXML
  private void cadastrarProfessor() {
    if (camposUsuarioVazios() || tfDepartamento.getText().isEmpty()) {
      Alerta.exibirAlerta("Cadastro", "Preencha todos os campos!");
      return;
    }
    boolean cadastrou = OperacoesUsuarios.cadastrarProfessor(
      tfNome.getText(),
      tfCPF.getText(),
      tfMatricula.getText(),
      dtNascimento.getValue(),
      tfDepartamento.getText()
    );
    if (cadastrou) {
      Alerta.exibirAlerta("Cadastro", "Professor cadastrado com sucesso!");

      // caso fique melhor limpar os campos depos de cadastrar.
      // limparCampos();
    } else {
      Alerta.exibirAlerta("Cadastro", "Não foi possivel cadastrar o professor!");
    }
  }


  @FXML
  private void cadastrarBiblitecario() {
    if (camposUsuarioVazios() || tfLogin.getText().isEmpty() || pfSenha.getText().isEmpty()) {
      Alerta.exibirAlerta("Cadastro", "Preencha todos os campos!");
      return;
    }
    boolean cadastrou = OperacoesUsuarios.cadastrarBiliotecario(
      tfNome.getText(),
      tfCPF.getText(),
      tfMatricula.getText(),
      dtNascimento.getValue(),
      tfLogin.getText(),
      pfSenha.getText()
    );
    if (cadastrou) {
      Alerta.exibirAlerta("Cadastro", "Bibliotecário cadastrado com sucesso!");

      // caso fique melhor limpar os campos depos de cadastrar.
      // limparCampos();
    } else {
      Alerta.exibirAlerta("Cadastro", "Não foi possivel cadastrar o bibliotecário!");
    }
  }

  @FXML
  private boolean camposUsuarioVazios() {
    return tfNome.getText().isEmpty()
           || tfCPF.getText().isEmpty()
           || tfMatricula.getText().isEmpty()
           || dtNascimento.getValue() == null;
  }

  @FXML
  private void limparCampos() {
    tfNome.clear();
    tfCPF.clear();
    tfMatricula.clear();
    dtNascimento.setValue(null);
    tfCurso.clear();
    tfDepartamento.clear();
    tfLogin.clear();
    pfSenha.clear();
  }
}