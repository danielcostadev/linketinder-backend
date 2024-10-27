package com.aczg.service


import com.aczg.DAO.ICandidatoDAO
import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.model.Candidato
import com.aczg.service.interfaces.EntidadeTrait
import com.aczg.service.interfaces.IEntidadeService

class CandidatoService implements IEntidadeService<Candidato>, EntidadeTrait{

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
        return false
    }










    Long adicionarCandidato(Candidato candidato){

        try {
            Long candidatoId = candidatoDAO.adicionarCandidato(candidato)

            return candidatoId

        } catch (Exception e){
            println "Erro ao cadastrar candidato: ${e.message}"
        }
    }

    List<Candidato> listarCandidados(){
        return getCandidatoDAO().listarCandidatos()
    }

    void atualizarCandidato(Candidato candidato){
        getCandidatoDAO().atualizarCandidato(candidato)
    }

    void removerCandidato(Long candidatoId) {
        getCandidatoDAO().removerCandidato(candidatoId)
    }
}
