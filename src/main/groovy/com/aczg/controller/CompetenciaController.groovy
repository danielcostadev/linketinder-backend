package com.aczg.controller

import com.aczg.exceptions.DatabaseException
import com.aczg.interfaces.ICompetencia
import com.aczg.model.Competencia

class CompetenciaController implements ICompetencia {

    private ICompetencia competenciaService

    CompetenciaController(ICompetencia competenciaService){
        this.competenciaService = competenciaService
    }

    @Override
    void cadastrar(List<String> competencias, Long candidatoId, Long vagaId) throws DatabaseException {
        competenciaService.cadastrar(competencias, candidatoId, vagaId)
    }

    @Override
    List<Competencia> listar() throws DatabaseException {
        return competenciaService.listar()
    }
}
