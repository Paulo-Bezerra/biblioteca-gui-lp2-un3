package br.ufrn.imd.biblioteca.util;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Alerta {
  public static void exibirAlerta(String titulo, String mensagem) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(
        new Image(Objects.requireNonNull(Alerta.class.getResourceAsStream("/br/ufrn/imd/biblioteca/images/App-256.png")))
    );
    alert.setTitle(titulo);
    alert.setHeaderText(null);
    alert.setContentText(mensagem);
    alert.showAndWait();
  }
}
