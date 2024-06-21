package br.ufrn.imd.biblioteca.dao;

import br.ufrn.imd.biblioteca.repository.*;

import java.io.*;


public class BancoDAO {
  private LivroRepository LR;
  private UsuarioRepository UR;
  private EmprestimoRepository ER;

  private static BancoDAO instance;

  private BancoDAO() {
    LR = new LivroRepository();
    UR = new UsuarioRepository();
    ER = new EmprestimoRepository();
  }

  public static BancoDAO getInstance() {
    if (instance == null) {
      instance = new BancoDAO();
    }
    return instance;
  }

  public LivroRepository getLR() {
    return LR;
  }

  public UsuarioRepository getUR() {
    return UR;
  }

  public EmprestimoRepository getER() {
    return ER;
  }

  public boolean salvarDados() {
    try (FileOutputStream arquivoUR = new FileOutputStream(getDados("ur"));
         ObjectOutputStream saidaUR = new ObjectOutputStream(arquivoUR);
         FileOutputStream arquivoLR = new FileOutputStream(getDados("lr"));
         ObjectOutputStream saidaLR = new ObjectOutputStream(arquivoLR);
         FileOutputStream arquivoER = new FileOutputStream(getDados("er"));
         ObjectOutputStream saidaER = new ObjectOutputStream(arquivoER)
    ) {
      saidaUR.writeObject(UR);
      saidaUR.close();

      saidaLR.writeObject(LR);
      saidaLR.close();

      saidaER.writeObject(ER);
      saidaER.close();
      return true;
    } catch (Exception e) {
      System.out.println("Houve um erro salvar os dados.");
      System.out.println(e.getMessage());
      return false;
    }
  }

  public boolean carregarDados() {
    try (FileInputStream arquivoUR = new FileInputStream(getDados("ur"));
         ObjectInputStream entradaUR = new ObjectInputStream(arquivoUR);
         FileInputStream arquivoLR = new FileInputStream(getDados("lr"));
         ObjectInputStream entradaLR = new ObjectInputStream(arquivoLR);
         FileInputStream arquivoER = new FileInputStream(getDados("er"));
         ObjectInputStream entradaER = new ObjectInputStream(arquivoER)
    ) {
      this.UR = new UsuarioRepository((UsuarioRepository) entradaUR.readObject());
      entradaUR.close();

      this.LR = new LivroRepository((LivroRepository) entradaLR.readObject());
      entradaLR.close();

      this.ER = new EmprestimoRepository((EmprestimoRepository) entradaER.readObject());
      entradaER.close();

      return true;
    } catch (FileNotFoundException e) {
      System.out.println("Erro ao carregar um dos arquivos de dados.\n" +
          "(Ignore caso for o primeiro uso).");
      return false;
    } catch (ClassNotFoundException e) {
      System.out.println("Houve um erro ao copiar os dados dos arquivos para as classes.");
      System.out.println(e.getMessage());
      return false;
    } catch (IOException e) {
      System.out.println("Houve um erro carregar os arquivos de dados.");
      System.out.println(e.getMessage());
      return false;
    }
  }

  public String getDados(String dados) {
    return "src/main/resources/br/ufrn/imd/biblioteca/data/salvo_" + dados + ".bin";
  }
}
