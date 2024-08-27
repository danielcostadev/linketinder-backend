package com.aczg.model

class Candidato extends Pessoa {

    String cpf
    Integer idade
    List<String> competencias

    Candidato(String nome, String email, String estado, String cep, String descricao, String cpf, Integer idade, List<String> competencias) {
        super(nome, email, estado, cep, descricao)
        this.cpf = cpf
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
                    Idade: ${idade}
                    Competencias: ${competencias}
                  """
    }
}