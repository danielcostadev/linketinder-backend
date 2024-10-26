package com.aczg.service

import com.aczg.DAO.IVagaDAO
import com.aczg.model.Vaga

class VagaService {

    IVagaDAO vagaDAO

    VagaService(IVagaDAO vagaDAO){
        this.vagaDAO = vagaDAO
    }

    Long adicionarVaga(String nome, String descricao, String local, Long empresaId) {

        try {
            Vaga vaga = new Vaga(nome, descricao, local)
            Long vagaId = vagaDAO.adicionarVaga(vaga.nome, vaga.descricao, vaga.local, empresaId)
            println "Vaga '${nome}' cadastrada para a empresa ID: ${empresaId}"

            return vagaId

        } catch (Exception e) {
            println "Erro ao cadastrar vaga: ${e.message}"
            return null
        }
    }

    List<Vaga> listarVagas(){
        return getVagaDAO().listarVagas()
    }

    void atualizarVaga(Vaga vaga){
        getVagaDAO().atualizarVaga(vaga)
    }

    void removerVaga(Long vagaId){
        getVagaDAO().removerVaga(vagaId)
    }

}
