package com.aczg.DAO

import com.aczg.model.Competencia

interface ICompetenciaDAO {

    void adicionarCompetencia(String competencia, Long candidatoId, Long vagaId)
    List<Competencia> listarCompetencias()


}