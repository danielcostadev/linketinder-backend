package com.aczg.service

import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.model.Empresa
import com.aczg.service.interfaces.ManipulaEntidadeTrait
import com.aczg.interfaces.IEntidade

class EmpresaService implements IEntidade<Empresa>, ManipulaEntidadeTrait{

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
            println "Erro ao cadastrar empresa: ${e.message}"
            return null
        }
    }

    @Override
    List<Empresa> listar(){
        try {
            return getEmpresaDAO().listar()
        } catch (Exception e) {
            println "Erro ao recuperar lista de empresas: ${e.message}"
            return null
        }
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
}
