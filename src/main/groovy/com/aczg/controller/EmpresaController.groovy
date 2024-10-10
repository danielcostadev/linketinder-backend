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
            Long empresaId = empresaService.cadastrarEmpresa(nome,email,estado,cnpj,pais,cep,descricao,senha)
            println("Empresa '${nome}' cadastrada com sucesso!");

            if(empresaId != null){
                adicionarVaga(empresaId)
            }

        } catch (Exception e) {
            println("Erro ao cadastrar empresa '${nome}': ${e.message}");
        }

    }


    void adicionarVaga(Long empresaId){

        String nome = validarTexto("Digite o TITULO da vaga: ")
        String descricao = validarTexto("Digite a DESCRIÇÃO da vaga: ")
        String local = validarTexto("Digite o LOCAL da vaga: ")

        try {
            Long vagaId = empresaService.cadastrarVaga(nome,descricao,local,empresaId)
            println("Vaga '${nome}' cadastrada com sucesso!");

            if (vagaId != null){
                adicionarCompetencia(vagaId)
            }

        } catch (Exception e) {
            println("Erro ao cadastrar vaga '${nome}': ${e.message}");
        }
    }


    void adicionarCompetencia(Long vagaId){

        String competencias = validarTexto("Digite as competências separadas por vírgula: ");
        List<String> listaCompetencias = competencias.split(",\\s*");

        try {
            empresaService.cadastrarCompetenciaVaga(listaCompetencias, vagaId)
            println("Competencia '${nome}' cadastrada com sucesso!");

        } catch (Exception e) {

        }

    }
}
