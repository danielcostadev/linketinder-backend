package com.aczg.service

import com.aczg.DAO.IEmpresaDAO
import com.aczg.model.Empresa


class EmpresaService {

    IEmpresaDAO empresaDAO

    EmpresaService(IEmpresaDAO empresaDAO){
        this.empresaDAO = empresaDAO
    }


    Long adicionarEmpresa(Empresa empresa){

        try {
            Long empresaId = empresaDAO.adicionarEmpresa(empresa)
            return empresaId

        } catch (Exception e) {
            println "Erro ao cadastrar empresa e vagas: ${e.message}"
            return null
        }
    }

    List<Empresa> listarEmpresas(){
        return getEmpresaDAO().listarEmpresas()
    }

    void atualizarEmpresa(Empresa empresa){
        getEmpresaDAO().atualizarEmpresa(empresa)
    }

    void removerEmpresa(Long empresaId) {
        getEmpresaDAO().removerEmpresa(empresaId)
    }

}
