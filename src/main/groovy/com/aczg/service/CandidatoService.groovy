package com.aczg.service


import com.aczg.DAO.ICandidatoDAO
import com.aczg.model.Candidato

class CandidatoService {

    ICandidatoDAO candidatoDAO

    CandidatoService(ICandidatoDAO candidatoDAO){
        this.candidatoDAO = candidatoDAO
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
