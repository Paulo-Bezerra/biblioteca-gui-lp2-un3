package br.ufrn.imd.biblioteca.controller;

import java.io.IOException;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.model.Usuario;
import br.ufrn.imd.biblioteca.service.OperacoesUsuarios;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyCode.ENTER;

public class LoginController {
  // Elementos da interface gráfica.
  @FXML
  private Label lbInvalida;

  @FXML
  private TextField tfLogin;

  @FXML
  private PasswordField pfSenha;

  // Método chamado ao tentar autenticar

  @FXML
  private void autenticar() throws IOException {
    if (tfLogin.getText().isEmpty() && pfSenha.getText().isEmpty()) {
      lbInvalida.setText("Preencha todos os campos!");
      return;
    }

    if (tfLogin.getText().isEmpty()) {
      lbInvalida.setText("Preencha o campo de usuário!");
      return;
    }

    if (pfSenha.getText().isEmpty()) {
      lbInvalida.setText("Preencha o campo de senha!");
      return;
    }

    // Verifica se o login e senha correspondem ao esperado.
    if (OperacoesUsuarios.autenticar(tfLogin.getText(), pfSenha.getText())) {
      App.trocarTela("inicio"); // Troca para a tela inicial se a autenticação for bem-sucedida.
    } else {
      lbInvalida.setText("Credenciais inválidas."); // Exibe mensagem de erro se a autenticação falhar.
    }
  }

  @FXML
  private void autenticarComEnter(KeyEvent tecla) throws IOException {
    if (tecla.getCode() == ENTER) autenticar();
  }
}