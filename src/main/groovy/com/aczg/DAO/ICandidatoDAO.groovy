package com.aczg.DAO

import com.aczg.model.Candidato

interface ICandidatoDAO {

    Long adicionarCandidato(Candidato candidato)
    List<Candidato> listarCandidatos()
    void atualizarCandidato(Candidato candidato)
    void removerCandidato(Long candidatoId)

}