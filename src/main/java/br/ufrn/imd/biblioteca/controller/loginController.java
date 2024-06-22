package br.ufrn.imd.biblioteca.controller;

import java.io.IOException;

import br.ufrn.imd.biblioteca.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class loginController {
  @FXML
  private Label lbInvalida;

  @FXML
  private TextField tfLogin;

  @FXML
  private PasswordField pfSenha;

  @FXML
  private Button btEntrar;

  @FXML
  private void autenticar() throws IOException {
    lbInvalida.setVisible(false);
    if (tfLogin.getText().equals("admin") && pfSenha.getText().equals("admin123")) {
      App.trocarTela("principal");
    } else {
      lbInvalida.setVisible(true);
    }
  }

}
