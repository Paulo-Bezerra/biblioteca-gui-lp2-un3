package br.ufrn.imd.biblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

  private static Scene scene;

  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(carregarFXML("login"));
    stage.setMinWidth(380);
    stage.setMinHeight(390);
    stage.setTitle("Biblioteca do IMD");
    stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/App-256.png"))));
    stage.setScene(scene);
    stage.show();
  }

  public static void trocarTela(String fxml) throws IOException {
    scene.setRoot(carregarFXML(fxml));
  }

  private static Parent carregarFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/" + fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void main(String[] args) {
    launch();
  }
}