package com.aczg.controller

import com.aczg.controller.interfaces.IEmpresaController
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.exceptions.EntidadeNaoEncontradaException
import com.aczg.model.Empresa
import com.aczg.service.interfaces.IEmpresaService

class EmpresaController implements IEmpresaController<Empresa> {

    IEmpresaService empresaService

    EmpresaController(IEmpresaService empresaService) {
        this.empresaService = empresaService
    }

    @Override
    Long cadastrar(Empresa empresa) throws EntidadeJaExisteException, DatabaseException {
        return getEmpresaService().cadastrar(empresa)
    }

    @Override
    List<Empresa> listar() throws DatabaseException {
        return getEmpresaService().listar()
    }

    @Override
    void editar(Empresa empresa) throws DatabaseException {
        getEmpresaService().editar(empresa)
    }

    @Override
    void remover(Long empresaId) throws EntidadeNaoEncontradaException, DatabaseException {
        if (verificarExistencia(empresaId)) {
            getEmpresaService().remover(empresaId)
        } else {
            throw new EntidadeNaoEncontradaException()
        }
    }

    @Override
    boolean verificarExistencia(Long empresaId) throws EntidadeNaoEncontradaException, DatabaseException {
        return getEmpresaService().verificarExistencia(empresaId)
    }

    @Override
    Empresa buscarPorId(Long id) {
        return getEmpresaService().buscarPorId(id)
    }
}
