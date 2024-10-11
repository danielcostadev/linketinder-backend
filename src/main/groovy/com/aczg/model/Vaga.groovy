package com.aczg.model

class Vaga {

    Long id
    String nome
    String descricao
    String local
    Integer idEmpresa
    Vaga(String nome, String descricao, String local, Long idEmpresa){

        this.id = id
        this.nome = nome
        this.descricao = descricao
        this.local = local
        this.idEmpresa = idEmpresa
    }

}
