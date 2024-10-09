package com.aczg.controller

import com.aczg.service.EmpresaService

class EmpresaController implements validadorEntrada{

    EmpresaService empresaService

    EmpresaController(EmpresaService empresaService){
        this.empresaService = empresaService
    }

    void adicionarEmpresa(){

        String nome = validarTexto("Digite o NOME da empresa: ")
        String email = validarTexto("Digite o EMAIL pessoal da empresa: ")
        String estado = validarTexto("Digite o estado da empresa: ")
        String cnpj = validarTexto("Digite o CNPJ da empresa: ")
        String pais = validarTexto("Digite o PAÍS da empresa: ")
        String cep = validarTexto("Digite o CEP da empresa: ")
        String descricao = validarTexto("Digite uma breve descrição da empresa: ")
        String senha = validarTexto("Digite a SENHA da empresa: ")

        try {
            empresaService.cadastrarEmpresa(nome,email,estado,cnpj,pais,cep,descricao,senha)
            println("Empresa '${nome}' cadastrada com sucesso!");
        } catch (Exception e) {
            println("Erro ao cadastrar empresa '${nome}': ${e.message}");
        }

    }



}
