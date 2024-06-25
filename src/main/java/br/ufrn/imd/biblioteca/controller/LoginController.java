package br.ufrn.imd.biblioteca.controller;

import java.io.IOException;

import br.ufrn.imd.biblioteca.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

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
    lbInvalida.setVisible(false);

    // Verifica se o login e senha correspondem ao esperado.
    if (tfLogin.getText().equals("admin") && pfSenha.getText().equals("admin123")) {
      App.trocarTela("inicio"); // Troca para a tela inicial se a autenticação for bem-sucedida.
    } else {
      lbInvalida.setVisible(true); // Exibe mensagem de erro se a autenticação falhar.
    }
  }
}