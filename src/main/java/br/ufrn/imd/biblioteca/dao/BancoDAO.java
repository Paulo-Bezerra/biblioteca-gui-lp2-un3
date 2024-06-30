package br.ufrn.imd.biblioteca.dao;

import br.ufrn.imd.biblioteca.repository.*;

import java.io.*;

// Classe responsável por gerenciar o acesso aos repositórios de dados da biblioteca.
public class BancoDAO {
  private LivroRepository LR; // Repositório de livros
  private UsuarioRepository UR; // Repositório de usuários
  private EmprestimoRepository ER; // Repositório de empréstimos

  private static BancoDAO instance; // Repositório de empréstimos

  // Construtor privado que inicializa os repositórios de dados.
  private BancoDAO() {
    LR = new LivroRepository();
    UR = new UsuarioRepository();
    ER = new EmprestimoRepository();
  }

  // (Padrão Singleton) Método estático que retorna a instância única de BancoDAO.
  public static BancoDAO getInstance() {
    if (instance == null) {
      instance = new BancoDAO();
    }
    return instance;
  }

  // Métodos de acesso aos repositórios de dados.
  public LivroRepository getLR() {
    return LR;
  }

  public UsuarioRepository getUR() {
    return UR;
  }

  public EmprestimoRepository getER() {
    return ER;
  }

  // Método para salvar os dados dos repositórios em arquivos binários.
  // Retorna true se os dados foram salvos com sucesso, false caso contrário.
  public boolean salvarDados() {
    try (FileOutputStream arquivoUR = new FileOutputStream(getDados("usuarios"));
         ObjectOutputStream saidaUR = new ObjectOutputStream(arquivoUR);
         FileOutputStream arquivoLR = new FileOutputStream(getDados("livros"));
         ObjectOutputStream saidaLR = new ObjectOutputStream(arquivoLR);
         FileOutputStream arquivoER = new FileOutputStream(getDados("emprestimos"));
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

  // Método para carregar os dados dos arquivos binários para os repositórios.
  // Retorna true se os dados foram carregados com sucesso, false caso contrário.
  public boolean carregarDados() {
    try (FileInputStream arquivoUR = new FileInputStream(getDados("usuarios"));
         ObjectInputStream entradaUR = new ObjectInputStream(arquivoUR);
         FileInputStream arquivoLR = new FileInputStream(getDados("livros"));
         ObjectInputStream entradaLR = new ObjectInputStream(arquivoLR);
         FileInputStream arquivoER = new FileInputStream(getDados("emprestimos"));
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

  // Retorna o caminho do arquivo de dados específico com base no tipo.
  public String getDados(String dados) {
    return "src/main/resources/br/ufrn/imd/biblioteca/data/" + dados + ".bin";
  }
}