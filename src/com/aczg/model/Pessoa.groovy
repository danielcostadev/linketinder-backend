package com.aczg.model

abstract class Pessoa implements Personalizavel{

    String nome
    String estado
    String cep
    String descricao

    Pessoa(String nome, String estado, String cep, String descricao) {
        this.nome = nome
        this.estado = estado
        this.cep = cep
        this.descricao = descricao
    }

    @Override
    void mostrarInformacoes() {
        println "Teste"
    }

}