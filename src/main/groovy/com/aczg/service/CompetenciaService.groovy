package com.aczg.service

import com.aczg.DAO.ICompetenciaDAO
import com.aczg.model.Competencia

class CompetenciaService {
    ICompetenciaDAO competenciaDAO

    CompetenciaService(ICompetenciaDAO competenciaDAO) {
        this.competenciaDAO = competenciaDAO
    }

    private void adicionarCompetenciaGenerico(List<String> competencias, Long candidatoId, Long vagaId) {
        competencias.each { nomeCompetencia ->
            Competencia novaCompetencia = new Competencia(nomeCompetencia)
            competenciaDAO.adicionarCompetencia(novaCompetencia.nome, candidatoId, vagaId)
        }
    }

    void adicionarCompetenciaParaCandidato(List<String> competencias, Long candidatoId) {
        adicionarCompetenciaGenerico(competencias, candidatoId, null)
    }

    void adicionarCompetenciaParaVaga(List<String> competencias, Long vagaId) {
        adicionarCompetenciaGenerico(competencias, null, vagaId)
    }

    void atualizarCompetencia(Competencia competencia) {
        competenciaDAO.atualizarCompetencia(competencia)
    }

    void removerCompetencia(Long competenciaId) {
        competenciaDAO.removerCompetencia(competenciaId)
    }
}
