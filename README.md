# LinkeTinder (BACKEND) - Autor: Daniel Costa - ACZG 6.0

Linketinder é uma aplicação Groovy que permite a interação entre candidatos e empresas, e promete revolucionar o método como são realizadas as contratações de novos colaboradores, esta aplicação está sendo desenvolvida como um dos projetos do ACZG 6.0.

## Modelagem de Dados e SQL

<details><summary>Clique para ler a Documentação</summary>

## Tecnologias Utilizadas

- **POSTGRESQL**: Banco de dados utilizado para comportar os dados (Versão: 17.0)
- **PGADMIN**: SGBD (Sistema de gerenciamento de Banco de dados do Postgres). Utilizei principalmente para elaboração do Diagrama de Entidade e Relacionamento, e para execução dos scripts SQL

# Modelagem de Dados Linketinder

A partir dessa estrutura e da implementação em código, o sistema será capaz de permitir que empresas publiquem vagas, candidatos possam curtir essas vagas e empresas possam curtir candidatos diretamente. Segue abaixo uma explicação de cada entidade e suas relações.

## Entidades Principais
**Candidatos (candidatos)**

Armazena as informações pessoais dos candidatos, como nome, sobrenome, email, telefone, CPF, formação e uma breve descrição.
Os candidatos podem curtir vagas específicas, mas não podem curtir empresas diretamente.

**Empresas (empresas)**

Armazena as informações das empresas, como nome, CNPJ, email, endereço e uma descrição.
As empresas podem curtir diretamente os perfis de candidatos, demonstrando interesse.

**Vagas (vagas)**

Representa as oportunidades de emprego criadas pelas empresas, com detalhes como nome da vaga, descrição e local.
Os candidatos podem curtir as vagas, e essa interação é armazenada na tabela de curtidas.

## Relacionamentos

**Candidatos - Competências (candidato_competencias)**

Relação de muitos para muitos (N) entre candidatos e competências. Cada candidato pode ter várias competências, e uma competência pode ser compartilhada por vários candidatos.

**Vagas - Competências (vaga_competencia)**

Relação de muitos para muitos (N) entre vagas e competências. Cada vaga pode requerer várias competências, e uma competência pode ser exigida por várias vagas.

**Curtidas de Candidatos em Vagas (curtida_candidato_vaga)**

Relaciona candidatos e vagas em uma relação de muitos para muitos (N).
Armazena as curtidas feitas por candidatos em vagas específicas, sem qualquer interação direta com as empresas.

**Curtidas de Empresas em Candidatos (curtida_empresa_candidato)**

Relaciona empresas e candidatos em uma relação de muitos para muitos (N).
As empresas podem visualizar e curtir os candidatos diretamente, demonstrando interesse por perfis específicos.

**Matches (matches)**

Armazena os matches entre empresas e candidatos, quando ambos demonstram interesse.
Um match ocorre quando um candidato curte uma vaga e a empresa responsável pela vaga curte o candidato.

## Fluxo de Curtidas

**Candidatos curtem vagas:** Os candidatos não interagem diretamente com as empresas. Eles podem curtir apenas as vagas publicadas pelas empresas.

**Empresas curtem candidatos:** As empresas têm a capacidade de curtir diretamente o perfil dos candidatos, sem a necessidade de uma interação inicial por parte do candidato.

## Minhas considerações

Esta modelagem permite uma dinâmica de match semelhante a aplicativos de relacionamento (No caso especificamente o Tinder), onde tanto candidatos quanto empresas podem demonstrar interesse uns nos outros.

A estrutura de curtidas mediada pelas vagas permite que candidatos interajam apenas com as oportunidades de emprego, enquanto as empresas podem interagir diretamente com os perfis de candidatos.
</details>

