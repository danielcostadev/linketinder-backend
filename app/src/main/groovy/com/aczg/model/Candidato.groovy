package com.aczg.model

class Candidato extends Pessoa {

    String sobrenome
    String cpf
    Date dataNascimento
    String formacao
    String telefone
    String linkedin
    Integer idade
    List<Competencia> competencias

    Candidato(String nome, String sobrenome, String email, String telefone, String linkedin,  String cpf, Date dataNascimento, String estado, String cep, String descricao, String formacao, List<Competencia> competencias, String senha, List<Vaga> vagas) {
        super(nome, email, estado, cep, descricao, competencias, senha, vagas)
        this.sobrenome = sobrenome
        this.cpf = cpf
        this.dataNascimento = dataNascimento
        this.formacao = formacao
        this.telefone = telefone
        this.linkedin = linkedin
    }

    @Override
    void mostrarInformacoes() {
        print """
        Nome: ${nome}
        Email: ${email}
        Estado: ${estado}
        CEP: ${cep}
        Descrição: ${descricao}
        CPF: ${cpf}
        Idade: ${idade}
        Competencias: ${competencias.join(', ')}
        """.stripIndent()
    }
}