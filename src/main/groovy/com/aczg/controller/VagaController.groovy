package com.aczg.controller


import com.aczg.model.Vaga
import com.aczg.interfaces.IEntidade

class VagaController implements IEntidade<Vaga> {

    IEntidade vagaService

    VagaController(IEntidade vagaService){
        this.vagaService = vagaService
    }

    @Override
    Long cadastrar(Vaga vaga) {
        try {
            return getVagaService().cadastrar(vaga)
        } catch (Exception e) {
            println "Erro ao cadastrar vaga: ${e.message}"
        }
    }

    @Override
    List<Vaga> listar() {
        try {
            return getVagaService().listar()
        } catch (Exception e) {
            println "Erro ao listar vagas: ${e.message}"
        }
    }

    @Override
    void editar(Vaga vaga) {
        try {
            if (verificarExistencia(vaga.id)) {
                getVagaService().editar(vaga)
            } else {
                println "Vaga com ID ${vagaId} não encontrada."
            }
        } catch (Exception e) {
            println "Erro ao editar vaga: ${e.message}"
        }
    }

    @Override
    void remover(Long vagaId) {
        try {
            if (verificarExistencia(vagaId)) {
                getVagaService().remover(vagaId)
            } else {
                println "Vaga com ID ${vagaId} não encontrada."
            }
        } catch (Exception e) {
            println "Erro ao remover vaga: ${e.message}"
        }
    }

    @Override
    boolean verificarExistencia(Long vagaId) {
        try {
            return getVagaService().verificarExistencia(vagaId)
        } catch (Exception e) {
            println "Erro ao verificar existência da vaga: ${e.message}"
            return false
        }
    }
}
