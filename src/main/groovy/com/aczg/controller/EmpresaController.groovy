package com.aczg.controller

import com.aczg.controller.interfaces.IEmpresaController
import com.aczg.model.Empresa
import com.aczg.service.interfaces.IEmpresaService

class EmpresaController implements IEmpresaController<Empresa> {

    IEmpresaService empresaService

    EmpresaController(IEmpresaService empresaService){
        this.empresaService = empresaService
    }

    @Override
    Long cadastrar(Empresa empresa){
        try {
            return getEmpresaService().cadastrar(empresa)
        } catch (Exception e) {
            println "Erro ao cadastrar empresa: ${e.message}"
        }
    }

    @Override
    List<Empresa> listar() {
        try {
            return getEmpresaService().listar()
        } catch (Exception e) {
            println "Erro ao listar empresas: ${e.message}"
        }
    }

    @Override
    void editar(Empresa empresa) {
        try {
            getEmpresaService().editar(empresa)
        } catch (Exception e) {
            println "Erro ao editar empresa: ${e.message}"
        }
    }

    @Override
    void remover(Long empresaId) {
        try {
            if (verificarExistencia(empresaId)) {
                getEmpresaService().remover(empresaId)
            } else {
                println "Empresa com ID ${empresaId} não encontrada."
            }
        } catch (Exception e) {
            println "Erro ao remover empresa: ${e.message}"
        }
    }

    @Override
    boolean verificarExistencia(Long empresaId) {
        try {
            return getEmpresaService().verificarExistencia(empresaId)
        } catch (Exception e) {
            println "Erro ao verificar existência da empresa: ${e.message}"
            return false
        }
    }

}
