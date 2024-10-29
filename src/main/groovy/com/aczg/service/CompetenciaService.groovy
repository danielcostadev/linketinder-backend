package com.aczg.service

import com.aczg.DAO.interfaces.ICompetenciaDAO
import com.aczg.interfaces.ICompetencia
import com.aczg.model.Competencia
import com.aczg.service.interfaces.ManipulaEntidadeTrait

class CompetenciaService implements ICompetencia, ManipulaEntidadeTrait{
    ICompetenciaDAO competenciaDAO

    CompetenciaService(ICompetenciaDAO competenciaDAO) {
        this.competenciaDAO = competenciaDAO
    }

    void cadastrar(List<String> competencias, Long candidatoId, Long vagaId) {
        competencias.each { competencia ->
            Competencia novaCompetencia = new Competencia(competencia)
            competenciaDAO.cadastrar(novaCompetencia.getNome(), candidatoId, vagaId)
        }
    }

    List<Competencia> listar() {
        return competenciaDAO.listar()
    }

}
