# LinkeTinder - Autor: Daniel Costa - ACZG 6.0

Linketinder é uma aplicação Groovy que permite a interação entre candidatos e empresas, e promete revolucionar o método como são realizadas as contratações de novos colaboradores, esta aplicação está sendo desenvolvida como um dos projetos do ACZG 6.0.

## Funcionalidades

- **Adicionar Empresa**: Crie novas tarefas especificando nome, descrição, data de término, prioridade, categoria e status.
- **Adicionar Candidato**: Modifique as informações das tarefas existentes para mantê-las atualizadas.
- **Listar Empresas**: Remova tarefas que não são mais necessárias.
- **Listar Candidatos**: Ordene as tarefas por prioridade, categoria ou status.
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

- **Link:** [Clique aqui para baixar](https://github.com/danielcostadev/Linketinder-Project/raw/master/Linketinder-Project.jar)
- Após baixar o arquivo RAR, extraia escolhendo a opção "Extrair aqui".
- Com auxílio do seu terminal navegue até a pasta que foi extraída.
- Para executar a aplicação utilize o comando abaixo:

   ```bash
  java -jar Linketinder-Project.jar

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença

- **MIT LICENSE:** [Ver licença](https://github.com/danielcostadev/Linketinder-Project/blob/master/LICENSE)


## Contato

Para maiores informações ou dúvidas, entre em contato:

- **Nome:** Daniel Costa
- **LinkedIn:** [DanielCostaDev](https://www.linkedin.com/in/danielcostadev)
