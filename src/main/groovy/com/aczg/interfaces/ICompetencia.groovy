package com.aczg.interfaces

import com.aczg.model.Competencia

interface ICompetencia {

    void cadastrar(List<String> competencias, Long candidatoId, Long vagaId)
    List<Competencia> listar()
    Competencia buscarPorId(Long id)
}