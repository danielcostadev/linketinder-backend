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

- **Adicionar Empresa**: Capacidade de criar empresas
- **Adicionar Candidato**: Capacidade de criar candidatos
- **Listar Empresas**: Listagem de tarefas
- **Listar Candidatos**: Listagem de candidatos
- **Validação de Dados**: Entrada de dados validada para garantir a integridade das informações.

## Tecnologias Utilizadas

- **Groovy**: Linguagem de programação principal utilizada no desenvolvimento do projeto.
- **Git e GitHub**: Para versionamento e armazenamento do projeto
- **JDK 1.8**: Versão do Java Development Kit utilizada como base para o projeto Groovy.
- **IntelliJ IDEA**: Ambiente de Desenvolvimento Integrado (IDE) utilizado.

## Minhas breves considerações

> O projeto LinkeTinder traz consigo uma ideia bastante promissora quando a contração de novos colaboradores, através da "Contratação às cegas" [Funcionalidade que será incluida no decorrer do desenvolimento].
> 
> No desevolvimento desse projeto pude aprimorar ainda mais meus conhecimentos em POO e organização de projeto seguindo os ritos básicos do padrão MVC, também pude melhorar a escrita do código deixando de lado
> antigas práticas, no que diz respeito ao uso desnecessário do modificador 'static', comentários redundantes e nomenclatura com prefixos repetitivos. Com essa mudança de hábito pude perceber o quanto o código
> se tornou manutenível e escalável.
>
> Nesse projeto também utilizei a abordagem de validar os dados passados pelo usuário, onde é possível evitar exceções em tempo de execução.
> Dessa vez utilizei o validador dentro de uma classe 'Trait' que serve como um tipo de interface ou classe abstrata, porém mais robusta e poderosa.
>
> Como mais uma vez não houve a exigência de implementação de um front-end robusto, toda a aplicação é exibida através de um menu simples, com interações para o usuário.
>

## COMO FUNCIONA?
### Tela Principal
- Ao executar a aplicação o usuário é apresentado a um modesto Menu interativo
- Onde é possível escolher ações como Cadastrar e Listar Candidatos ou Empresas
### Cadastrar Candidato/Empresa
- Na opção de cadastrar o usuário preenche um breve formulário.
- Durante o preenchimento do formulário são realizadas validações quanto a integridade dos dados. (A verificação dos campos de acordo com o tipo será incluida em versões futuras)
- Todas as entradas de dados passam por esse rigoroso sistema de validações.
- Essas validações asseguram que não sejam lançadas exceções durante a execução da aplicação
- Após o preenchimento da última informação, os dados são armazenados em uma lista.
- Essa lista não é a lista original, para preservar os dados e garantir a persistência de pelo menos 5 cadastros tanto para candidatos quanto para empresas, o cadastro não manipula diretamente a lista original
- Foi criado um clone da lista, e os novos cadastros são inseridos nesse clone.
### Listar Candidatos/Empresas
- São exibido os candidatos ou empresas cadastradas que por sua vez são recuperadas de uma lista que foi previamente populada
- AS informações que o usário vê são da lista dinâmica (Um clone da lista original + novos cadastros)

## Estrutura do Projeto

### O projeto está organizado da seguinte forma:

- **Controller**: Contém as classes e os métodos responsáveis por gerenciar as operações.
- **Repository**: Contém as classes responsáveis por manutenção e manipulação dos dados.
- **Model**: Define as classes principais do domínio, como `Pessoa, Canditado e Empresa`.
- **View**: Contém a estrutura principal de exibição do Menu e tarefas.


## Como Executar

### Pré-requisitos

- Groovy 4.0.14 ou superior instalado.
- IDE como IntelliJ IDEA (opcional, mas recomendado).

### Passos

1. Clone o repositório:

   ```bash
   git clone https://github.com/danielcostadev/Linketinder-Project.git

2. Navegue até a pasta do projeto

   ```bash
   cd Linketinder-Project/

3. Compile o projeto

   ```bash
   groovyc -d build src/model/*.groovy src/view/*.groovy src/controller/*.groovy src/repository/*.groovy

4. Execute o projeto

   ```bash
   java -cp build Main

### Download do arquivo JAR v1.0.0

---

- **Link:** [Clique aqui para baixar](https://github.com/danielcostadev/linketinder-backend/raw/master/Linketinder-Project.jar)
- Após baixar o arquivo RAR, extraia escolhendo a opção "Extrair aqui".
- Com auxílio do seu terminal navegue até a pasta que foi extraída.
- Para executar a aplicação utilize o comando abaixo:

   ```bash
  java -jar Linketinder-Project.jar

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença

- **MIT LICENSE:** [Ver licença](https://github.com/danielcostadev/linketinder-backend/blob/master/LICENSE)


## Contato

Para maiores informações ou dúvidas, entre em contato:

- **Nome:** Daniel Costa
- **LinkedIn:** [DanielCostaDev](https://www.linkedin.com/in/danielcostadev)
