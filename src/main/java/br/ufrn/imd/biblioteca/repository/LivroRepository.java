package br.ufrn.imd.biblioteca.repository;

import br.ufrn.imd.biblioteca.model.Livro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LivroRepository implements Serializable {
  private final HashMap<String, Livro> isbn_LR;;

  public LivroRepository() {
    this.isbn_LR = new HashMap<>();
  }

  public LivroRepository(LivroRepository livroRepository) {
    this.isbn_LR = new HashMap<>(livroRepository.isbn_LR);
  }

  public boolean adicionarLivro(Livro livro) {
    if (isbn_LR.containsKey(livro.getIsbn())) {
      return false;
    }
    isbn_LR.put(livro.getIsbn(), livro);
    return true;
  }

  public boolean removerLivro(String isbn) {
    if (!isbn_LR.containsKey(isbn)) {
      return false;
    }
    isbn_LR.remove(isbn);
    return true;
  }

  public List<Livro> getLivros() {
    return new ArrayList<>(isbn_LR.values());
  }

  public Livro getLivroPorIsbn(String isbn) {
    if (!existeLivro(isbn)) {
      return null;
    }
    return new Livro(isbn_LR.get(isbn));
  }

  public boolean existeLivro(String isbn) {
    return isbn_LR.containsKey(isbn);
  }
}
