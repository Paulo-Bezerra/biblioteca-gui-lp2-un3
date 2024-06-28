package br.ufrn.imd.biblioteca.service;

import br.ufrn.imd.biblioteca.dao.BancoDAO;
import br.ufrn.imd.biblioteca.dto.LivroDTO;
import br.ufrn.imd.biblioteca.model.Livro;
import br.ufrn.imd.biblioteca.repository.LivroRepository;
import br.ufrn.imd.biblioteca.util.Validacao;

import java.util.ArrayList;
import java.util.List;

public class OperacoesLivros {
  // Recupera o repostório de livro contida no BancoDAO.
  private static LivroRepository getLR() {
    return BancoDAO.getInstance().getLR();
  }

  // Cria, valida e cadastra um livro. Retorna true se bem-sucedido.
  public static boolean cadastrarLivro(String titulo, String autor, String assunto, String isbn,  String ano, String estoque) {
    if (!Validacao.strNumerica(ano) || !Validacao.strNumerica(estoque)) {
      return false;
    }
    Livro livro = new Livro(titulo, autor, assunto, isbn, Integer.parseInt(ano), Integer.parseInt(estoque));
    return livro.validar() && getLR().cadastrarLivro(livro);
  }

  // Retorna a lista de todos os usuários cadastrados.
  public static List<LivroDTO> listarLivro() {
    ArrayList<LivroDTO> livros = new ArrayList<>();
    for (Livro l : getLR().getLivros()) {
      livros.add(new LivroDTO(l.getTitulo(), l.getIsbn()));
    }
    return livros;
  }

  public static boolean removerLivro(String isbn) {
    return isbn != null && getLR().removerLivro(isbn);
  }

  public static int quantidadeUsuarios() {
    return getLR().quantidadeLivros();
  }

  public static Livro getUsuario(String matricula) {
    return getLR().getLivro(matricula);
  }
}
