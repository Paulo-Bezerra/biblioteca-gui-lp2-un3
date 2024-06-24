package br.ufrn.imd.biblioteca.util;

public class Validacao {
  // Método para verificar se uma string contém apenas números
  public static boolean isNumeric(String str) {
    return str.matches("\\d+");
  }
}
