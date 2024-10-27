package com.aczg.service

import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.model.Empresa
import com.aczg.service.interfaces.EntidadeTrait
import com.aczg.service.interfaces.IEntidadeService


class EmpresaService implements IEntidadeService<Empresa>, EntidadeTrait{

    IEntidadeDAO empresaDAO

    EmpresaService(IEntidadeDAO empresaDAO){
        this.empresaDAO = empresaDAO
    }

    @Override
    Long cadastrar(Empresa empresa){

        try {
            Long empresaId = empresaDAO.cadastrar(empresa)
            return empresaId

        } catch (Exception e) {
            println "Erro ao cadastrar empresa e vagas: ${e.message}"
            return null
        }
    }

    @Override
    List<Empresa> listar(){
        return getEmpresaDAO().listar()
    }

    @Override
    void editar(Empresa empresa) {
        try {
            manipularEntidade(empresa.id, "Empresa",
                    { id -> verificarExistencia(id) },
                    { id -> empresaDAO.editar(empresa) },
                    "atualizado(a)"
            )
        } catch (Exception e) {
            println "Erro ao editar empresa: ${e.message}"
        }
    }

    @Override
    void remover(Long empresaId) {
        try {
            manipularEntidade(empresaId, "Empresa",
                    { id -> verificarExistencia(empresaId) },
                    { id -> empresaDAO.remover(empresaId) },
                    "removido(a)"
            )
        } catch (Exception e) {
            println "Erro ao remover empresa: ${e.message}"
        }
    }

    @Override
    boolean verificarExistencia(Long empresaId) {
        return empresaDAO.verificarExistencia('empresas', empresaId)
    }







    void atualizarEmpresa(Empresa empresa){
        getEmpresaDAO().editar(empresa)
    }

    void removerEmpresa(Long empresaId) {
        getEmpresaDAO().remover(empresaId)
    }

}
