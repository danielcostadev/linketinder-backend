package com.aczg.model

abstract class Pessoa implements Personalizavel{

    String nome
    String email
    String estado
    String cep
    String descricao
    List<Competencia> competencias
    String senha
    List<Vaga> vagas


    Pessoa(String nome, String email, String estado, String cep, String descricao, List<Competencia> competencias, String senha, List<Vaga> vagas) {
        this.nome = nome
        this.email = email
        this.estado = estado
        this.cep = cep
        this.descricao = descricao
        this.competencias = competencias
        this.senha = senha
        this.vagas = vagas
    }

    @Override
    void mostrarInformacoes() {
        println "Teste"
    }

}