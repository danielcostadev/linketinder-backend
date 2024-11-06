package com.aczg.service

import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.model.Candidato
import com.aczg.service.interfaces.ICandidatoService
import com.aczg.service.interfaces.ManipulaEntidadeTrait

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CandidatoService implements ICandidatoService<Candidato>, ManipulaEntidadeTrait{

    private static final Logger log = LoggerFactory.getLogger(CandidatoService)

    IEntidadeDAO candidatoDAO

    CandidatoService(IEntidadeDAO candidatoDAO){
        this.candidatoDAO = candidatoDAO
    }

    @Override
    Long cadastrar(Candidato candidato) throws EntidadeJaExisteException, DatabaseException{
        try {
            Long candidatoId = candidatoDAO.cadastrar(candidato)
            log.info("Candidato cadastrado com sucesso")
            return candidatoId
        } catch (EntidadeJaExisteException | DatabaseException e) {
            log.error("Erro: ${e.getMessage()}")
            throw e;
        }
    }

    @Override
    List<Candidato> listar() throws DatabaseException{
        try {
            return getCandidatoDAO().listar()
        } catch (DatabaseException e) {
            log.error("Erro: ${e.getMessage()}")
            throw e
        }
    }

    @Override
    void editar(Candidato candidato) throws DatabaseException{

        try {
            manipularEntidade(candidato.id, "Candidato",
                    { id -> verificarExistencia(id) },
                    { id -> candidatoDAO.editar(candidato) },
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
    void remover(Long candidatoId) throws DatabaseException{
        try {
            manipularEntidade(candidatoId, "Candidato",
                    { id -> verificarExistencia(candidatoId) },
                    { id -> candidatoDAO.remover(candidatoId) },
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
    boolean verificarExistencia(Long candidatoId) {
        return candidatoDAO.verificarExistencia('candidatos', candidatoId)
    }

}
