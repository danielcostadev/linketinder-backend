package com.aczg.DAO.interfaces

import com.aczg.model.Candidato

interface ICandidatoDAO {

    Long cadastrar(Candidato candidato)
    List<Candidato> listar()
    void editar(Candidato candidato)
    void remover(Long candidatoId)
    Candidato buscarPorId(Long candidatoId)

}