package com.aczg.DAO.interfaces

import com.aczg.model.Competencia

interface ICompetenciaDAO {

    List<Competencia> listar()
    Long cadastrar(String nomeCompetencia, Long candidatoId, Long vagaId)
    void editar(Competencia competencia)
    void remover(Long competenciaId)
}