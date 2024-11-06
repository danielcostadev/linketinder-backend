package com.aczg.service

import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.model.Vaga
import com.aczg.service.interfaces.IVagaService
import com.aczg.service.interfaces.ManipulaEntidadeTrait

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class VagaService implements IVagaService<Vaga>, ManipulaEntidadeTrait {

    private static final Logger log = LoggerFactory.getLogger(CandidatoService)

    IEntidadeDAO vagaDAO

    VagaService(IEntidadeDAO vagaDAO) {
        this.vagaDAO = vagaDAO
    }

    @Override
    Long cadastrar(Vaga vaga) throws EntidadeJaExisteException, DatabaseException {
        try {
            Long vagaId = vagaDAO.cadastrar(vaga)
            log.info("Candidato cadastrado com sucesso")
            return vagaId

        } catch (EntidadeJaExisteException | DatabaseException e) {
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
            manipularEntidade(vaga.id, "Vaga",
                    { id -> verificarExistencia(id) },
                    { id -> vagaDAO.editar(vaga) },
                    "atualizado(a)"
            )
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
            manipularEntidade(vagaId, "Vaga",
                    { id -> verificarExistencia(vagaId) },
                    { id -> vagaDAO.remover(vagaId) },
                    "removido(a)"
            )
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
}
