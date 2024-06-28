package br.ufrn.imd.biblioteca.controller;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.service.OperacoesUsuarios;
import br.ufrn.imd.biblioteca.util.Alerta;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CadastrarUsuarioController {
  // Elementos da interface gráfica.
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

  // Inicializa  o monitoramento para mostrar os campos de acordo com o usuário selecionado do RadioButton.
  @FXML
  private void initialize() {
    TipoUsuario.selectedToggleProperty().addListener(
      (objObservado, valorAntigo, valorNovo) -> atualizarVisibilidade(valorNovo)
    );

    // Caso precise verificar o radiobutton assim que entrar na tela.
    atualizarVisibilidade(TipoUsuario.getSelectedToggle());

    dtNascimento.focusedProperty().addListener(
      (objObservado, valorAntigo, valorNovo) -> {
        if (!valorNovo) { // Se o foco foi perdido.
          String dataString = dtNascimento.getEditor().getText();
          try {
            LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            // Se chegou aqui, a data é válida.
          } catch (DateTimeParseException e) {
            // Se houve erro ao converter a data limpa o DataPicker.
            dtNascimento.getEditor().clear();
            // Retorna no terminal o formato aceito.
            System.out.println("Data inválida: " + dataString + "\n Usar formato: \"dd/MM/yyyy\"");
          }
        }
      }
    );
  }

  // Atualiza a visibilidade dos campos conforme o RadioButton selecionado (Estudante, Professor, Bibliotecário).
  @FXML
  private void atualizarVisibilidade(Toggle opcaoSelecionada) {
    ctEstudante.setVisible(opcaoSelecionada.equals(rbEstudante));
    ctProfessor.setVisible(opcaoSelecionada.equals(rbProfessor));
    ctBibliotecario.setVisible(opcaoSelecionada.equals(rbBibliotecario));
  }

  // Método para cadastrar um estudante.
  @FXML
  private void cadastraEstudante() {
    if (camposUsuarioVazios() || tfCurso.getText().isEmpty()) {
      Alerta.exibirAviso("Cadastro", "Preencha todos os campos!");
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
      Alerta.exibirInformacao("Cadastro", "Estundante cadastrado com sucesso!");

      // caso fique melhor limpar os campos depos de cadastrar.
      // limparCampos();
    } else {
      Alerta.exibirErro("Cadastro", "Não foi possivel cadastrar o estudante!");
    }
  }

  // Método para cadastrar um professor.
  @FXML
  private void cadastrarProfessor() {
    if (camposUsuarioVazios() || tfDepartamento.getText().isEmpty()) {
      Alerta.exibirAviso("Cadastro", "Preencha todos os campos!");
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
      Alerta.exibirInformacao("Cadastro", "Professor cadastrado com sucesso!");

      // caso fique melhor limpar os campos depos de cadastrar.
      // limparCampos();
    } else {
      Alerta.exibirErro("Cadastro", "Não foi possivel cadastrar o professor!");
    }
  }

  // Método para cadastrar um bibliotecário.
  @FXML
  private void cadastrarBiblitecario() {
    if (camposUsuarioVazios() || tfLogin.getText().isEmpty() || pfSenha.getText().isEmpty()) {
      Alerta.exibirAviso("Cadastro", "Preencha todos os campos!");
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
      Alerta.exibirInformacao("Cadastro", "Bibliotecário cadastrado com sucesso!");

      // caso fique melhor limpar os campos depos de cadastrar.
      // limparCampos();
    } else {
      Alerta.exibirErro("Cadastro", "Não foi possivel cadastrar o bibliotecário!");
    }
  }

  // Verifica se há campos de usuário vazios.
  @FXML
  private boolean camposUsuarioVazios() {
    return tfNome.getText().isEmpty()
           || tfCPF.getText().isEmpty()
           || tfMatricula.getText().isEmpty()
           || dtNascimento.getValue() == null;
  }

  // Volta para a tela de usuários.
  @FXML
  private void voltar() throws IOException {
    App.trocarTela("usuarios");
  }

  // Limpa todos os campos de entrada
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