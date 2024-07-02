# Biblioteca IMD (com GUI)

## Descrição Geral

O código implementa um sistema de gerenciamento de biblioteca que permite a administração de usuários, livros e empréstimos. Ele utiliza o padrão de design Singleton para gerenciar as instâncias dos objetos de dados e persistir as informações em arquivos binários. Além disso, é utilizado o modelo MVC do Spring Boot.

Você pode ver a apresentação das telas do sisctema com a descrição das funcionalidades da interface [aqui](biblioteca_gui.pdf).

## Instruções de uso

Ao iniciar o sistema será exigido o usuário do bibliotecário, que pode ser um bibliotecário cadastrado ou o usuário padrão com as seguintes credenciais: 
```
Usuário: admin
Senha: admin123
```

## Estrutura do Projeto

- **Controller:** Package responsável por intermediar as interações do programa.
- **DAO:** Package para a classe de acesso às informações do programa de forma centralizada, os objetos de acesso a dados (acrônimo do inglês: Data Access Object - DAO).
- **DTO:** Package contendo os objetos de transferência de dados (acrônimo do inglês: Data Transfer Object - DTO), utilizados para transferir dados entre camadas da aplicação (por exemplo, uma lista de usuário do controller para view), podendo fazer a seleção do que será transferido.
- **Model:** Package que contém as classes que representam os dados do sistema, como Livro, Usuario e Emprestimo.
- **Repository:** Package contendo as classes responsáveis por encapsular a lógica de acesso aos dados da aplicação (adicionar, remover, recuperar um dado), separando das regras de negócio.
- **Service:** Package com as classes que implementam as regras de negócio da aplicação e coordenam as operações entre diferentes entidades e objetos da aplicação.
- **Util:** Package contém classes e métodos utilitários que fornecem funcionalidades genéricas e reutilizáveis para toda a aplicação.

## Classes e Funcionalidades

### Model:

#### Usuario
Esta classe é uma classe abstrata que representa um usuário genérico da biblioteca. Ela possui os atributos: nome, CPF, matrícula, data de nascimento e tipo de usuário.

#### Estudante
Representa um estudante que é usuário da biblioteca. Além dos atributos herdados da classe Usuario, possui um atributo específico "curso".

#### Professor
Representa um professor que é usuário da biblioteca. Além dos atributos herdados da classe Usuario, possui um atributo específico "departamento".

#### Bibliotecario
Representa um bibliotecário que é usuário da biblioteca. Além dos atributos herdados da classe Usuario, possui atributos específicos "login" e "senha". Este é o único perfil que tem a permissão para gerenciar a biblioteca, como entrar no sistema e realizar as ações de manipulação de usuários e livros.

#### Livro
Representa um livro na biblioteca. Possui os atributos: título, autor, assunto, ISBN, ano de lançamento e quantidade em estoque.

#### Emprestimo
Representa um empréstimo de um livro por um usuário. Possui os atributos: matrícula, ISBN, data do empréstimo e data de devolução prevista.

#### IValidarClasses
Interface que define o método para validar os atributos das classes que precisam ser verificadas antes do uso (como usuários).

### DAO:

#### BancoDAO
Classe que gerencia a persistência e recuperação de dados dos usuários, livros e empréstimos. A persistência de dados é feita utilizando a serialização Java para salvar e carregar listas de objetos (usuarios.bin, livros.bin, emprestimos.bin).

### Controller:

#### UsuariosController
Classe que gerencia operações relacionadas a tela de usuários.

#### LoginController
Classe que gerencia o acesso ao sistema.

#### LivrosController
Classe que gerencia as operações na tela de livros.

#### InicioController
Classe que gerencia a tela inicial do programa, após o login do bibliotecário.

#### EmprestimosController
Classe que gerencia as operações na tela de empréstimos.

#### CadastrarUsuarioController
Classe que gerencia as operações na tela de cadastro de novos usuários.

#### CadastrarLivroController
Classe que gerencia as operações na tela de cadastro de novos livros.

#### CadastrarEmprestimoController
Classe que gerencia as operações na tela de cadastro de novos empréstimos.

### DTO (usados para transferir informações entre camadas da aplicação):

#### UsuarioDTO
Objeto de transferência de dados para usuários.

#### LivroDTO
Objeto de transferência de dados para livros.

#### EmprestimoDTO
Objeto de transferência de dados para empréstimos.

### Repository (responsável por gerenciar a persistência e recuperação de dados):

#### UsuarioRepository
Repositório responsável os dados relacionados aos usuários.

#### LivroRepository
Repositório responsável os dados relacionados aos livros.

#### EmprestimosRepository
Repositório responsável os dados relacionados aos empréstimos.

### Service:

#### OperacoesUsuarios
Classe responsável por executar as ações relacionadas a usuário, como por exemplo, cadastro, remoção e listagem de usuário.

#### OperacoesLivros
Classe responsável por executar as ações relacionadas a livros, como, por exemplo, pesquisa, adição e remoção de livro.

#### OperacoesEmprestimos
Classe responsável por executar as ações relacionadas a empréstimo, como por exemplo, listar empréstimos ativos ou realizar um empréstimo à um usuário.

### Util:

#### Alerta
Classe que disponibiliza os alertas a serem mostrados na aplicação.

#### FiltroPequisa
Enumeração responsável pela identificação do tipo de pesquisa que o usuário escolher realizar (por matrícula ou por ISBN).

#### Tratamento
Oferece métodos que auxiliam na manipulação e usos dos dados, como converter LocalDate para String e verificar se uma String está contida em outra.

#### Validacao
Oferece um conjunto de métodos genéricos que auxiliam na validação dos dados.

## Funcionalidades do sistema

O usuário com privilégio de bibliotecário poderá:

1. Adicionar novos livros ao acervo.
2. Listar os livros disponíveis no acervo.
3. Pesquisar os livros no acervo segundo o título.
4. Remover um livro do acervo.
5. Realizar um empréstimo de um livro do acervo, a algum usuário cadastrado no sistema.
6. Listar todos os empréstimos ativos no sistema.
7. Listar todos os empréstimos em atraso no sistema.
8. Listar os empréstimos de um usuário específico.
9. Realizar a devolução de um livro ao acervo.
10. Adicionar um novo usuário ao sistema.
11. Listar os usuários atualmente ativos no sistema.
12. Remover algum usuário do sistema.

## Ferramentas Utilizadas

- [JDK, Versão 21.0.3](https://www.oracle.com/br/java/technologies/downloads/#java21)
- [JavaFX, Versão 21.0.3](https://gluonhq.com/products/javafx)
- [Apache Maven 3.9.8](https://maven.apache.org/download.cgi)
- [Scene Builder, Versão 21.0.0](https://github.com/gluonhq/scenebuilder/releases/tag/20.0.0)
- [IntelliJ IDEA, Versão  2024.1.4](https://www.jetbrains.com/pt-br/idea/download)
- [Visual Studio Code, Versão 1.90.2](https://code.visualstudio.com/download)
- [GitHub Desktop, Versão 3.4.1](https://desktop.github.com/download)

## Desenvolvedores

- Hiranilson Andrade dos Santos
- Paulo Bezerra da Silva
