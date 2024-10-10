package com.aczg.model

import java.sql.Date

class Candidato extends Pessoa{

    String sobrenome
    String cpf
    Date dataNascimento
    String formacao
    String telefone
    String linkedin

        Candidato(String nome, String sobrenome, String email, String telefone, String linkedin, String cpf, Date dataNascimento, String estado, String cep, String descricao, String formacao, String senha) {
            super(nome, email, estado, cep, descricao, senha)
            this.sobrenome = sobrenome
            this.cpf = cpf
            this.dataNascimento = dataNascimento
            this.formacao = formacao
            this.telefone = telefone
            this.linkedin = linkedin
        }

}
