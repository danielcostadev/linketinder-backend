package com.aczg.controller

import com.aczg.model.Competencia
import com.aczg.model.Empresa
import com.aczg.model.Vaga
import com.aczg.service.EmpresaService

class EmpresaController implements validadorEntrada{

    EmpresaService empresaService

    EmpresaController(EmpresaService empresaService){
        this.empresaService = empresaService
    }

    void adicionarEmpresa(){

        String nome = validarTexto("Digite o NOME da empresa: ")
        String email = validarTexto("Digite o EMAIL da empresa: ")
        String estado = validarTexto("Digite o estado da empresa: ")
        String cnpj = validarTexto("Digite o CNPJ da empresa: ")
        String pais = validarTexto("Digite o PAÍS da empresa: ")
        String cep = validarTexto("Digite o CEP da empresa: ")
        String descricao = validarTexto("Digite uma breve descrição da empresa: ")
        String senha = validarTexto("Digite a SENHA da empresa: ")

        try {
            Long empresaId = getEmpresaService().cadastrarEmpresa(nome,email,estado,cnpj,pais,cep,descricao,senha)
            println("Empresa '${nome}' cadastrada com sucesso!");

            if(empresaId){
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

            if (vagaId){
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
            empresaService.cadastrarCompetencia(listaCompetencias, vagaId)
            println("Competencia cadastrada com sucesso!");

        } catch (Exception e) {
            println("Erro ao cadastrar dados': ${e.message}");
        }
    }


    void exibirEmpresa(){
        List<Empresa> empresas = getEmpresaService().mostrarEmpresas()
        empresas.each { empresa ->
            println "Descrição: ${empresa.getDescricao()}, Estado: ${empresa.getEstado()}"
        }
    }

    void exibirVaga(){
        List<Vaga> vagas = getEmpresaService().mostrarVagas()
        vagas.each { vaga ->
            println "Nome: ${vaga.getNome()}, Descrição: ${vaga.getDescricao()}, Local: ${vaga.getLocal()}"
        }
    }


    void atualizarEmpresa() {
        Long empresaId = validarInteiro("Digite o ID da empresa que deseja editar: ")

        try {
            if (getEmpresaService().getEmpresaDAO().empresaExiste(empresaId)) {
                editarEmpresa(empresaId)
            } else {
                println "Erro: A empresa com ID '${empresaId}' não existe."
            }
        } catch (Exception e) {
            println "Erro: ${e.message}"
        }
    }

    private void editarEmpresa(Long empresaId){

        String newNome = validarTexto("Digite o NOME da empresa: ")
        String newEmail = validarTexto("Digite o EMAIL da empresa: ")
        String newEstado = validarTexto("Digite o estado da empresa: ")
        String newCnpj = validarTexto("Digite o CNPJ da empresa: ")
        String newPais = validarTexto("Digite o PAÍS da empresa: ")
        String newCep = validarTexto("Digite o CEP da empresa: ")
        String newDescricao = validarTexto("Digite uma breve descrição da empresa: ")
        String newSenha = validarTexto("Digite a SENHA da empresa: ")

        Empresa empresaAtualizada = new Empresa(newNome,newEmail,newEstado,newCnpj,newPais,newCep,newDescricao,newSenha)
        empresaAtualizada.id = empresaId

        try {
            getEmpresaService().atualizarEmpresa(empresaAtualizada)
            println "empresa atualizada com sucesso!"

        } catch (Exception e) {
            println "Erro ao atualizar empresa: ${e.message}"
        }
    }

    void atualizarVaga(){
        Long vagaId = validarInteiro("Digite o ID da vaga que deseja editar: ")

        try {
            if (getEmpresaService().getVagaDAO().vagaExiste(vagaId)) {
                editarVaga(vagaId)
            } else {
                println "Erro: A vaga com ID '${vagaId}' não existe."
            }
        } catch (Exception e) {
            println "Erro: ${e.message}"
        }
    }

    private void editarVaga(Long vagaId){
        String newNome = validarTexto("Digite o NOME da vaga: ")
        String newDescricao = validarTexto("Digite o DESCRIÇÃO da vaga: ")
        String newLocal = validarTexto("Digite o LOCAL da vaga: ")

        Vaga vagaAtualizada = new Vaga(newNome,newDescricao,newLocal)
        vagaAtualizada.id = vagaId

        try {
            getEmpresaService().atualizarVaga(vagaAtualizada)
            println "vaga atualizada com sucesso!"
        } catch (Exception e) {
            println "Erro ao atualizar vaga: ${e.message}"
        }
    }

    void atualizarCompetencia(){
        Long competenciaId = validarInteiro("Digite o ID da competência que deseja editar: ")

        try {
            if (getEmpresaService().getCompetenciaDAO().competenciaExiste(competenciaId)) {
                editarCompetencia(competenciaId)
            } else {
                println "Erro: A competência com ID '${competenciaId}' não existe."
            }
        } catch (Exception e) {
            println "Erro: ${e.message}"
        }
    }

    private void editarCompetencia(Long competenciaId){
        String newNome = validarTexto("Digite o NOME da competência: ")

        Competencia competenciaAtualizada = new Competencia(newNome)
        competenciaAtualizada.id = competenciaId

        try {
            getEmpresaService().atualizarCompetencia(competenciaAtualizada)
            println "Competência atualizada com sucesso!"
        } catch (Exception e) {
            println "Erro ao atualizar vaga: ${e.message}"
        }
    }


}
