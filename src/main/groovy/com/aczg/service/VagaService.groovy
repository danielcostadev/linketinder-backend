package com.aczg.service

import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.model.Vaga
import com.aczg.service.interfaces.ManipulaEntidadeTrait
import com.aczg.interfaces.IEntidade

class VagaService implements IEntidade<Vaga>, ManipulaEntidadeTrait {

    IEntidadeDAO vagaDAO

    VagaService(IEntidadeDAO vagaDAO) {
        this.vagaDAO = vagaDAO
    }

    @Override
    Long cadastrar(Vaga vaga) {
        try {
            Long vagaId = vagaDAO.cadastrar(vaga)
            return vagaId

        } catch (Exception e) {
            println "Erro ao cadastrar vaga: ${e.message}"
            return null
        }
    }

    @Override
    List<Vaga> listar() {
        try {
            return getVagaDAO().listar()
        } catch (Exception e) {
            println "Erro ao recuperar lista de vagas: ${e.message}"
            return null
        }
    }

    @Override
    void editar(Vaga vaga) {
        try {
            manipularEntidade(vaga.id, "Vaga",
                    { id -> verificarExistencia(id) },
                    { id -> vagaDAO.editar(vaga) },
                    "atualizado(a)"
            )
        } catch (Exception e) {
            println "Erro ao editar empresa: ${e.message}"
        }
    }

    @Override
    void remover(Long vagaId) {
        try {
            manipularEntidade(vagaId, "Vaga",
                    { id -> verificarExistencia(vagaId) },
                    { id -> vagaDAO.remover(vagaId) },
                    "removido(a)"
            )
        } catch (Exception e) {
            println "Erro ao remover vaga: ${e.message}"
        }
    }

    @Override
    boolean verificarExistencia(Long vagaId) {
        return vagaDAO.verificarExistencia('vagas', vagaId)
    }
}
