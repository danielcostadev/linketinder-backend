package com.aczg.model

class Vaga {

    Long id
    String nome
    String descricao
    String local
    Long empresaId

    Vaga(String nome, String descricao, String local, Long empresaId) {
        this.nome = nome
        this.descricao = descricao
        this.local = local
        this.empresaId = empresaId
    }

    Vaga(Long id, String nome, String descricao, String local, Long empresaId) {
        this(nome, descricao, local, empresaId)
        this.id = id
    }

}
