package com.aczg.service

import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.model.Candidato
import com.aczg.service.interfaces.ManipulaEntidadeTrait
import com.aczg.interfaces.IEntidade

class CandidatoService implements IEntidade<Candidato>, ManipulaEntidadeTrait{

    IEntidadeDAO candidatoDAO

    CandidatoService(IEntidadeDAO candidatoDAO){
        this.candidatoDAO = candidatoDAO
    }

    @Override
    Long cadastrar(Candidato candidato) {
        try {
            Long candidatoId = candidatoDAO.cadastrar(candidato)
            return candidatoId
        } catch (Exception e) {
            println "Erro ao cadastrar candidato(a): ${e.message}"
            return null
        }
    }

    @Override
    List<Candidato> listar() {
        try {
            return getCandidatoDAO().listar()
        } catch (Exception e) {
            println "Erro ao recuperar lista de candidatos: ${e.message}"
            return null
        }
    }

    @Override
    void editar(Candidato candidato) {

        try {
            manipularEntidade(candidato.id, "Candidato",
                    { id -> verificarExistencia(id) },
                    { id -> candidatoDAO.editar(candidato) },
                    "atualizado(a)"
            )
        } catch (Exception e) {
            println "Erro ao editar candidato: ${e.message}"
        }
    }

    @Override
    void remover(Long candidatoId) {
        try {
            manipularEntidade(candidatoId, "Candidato",
                    { id -> verificarExistencia(candidatoId) },
                    { id -> candidatoDAO.remover(candidatoId) },
                    "removido(a)"
            )
        } catch (Exception e) {
            println "Erro ao remover candidato(a): ${e.message}"
        }
    }

    @Override
    boolean verificarExistencia(Long candidatoId) {
        return candidatoDAO.verificarExistencia('candidatos', candidatoId)
    }

}
