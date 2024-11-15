package com.aczg.controller

import com.aczg.controller.interfaces.ICandidatoController
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.exceptions.EntidadeNaoEncontradaException

import com.aczg.model.Candidato
import com.aczg.service.interfaces.ICandidatoService

class CandidatoController implements ICandidatoController<Candidato> {

    ICandidatoService candidatoService

    CandidatoController(ICandidatoService candidatoService) {
        this.candidatoService = candidatoService
    }

    @Override
    Long cadastrar(Candidato candidato) throws EntidadeJaExisteException, DatabaseException {
        return getCandidatoService().cadastrar(candidato)
    }

    @Override
    List<Candidato> listar() throws DatabaseException {
        return getCandidatoService().listar()
    }

    @Override
    void editar(Candidato candidato) throws DatabaseException {
        getCandidatoService().editar(candidato)
    }

    @Override
    void remover(Long candidatoId) throws EntidadeNaoEncontradaException, DatabaseException {
        if (verificarExistencia(candidatoId)) {
            getCandidatoService().remover(candidatoId)
        } else {
            throw new EntidadeNaoEncontradaException()
        }
    }

    @Override
    boolean verificarExistencia(Long candidatoId) throws EntidadeNaoEncontradaException, DatabaseException {
        return getCandidatoService().verificarExistencia(candidatoId)
    }

    @Override
    Candidato buscarPorId(Long id) {
        return getCandidatoService().buscarPorId(id)
    }

}
