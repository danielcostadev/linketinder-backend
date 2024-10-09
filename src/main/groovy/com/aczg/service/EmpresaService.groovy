package com.aczg.service

import com.aczg.DAO.EmpresaDAO
import com.aczg.model.Empresa

class EmpresaService {

    EmpresaDAO empresaDAO

    EmpresaService(EmpresaDAO empresaDAO){
        this.empresaDAO = empresaDAO
    }

    void cadastrarEmpresa(String nome, String email, String estado, String cnpj, String pais, String cep, String descricao, String senha){

        /*if(EmpresaDAO.existeEmpresa(cnpj)){
            println("A empresa '${nome}' já existe.");
        }*/

        Empresa empresa = new Empresa(nome,email,estado,cnpj,pais,cep,descricao,senha)
        empresaDAO.createEmpresa(empresa)

    }
}
