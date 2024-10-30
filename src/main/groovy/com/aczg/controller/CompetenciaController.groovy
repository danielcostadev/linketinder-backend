package com.aczg.controller

import com.aczg.interfaces.ICompetencia
import com.aczg.interfaces.IEntidadeVerificarExistencia
import com.aczg.model.Competencia

class CompetenciaController implements ICompetencia, IEntidadeVerificarExistencia {

    private ICompetencia competenciaService

    CompetenciaController(ICompetencia competenciaService){
        this.competenciaService = competenciaService
    }

    @Override
    void cadastrar(List<String> competencias, Long candidatoId, Long vagaId) {
        competenciaService.cadastrar(competencias, candidatoId, vagaId)
    }

    @Override
    List<Competencia> listar() {
        return competenciaService.listar()
    }

    @Override
    boolean verificarExistencia(Long entidadeId) {
        return false
    }
}
