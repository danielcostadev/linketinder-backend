package com.aczg.controller

import com.aczg.controller.interfaces.IVagaController
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.exceptions.EntidadeNaoEncontradaException
import com.aczg.model.Vaga
import com.aczg.service.interfaces.IVagaService

class VagaController implements IVagaController<Vaga> {

    IVagaService vagaService

    VagaController(IVagaService vagaService){
        this.vagaService = vagaService
    }

    @Override
    Long cadastrar(Vaga vaga) throws EntidadeJaExisteException, DatabaseException {
        return getVagaService().cadastrar(vaga)
    }

    @Override
    List<Vaga> listar() throws DatabaseException {
        return getVagaService().listar()
    }

    @Override
    void editar(Vaga vaga) throws DatabaseException {
        getVagaService().editar(vaga)
    }

    @Override
    void remover(Long vagaId) throws EntidadeNaoEncontradaException, DatabaseException {
            if (verificarExistencia(vagaId)) {
                getVagaService().remover(vagaId)
            } else {
                throw new EntidadeNaoEncontradaException()
            }
    }

    @Override
    boolean verificarExistencia(Long vagaId) throws EntidadeNaoEncontradaException, DatabaseException {
        return getVagaService().verificarExistencia(vagaId)
    }
}
