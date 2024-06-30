package br.ufrn.imd.biblioteca;

import br.ufrn.imd.biblioteca.dao.BancoDAO;
import br.ufrn.imd.biblioteca.service.OperacoesLivros;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
  // Recupera o ícone padrão.
  public static final Image icone = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/App-64x64.png")));
  // Declara um scene para manipular a troca de telas.
  private static Scene scene;


  // Inicializa o programa e exibe a tela de login.
  @SuppressWarnings("exports")
  @Override
  public void start(Stage stage) throws IOException {
    // Define dimensões mínimas, título e ícone do programa.
    stage.setMinWidth(450);
    stage.setMinHeight(560);
    stage.setTitle("Biblioteca do IMD");
    stage.getIcons().add(icone);

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
    // Dados para testes.
//    OperacoesLivros.cadastrarLivro("Dom Casmurro", "Machado de Assis", "Literatura Brasileira", "8501050372", "1899", "10");
//    OperacoesLivros.cadastrarLivro("Grande Sertão: Veredas", "João Guimarães Rosa", "Literatura Brasileira", "8532500872", "1956", "8");
//    OperacoesLivros.cadastrarLivro("Macunaíma", "Mário de Andrade", "Literatura Brasileira", "8532503385", "1928", "5");
//    OperacoesLivros.cadastrarLivro("Gabriela, Cravo e Canela", "Jorge Amado", "Literatura Brasileira", "8501009134", "1958", "12");
//    OperacoesLivros.cadastrarLivro("Memórias Póstumas de Brás Cubas", "Machado de Assis", "Literatura Brasileira", "8572324500", "1881", "7");
//    OperacoesLivros.cadastrarLivro("O Cortiço", "Aluísio Azevedo", "Literatura Brasileira", "8508136870", "1890", "6");
//
//    OperacoesLivros.cadastrarLivro("1984", "George Orwell", "Ficção Distópica", "0451524936", "1949", "15");
//    OperacoesLivros.cadastrarLivro("Matar um Rouxinol", "Harper Lee", "Ficção", "0061120085", "1960", "20");
//    OperacoesLivros.cadastrarLivro("Harry Potter e a Pedra Filosofal", "J.K. Rowling", "Fantasia", "0747532745", "1997", "25");
//    OperacoesLivros.cadastrarLivro("Cem Anos de Solidão", "Gabriel García Márquez", "Realismo Mágico", "0060883286", "1967","18");
//    OperacoesLivros.cadastrarLivro("O Alquimista", "Paulo Coelho", "Ficção Espiritual", "0061122416", "1988", "30");
//    OperacoesLivros.cadastrarLivro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", "Literatura Infantil", "8572326023", "1943", "22");

    // Inicializa a aplicação.
    launch();

    // Salva os dados ao fechar o programa.
    BancoDAO.getInstance().salvarDados();
  }
}