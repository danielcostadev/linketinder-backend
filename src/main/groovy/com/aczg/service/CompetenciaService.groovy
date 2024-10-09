package com.aczg.service

import com.aczg.DAO.CompetenciaDAO
import com.aczg.model.Competencia

class CompetenciaService {

    CompetenciaDAO competenciaDAO

    CompetenciaService(CompetenciaDAO competenciaDAO) {
        this.competenciaDAO = competenciaDAO;
    }

    void cadastrarCompetencia(String nome) {
        if (competenciaDAO.existeCompetencia(nome)) {
            throw new IllegalArgumentException("A competência '${nome}' já existe.");
        }

        Competencia novaCompetencia = new Competencia(nome);
        competenciaDAO.createCompetencia(novaCompetencia);
    }


}
