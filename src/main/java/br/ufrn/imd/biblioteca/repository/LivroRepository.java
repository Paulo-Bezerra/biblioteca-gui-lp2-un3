package br.ufrn.imd.biblioteca.repository;

import br.ufrn.imd.biblioteca.model.Livro;
import br.ufrn.imd.biblioteca.model.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LivroRepository implements Serializable {
  // Armazena os livros cadastrados, usando seus ISBN's como chave.
  private final HashMap<String, Livro> isbn_LR;

  // Construtor padrão que inicializa isbn_LR.
  public LivroRepository() {
    this.isbn_LR = new HashMap<>();
  }

  // Construtor de cópia de dados de outro LivroRepository.
  public LivroRepository(LivroRepository livroRepository) {
    this.isbn_LR = new HashMap<>(livroRepository.isbn_LR);
  }

  // Cadastra um livro se o ISBN ainda não estiver registrado.
  // Retorna true se bem-sucedido.
  public boolean cadastrarLivro(Livro livro) {
    if (existeLivro(livro)) {
      return false;
    }
    isbn_LR.put(livro.getIsbn(), livro);
    return true;
  }

  // Remove um livro pelo ISBN. Retorna true se bem-sucedido.
  public boolean removerLivro(String isbn) {
    return isbn_LR.remove(isbn) != null;
  }

  // Atualiza o livro associado ao ISBN do livro fornecido.
  public boolean atualizarLivro(Livro livro) {
    if (!existeLivro(livro)) {
      return false;
    }
    isbn_LR.put(livro.getIsbn(), livro);
    return true;
  }

  // Retorna a lista de todos os livros cadastrados.
  public List<Livro> getLivros() {
    return new ArrayList<>(isbn_LR.values());
  }

  // Retorna um livro pelo ISBN dele.
  public Livro getLivro(String isbn) {
    if (!existeLivro(isbn)) {
      return null;
    }
    return new Livro(isbn_LR.get(isbn));
  }

  // Verificar se existe o livro fornecido.
  public boolean existeLivro(Livro livro) {
    return existeLivro(livro.getIsbn());
  }
  // Verifica se existe um livro com o ISBN fornecido.
  public boolean existeLivro(String isbn) {
    return isbn_LR.containsKey(isbn);
  }

  // Retorna o número de livros cadastrados.
  public int quantidadeLivros() {
    return isbn_LR.size();
  }
}