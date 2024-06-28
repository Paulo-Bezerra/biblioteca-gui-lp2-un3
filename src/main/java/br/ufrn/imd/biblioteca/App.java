package br.ufrn.imd.biblioteca;

import br.ufrn.imd.biblioteca.dao.BancoDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
  // Declara um scene para manipular a troca de telas.
  private static Scene scene;

  // Inicializa o programa e exibe a tela de login.
  @SuppressWarnings("exports")
  @Override
  public void start(Stage stage) throws IOException {
    // Define dimensões mínimas, título e ícone do programa.
    stage.setMinWidth(450);
    stage.setMinHeight(550);
    stage.setTitle("Biblioteca do IMD");
    stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/App-256.png"))));

    // Inicializa o programa com a tela de login.
    scene = new Scene(carregarFXML("login"));
    stage.setScene(scene);
    stage.show();
  }

  // Troca a tela atual. Recebe nome do arquivo na pasta view.
  public static void trocarTela(String fxml) throws IOException {
    scene.setRoot(carregarFXML(fxml));
  }

  // Carrega um arquivo FXML da pasta view.
  private static Parent carregarFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/" + fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void main(String[] args) {
    // Carrega os dados ao iniciar o programa.
    BancoDAO.getInstance().carregarDados();

    // Inicializa a aplicação.
    launch();

    // Salva os dados ao fechar o programa.
    BancoDAO.getInstance().salvarDados();
  }
}