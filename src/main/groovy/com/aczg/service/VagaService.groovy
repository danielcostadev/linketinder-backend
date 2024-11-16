package com.aczg.service

import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.DAO.interfaces.IVagaDAO
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.model.Candidato
import com.aczg.model.Vaga
import com.aczg.service.interfaces.IVagaService
import com.aczg.service.interfaces.ManipulaEntidadeTrait

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class VagaService implements IVagaService<Vaga>, ManipulaEntidadeTrait {

    private static final Logger log = LoggerFactory.getLogger(VagaService)

    IVagaDAO vagaDAO

    VagaService(IVagaDAO vagaDAO) {
        this.vagaDAO = vagaDAO
    }

    @Override
    Long cadastrar(Vaga vaga) throws DatabaseException {
        try {
            Long vagaId = vagaDAO.cadastrar(vaga)
            log.info("Vaga cadastrada com sucesso")
            return vagaId

        } catch (DatabaseException e) {
            log.error("Erro: ${e.getMessage()}")
            throw e;
        }
    }

    @Override
    List<Vaga> listar() throws DatabaseException {
        try {
            return getVagaDAO().listar()
        } catch (DatabaseException e) {
            log.error("Erro: ${e.getMessage()}")
            throw e
        }
    }

    @Override
    void editar(Vaga vaga) throws DatabaseException {
        try {
            vagaDAO.editar(vaga)
        } catch (DatabaseException e) {
            throw e
        } catch (Exception e) {
            log.error("Erro: ${e.getMessage()}")
            throw e
        }
    }

    @Override
    void remover(Long vagaId) throws DatabaseException {
        try {
            vagaDAO.remover(vagaId)
        } catch (DatabaseException e) {
            throw e
        } catch (Exception e) {
            log.error("Erro: ${e.getMessage()}")
            throw e
        }
    }

    @Override
    boolean verificarExistencia(Long vagaId) {
        try {
            return vagaDAO.verificarExistencia('vagas', vagaId)
        } catch (Exception e) {
            log.warn("Erro: ${e.getMessage()}")
        }
    }

    @Override
    Vaga buscarPorId(Long id) throws DatabaseException {
        try {
            return vagaDAO.buscarPorId(id)
        } catch (Exception e) {
            log.warn("Erro ao buscar vaga com ID: ${id} - ${e.message}")
            throw e
        }
    }
}
