package com.aczg.model

class Empresa extends Pessoa{

    String cnpj
    String pais

    Empresa(String nome, String email, String estado, String cnpj, String pais, String cep, String descricao, String senha) {
        super(nome, email, estado, cep, descricao, senha)
        this.cnpj = cnpj
        this.pais = pais
    }

}