package br.ufrn.imd.biblioteca.util;

import br.ufrn.imd.biblioteca.App;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class Alerta {
  // Define a imagem a ser usada como icone nos alertas.
  private static final Image icone = App.icone;

  // Retorna um alerta do tipo passado.
  private static Alert criarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
    Alert alerta = new Alert(tipo);
    ((Stage) alerta.getDialogPane().getScene().getWindow()).getIcons().add(icone);
    alerta.setTitle(titulo);
    alerta.setHeaderText(null);
    alerta.setContentText(mensagem);
    return alerta;
  }

  // Exibe uma informação com o título e a mensagem fornecidos.
  public static void exibirInformacao(String titulo, String mensagem) {
    criarAlerta(titulo, mensagem, Alert.AlertType.INFORMATION).showAndWait();
  }

  // Exibe um aviso com o título e a mensagem fornecidos.
  public static void exibirAviso(String titulo, String mensagem) {
    criarAlerta(titulo, mensagem, Alert.AlertType.WARNING).showAndWait();
  }

  // Exibe um erro com o título e a mensagem fornecidos.
  public static void exibirErro(String titulo, String mensagem) {
    criarAlerta(titulo, mensagem, Alert.AlertType.ERROR).showAndWait();
  }

  // Exibe uma confirmação. Retorna boolean com a opção do usuário.
  public static boolean exibirConfirmacao(String titulo, String mensagem) {
    Optional<ButtonType> escolha = criarAlerta(titulo, mensagem, Alert.AlertType.CONFIRMATION).showAndWait();
    // Retorna true se o usuário confirmar a ação.
    return escolha.isPresent() && escolha.get() == ButtonType.OK;
  }
}