- **Link:** [Clique aqui para baixar o arquivo SQL](https://github.com/danielcostadev/linketinder-backend/raw/master/linketinder-db.sql)

## Diagrama do Banco de Dados

![Diagrama de Entidade e Relacionamento do Linketinder](linketinder-DER.png)

==========================================================

# Versão Groovy (BackEnd)

## Funcionalidades

- **CRUD Completo**: Capaciade de Criar, Listar, Atualizar e Deletar (Empresas, Candidatos, Vagas e competências)
- **Validação de Dados**: Entrada de dados validada com regex

## Tecnologias Utilizadas

- **Groovy**: Linguagem de programação principal utilizada no desenvolvimento do projeto.
- **Gradle**: Project Builder
- **Docker**: Containers Docker para O PostgreSQL e o PGADMIN4
- **PostgreSQL**: Banco de Dados utilizado no projeto
- **PgAdmin4**: SGDB utilizado durante o desenvolvimento do projeto
- **Git e GitHub**: Para versionamento e armazenamento do projeto
- **JDK 1.8**: Versão do Java Development Kit utilizada como base para o projeto Groovy.
- **IntelliJ IDEA**: Ambiente de Desenvolvimento Integrado (IDE) utilizado.

## Minhas breves considerações

Com a evolução do projeto Linketinder, novos desafios surgiram, especialmente na implementação de um banco de dados para a persistência das informações. Como o projeto exigia maior organização e estrutura, optei por separar o Backend do Frontend, permitindo que ambos evoluíssem de forma independente. Essa abordagem melhora a modularidade e facilita o gerenciamento de cada parte do sistema, o que é crucial para o crescimento do projeto. Com um banco de dados robusto e eficiente em mente, decidi usar o PostgreSQL para armazenar as informações, e o pgAdmin como interface de gerenciamento, que facilita a interação com o banco de dados.

Para garantir que o ambiente de banco de dados fosse replicável e isolado do sistema principal, utilizei o Docker para criar containers tanto do PostgreSQL quanto do pgAdmin. O Docker simplifica a criação de ambientes consistentes, permitindo que eu configure e gerencie o banco de dados sem a necessidade de instalá-lo diretamente na máquina local. Com o PostgreSQL rodando em um container, o banco de dados pode ser facilmente acessado e configurado através do pgAdmin, que também está em outro container, oferecendo uma interface gráfica amigável para gerenciamento de tabelas, visualização de dados e execução de queries SQL.

Além disso, essa nova versão reestruturada do projeto foi automatizada com o uso do Gradle, uma poderosa ferramenta de automação de builds. O Gradle não apenas facilita o processo de compilação e integração contínua, mas também permite a inclusão de dependências e tarefas de forma eficiente, tornando o desenvolvimento mais fluido e ágil. Com Docker gerenciando os containers do PostgreSQL e do pgAdmin, e o Gradle cuidando da automatização do build, o ambiente de desenvolvimento ficou mais organizado, replicável e preparado para novos desafios e expansões.








## COMO FUNCIONA?
### Tela Principal
- Ao executar a aplicação o usuário é apresentado a um menu multiplo
- Onde é possível escolher diversas ações para cada tipo de entidade
### Cadastrar Candidato/Empresa/Vagas
- Na opção de cadastro o usuário preenche os dados que serão encapsulados no objeto em questão e posteriormente enviadas ao BD
- Durante o preenchimento do formulário são realizadas validações quanto a integridade dos dados. (A verificação dos campos de acordo com o tipo será incluida em versões futuras)
- Todas as entradas de dados passam por esse rigoroso sistema de validações em regex
### Listar Candidatos/Empresas/Vagas
- AS informações que o usário vê são recuperadas do banco de dados.

## Estrutura do Projeto

### O projeto está organizado da seguinte forma:

- **Controller**: Contém as classes responsáveis por receber as entradas do usuário e fazer a interação entre a camada view e camada service
- **DAO**: Contém as classes responsáveis por manutenção e manipulação dos dados no banco de dados.
- **Model**: Define as classes principais do domínio, como `Pessoa, Canditado e Empresa`.
- **Service**: Define as classes responsáveis por gerir a lógica principal da aplicação e a comunicação com a camada DAO
- **View**: Contém a estrutura principal de exibição do Menu


## Como Executar

### Pré-requisitos

- Groovy 4.0.14 ou superior instalado.
- IDE como IntelliJ IDEA (opcional, mas recomendado).

### Passos

1. Clone o repositório:

   ```bash
   git clone https://github.com/danielcostadev/Linketinder-Backend.git

2. Navegue até a pasta do projeto

   ```bash
   cd Linketinder-Backend/

3. Compile o projeto

   ```bash
   groovyc -d build src/model/*.groovy src/view/*.groovy src/controller/*.groovy src/repository/*.groovy

4. Execute o projeto

   ```bash
   java -cp build Main

### Download do arquivo JAR v3.0.0

---

- **Link:** [Clique aqui para baixar](https://github.com/danielcostadev/linketinder-backend/raw/master/Linketinder-Project.jar)
- Após baixar o arquivo RAR, extraia escolhendo a opção "Extrair aqui".
- Com auxílio do seu terminal navegue até a pasta que foi extraída.
- Para executar a aplicação utilize o comando abaixo:

   ```bash
  java -jar Linketinder-Backend.jar

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença

- **MIT LICENSE:** [Ver licença](https://github.com/danielcostadev/linketinder-backend/blob/master/LICENSE)


## Contato

Para maiores informações ou dúvidas, entre em contato:

- **Nome:** Daniel Costa
- **LinkedIn:** [DanielCostaDev](https://www.linkedin.com/in/danielcostadev)
