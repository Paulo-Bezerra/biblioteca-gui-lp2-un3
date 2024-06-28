package br.ufrn.imd.biblioteca.util;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tratamento {
  // Método para verificar se uma string contém outra, ignorando acentos e capitalização.
  public static boolean contemString(String str, String subStr) {
    return removerAcentos(str).toLowerCase().contains(removerAcentos(subStr).toLowerCase());
  }

  // Método auxiliar para remover acentos de uma string
  public static String removerAcentos(String str) {
    // Normaliza e remove os acentos
    return Normalizer.normalize(str, Normalizer.Form.NFD)
      .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
  }

  public static String dataString(LocalDate date) {
    try {
      return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    } catch (Exception e) {
      return "";
    }
  }
}
