package br.ufrn.imd.biblioteca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.ufrn.imd.biblioteca.App;
import br.ufrn.imd.biblioteca.dto.UsuarioDTO;
import br.ufrn.imd.biblioteca.service.OperacoesUsuarios;
import br.ufrn.imd.biblioteca.util.Alerta;
import br.ufrn.imd.biblioteca.util.Tratamento;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class UsuariosController {
  // Elementos da interface gráfica.
  @FXML
  private TextField tfBusca;

  @FXML
  private Button btRemover;

  @FXML
  private ListView<UsuarioDTO> lvUsuarios;

  @FXML
  private void initialize() {
    lvUsuarios.getSelectionModel().selectedItemProperty().addListener(
      (observado, antigoValor, novoValor) -> {
        btRemover.setDisable(novoValor == null);
      }
    );
  }

  // Filtra usuários pelo nome digitado no TextField de busca
  @FXML
  private void buscarUsuario() {
    if (tfBusca.getText().trim().isEmpty()) {
      return;
    }
    List<UsuarioDTO> usuarios = new ArrayList<>();
    for (UsuarioDTO usuario : OperacoesUsuarios.listarUsuarios()) {
      if (Tratamento.contemString(usuario.nome(), tfBusca.getText().trim())) {
        usuarios.add(usuario);
      }
    }
    usuarios.sort(Comparator.comparing(UsuarioDTO::nome));
    lvUsuarios.getItems().setAll(usuarios);
  }

  // Lista todos os usuários e exibe na ListView
  @FXML
  private void listarUsarios() {
    List<UsuarioDTO> usuarios = OperacoesUsuarios.listarUsuarios();
    usuarios.sort(Comparator.comparing(UsuarioDTO::nome));
    lvUsuarios.getItems().setAll(usuarios);
  }

  // Métodos para navegação entre telas
  @FXML
  private void cadastrarUsuario() throws IOException {
    App.trocarTela("cadastrar-usuario");
  }

  @FXML
  private void removerUsuario() {
    UsuarioDTO usuario = lvUsuarios.getSelectionModel().getSelectedItem();
    if (usuario != null && Alerta.exibirConfirmacao("Remoção", "Remover: " + usuario + ".")) {
      if (OperacoesUsuarios.removerUsuario(usuario.matricula())) {
        lvUsuarios.getItems().remove(usuario);
        Alerta.exibirInformacao("Remoção", "Usuário removido com sucesso!");
      } else {
        Alerta.exibirErro("Remoção", "Não foi possivel remover o usuário!");
      }
    }
  }

  @FXML
  private void inicio() throws IOException {
    App.trocarTela("inicio");
  }

  @FXML
  private void livros() throws IOException {
    App.trocarTela("livros");
  }

  @FXML
  private void emprestimos() throws IOException {
    App.trocarTela("emprestimos");
  }

  @FXML
  private void sair() throws IOException {
    App.trocarTela("login");
  }
}