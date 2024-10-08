package com.aczg.model

class Vaga {

    Integer idEmpresa
    String nome
    String descricao
    Candidato candidato
    List<String> competencias

    Vaga(Integer idEmpresa, String nome, String descricao, Candidato candidato, List<String> competencias){

        this.idEmpresa = idEmpresa
        this.nome = nome
        this.descricao = descricao
        this.candidato = candidato
        this.competencias = competencias

    }



}
