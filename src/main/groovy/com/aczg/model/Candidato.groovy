package com.aczg.model

import java.sql.Date
import java.time.LocalDate

class Candidato extends Pessoa{

    String sobrenome
    String cpf
    LocalDate dataNascimento
    String formacao
    String telefone
    String linkedin

        Candidato(Long id = null, String nome, String sobrenome, String email, String telefone, String linkedin, String cpf, LocalDate dataNascimento, String estado, String cep, String descricao, String formacao, String senha) {
            super(nome, email, estado, cep, descricao, senha)
            this.id = id
            this.sobrenome = sobrenome
            this.cpf = cpf
            this.dataNascimento = dataNascimento
            this.formacao = formacao
            this.telefone = telefone
            this.linkedin = linkedin
        }

}
