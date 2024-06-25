package br.ufrn.imd.biblioteca.util;

public class Validacao {
  // Retorna true se a string contiver apenas números.
  public static boolean strNumerica(String str) {
    return str.matches("\\d+");
  }
}