package com.aczg.service

import com.aczg.DAO.interfaces.ICompetenciaDAO
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.interfaces.ICompetencia
import com.aczg.model.Competencia
import com.aczg.service.interfaces.ManipulaEntidadeTrait

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CompetenciaService implements ICompetencia, ManipulaEntidadeTrait {
    ICompetenciaDAO competenciaDAO

    private static final Logger log = LoggerFactory.getLogger(CompetenciaService)

    CompetenciaService(ICompetenciaDAO competenciaDAO) {
        this.competenciaDAO = competenciaDAO
    }

    void cadastrar(List<String> competencias, Long candidatoId, Long vagaId) throws DatabaseException {

        try {
            competencias.each { competencia ->
                Competencia novaCompetencia = new Competencia(competencia)
                competenciaDAO.cadastrar(novaCompetencia.getNome(), candidatoId, vagaId)
                log.info("Competência cadastrada com sucesso")
            }
        } catch (EntidadeJaExisteException | DatabaseException e) {
            log.error("Erro: ${e.getMessage()}")
            throw e;
        }
    }

    List<Competencia> listar() {
        try {
            return competenciaDAO.listar()
        } catch (DatabaseException e) {
            log.error("Erro: ${e.getMessage()}")
            throw e
        }
    }

    @Override
    Competencia buscarPorId(Long id) {
        try {
            return competenciaDAO.buscarPorId(id)
        } catch (Exception e) {
            log.warn("Erro ao buscar competência com ID: ${id} - ${e.message}")
            throw e
        }
    }
}
