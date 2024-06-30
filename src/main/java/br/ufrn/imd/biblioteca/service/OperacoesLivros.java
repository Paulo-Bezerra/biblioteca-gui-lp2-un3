package br.ufrn.imd.biblioteca.service;

import br.ufrn.imd.biblioteca.dao.BancoDAO;
import br.ufrn.imd.biblioteca.dto.LivroDTO;
import br.ufrn.imd.biblioteca.model.Livro;
import br.ufrn.imd.biblioteca.repository.EmprestimoRepository;
import br.ufrn.imd.biblioteca.repository.LivroRepository;

import java.util.ArrayList;
import java.util.List;

public class OperacoesLivros {
  // Recupera o repostório de livro contida no BancoDAO.
  private static LivroRepository getLR() {
    return BancoDAO.getInstance().getLR();
  }

  private static EmprestimoRepository getER() {
    return BancoDAO.getInstance().getER();
  }

  // Cria, valida e cadastra um livro. Retorna true se bem-sucedido.
  public static boolean cadastrarLivro(String titulo, String autor, String assunto, String isbn, String ano, String estoque) {
    try { // Tennta converter para inteiro, false se não conseguir.
      Livro livro = new Livro(titulo, autor, assunto, isbn, Integer.parseInt(ano), Integer.parseInt(estoque));
      return livro.validar() && getLR().cadastrarLivro(livro);
    } catch (Exception e) {
      return false;
    }
  }

  // Retorna a lista de todos os livros cadastrados.
  public static List<LivroDTO> listarLivros() {
    ArrayList<LivroDTO> livros = new ArrayList<>();
    for (Livro l : getLR().getLivros()) {
      livros.add(new LivroDTO(l.getTitulo(), l.getIsbn()));
    }
    return livros;
  }

  public static boolean removerLivro(String isbn) {
    return isbn != null && getER().quantidadeEmprestimoPorIsbn(isbn) == 0 && getLR().removerLivro(isbn);
  }

  public static int quantidadeLivros() {
    return getLR().quantidadeLivros();
  }

  public static Livro getLivro(String isbn) {
    return getLR().getLivro(isbn);
  }

  public static int quantidadeLivrosDisponivel(String isbn) {
    return getLR().getLivro(isbn).getEstoque() - getER().quantidadeEmprestimoPorIsbn(isbn);

  }
}
