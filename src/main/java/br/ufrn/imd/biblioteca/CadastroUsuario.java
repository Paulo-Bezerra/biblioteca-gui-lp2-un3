package br.ufrn.imd.biblioteca;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CadastroUsuario extends Application {

  @Override
  public void start(Stage primaryStage) {
    VBox root = new VBox(10);

    Label nameLabel = new Label("Nome:");
    TextField nameField = new TextField();

    Label cpfLabel = new Label("CPF:");
    TextField cpfField = new TextField();

    Label birthDateLabel = new Label("Data de nascimento:");
    DatePicker birthDatePicker = new DatePicker();

    ToggleGroup userTypeGroup = new ToggleGroup();

    RadioButton studentRadioButton = new RadioButton("Estudante");
    studentRadioButton.setToggleGroup(userTypeGroup);

    RadioButton professorRadioButton = new RadioButton("Professor");
    professorRadioButton.setToggleGroup(userTypeGroup);

    RadioButton librarianRadioButton = new RadioButton("Bibliotecário");
    librarianRadioButton.setToggleGroup(userTypeGroup);

    VBox additionalInfoBox = new VBox(10);

    userTypeGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
      additionalInfoBox.getChildren().clear();
      if (newValue == studentRadioButton) {
        Label courseLabel = new Label("Curso:");
        TextField courseField = new TextField();
        additionalInfoBox.getChildren().addAll(courseLabel, courseField);
      } else if (newValue == professorRadioButton) {
        Label departmentLabel = new Label("Departamento:");
        TextField departmentField = new TextField();
        additionalInfoBox.getChildren().addAll(departmentLabel, departmentField);
      } else if (newValue == librarianRadioButton) {
        Label loginLabel = new Label("Login:");
        TextField loginField = new TextField();
        additionalInfoBox.getChildren().addAll(loginLabel, loginField);
      }
    });

    Button registerButton = new Button("CADASTRAR");

    root.getChildren().addAll(nameLabel, nameField, cpfLabel, cpfField, birthDateLabel, birthDatePicker,
        studentRadioButton, professorRadioButton, librarianRadioButton, additionalInfoBox, registerButton);

    Scene scene = new Scene(root, 400, 400);
    primaryStage.setTitle("Cadastro de Usuário");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}