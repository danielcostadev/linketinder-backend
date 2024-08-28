package com.aczg.model

class Candidato extends Pessoa {

    String cpf
    String email
    Integer idade
    List<String> competencias

    Candidato(String nome, String email, String cpf, Integer idade, String estado, String cep, String descricao, List<String> competencias) {
        super(nome, estado, cep, descricao)
        this.cpf = cpf
        this.email = email
        this.idade = idade
        this.competencias = competencias
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