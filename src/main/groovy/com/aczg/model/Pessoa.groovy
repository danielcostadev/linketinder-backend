package com.aczg.model

abstract class Pessoa {

    Long id
    String nome
    String email
    String estado
    String cep
    String descricao
    String senha

    Pessoa(String nome, String email, String estado, String cep, String descricao, String senha) {
        this.id = id
        this.nome = nome
        this.email = email
        this.estado = estado
        this.cep = cep
        this.descricao = descricao
        this.senha = senha
    }

}
