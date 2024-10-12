package com.aczg.model

class Vaga {

    Long id
    String nome
    String descricao
    String local
    Integer idEmpresa
    Vaga(Long id = null, String nome, String descricao, String local){
        this.id = id
        this.nome = nome
        this.descricao = descricao
        this.local = local
        this.idEmpresa = idEmpresa
    }

}
