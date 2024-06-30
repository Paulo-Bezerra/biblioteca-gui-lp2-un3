package br.ufrn.imd.biblioteca.util;

public enum FiltroPesquisa {
  MATRICULA(1, "matrícula"),
  ISBN(2, "ISBN");

  private final int valor;
  private final String descricao;

  FiltroPesquisa(int valor, String descricao) {
    this.valor = valor;
    this.descricao = descricao;
  }

  public int getValor() {
    return valor;
  }

  public String getDescricao() {
    return descricao;
  }
}