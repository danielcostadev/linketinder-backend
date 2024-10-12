package com.aczg.controller

import com.aczg.model.Competencia
import com.aczg.model.Empresa
import com.aczg.model.Vaga
import com.aczg.service.EmpresaService

class EmpresaController implements validadorEntrada, EntidadeTrait{

    EmpresaService empresaService

    EmpresaController(EmpresaService empresaService){
        this.empresaService = empresaService
    }


    void adicionarEmpresa(){

        String nome = validarTextoComRegex("nome","Digite o NOME da empresa: ")
        String email = validarTextoComRegex("email","Digite o EMAIL da empresa: ")
        String estado = validarTextoComRegex("estado","Digite o estado da empresa: ")
        String cnpj = validarTextoComRegex("cnpj","Digite o CNPJ da empresa: ")
        String pais = validarTexto("Digite o PAÍS da empresa: ")
        String cep = validarTextoComRegex("cep","Digite o CEP da empresa: ")
        String descricao = validarTextoComRegex("descricao","Digite uma breve descrição da empresa: ")
        String senha = validarTexto("Digite a SENHA da empresa: ")

        try {
            Long empresaId = getEmpresaService().cadastrarEmpresa(nome,email,estado,cnpj,pais,cep,descricao,senha)
            println("Empresa '${nome}' cadastrada com sucesso!");

         /*   if(empresaId){
                adicionarVaga(empresaId)
            }*/

        } catch (Exception e) {
            println("Erro ao cadastrar empresa '${nome}': ${e.message}");
        }

    }

    void exibirEmpresa(){
        List<Empresa> empresas = getEmpresaService().mostrarEmpresas()
        empresas.each { empresa ->
            println "ID: ${empresa.getId()}, Descrição: ${empresa.getDescricao()}, Estado: ${empresa.getEstado()}"
        }
    }

    void atualizarEmpresa() {
        Long empresaId = validarInteiro("Digite o ID da empresa que deseja editar: ")
        manipularEntidade(empresaId, "Empresa",
                { id -> getEmpresaService().getEmpresaDAO().empresaExiste(id) },
                { id -> editarEmpresa(id) },
                "atualizada"
        )
    }

    void deletarEmpresa() {
        Long empresaId = validarInteiro("Digite o ID da empresa que deseja deletar: ")
        manipularEntidade(empresaId, "Empresa",
                { id -> getEmpresaService().getEmpresaDAO().empresaExiste(id) },
                { id -> getEmpresaService().deletarEmpresa(id) },
                "deletada"
        )
    }



    void adicionarVaga(){
        Long empresaId = validarInteiro("Digite o ID da empresa que está cadastrando a vaga: ")
        manipularEntidade(empresaId, "Empresa",
                { id -> getEmpresaService().getEmpresaDAO().empresaExiste(id) },
                { id -> cadastrarVaga(id) },
                "cadastrada",
                "Vaga"
        )
    }

    void exibirVaga(){
        List<Vaga> vagas = getEmpresaService().mostrarVagas()
        vagas.each { vaga ->
            println "ID: ${vaga.getId()}, Nome: ${vaga.getNome()}, Descrição: ${vaga.getDescricao()}, Local: ${vaga.getLocal()}"
        }
    }

    void atualizarVaga() {
        Long vagaId = validarInteiro("Digite o ID da vaga que deseja editar: ")
        manipularEntidade(vagaId, "Vaga",
                { id -> getEmpresaService().getVagaDAO().vagaExiste(id) },
                { id -> editarVaga(id) },
                "atualizada"
        )
    }

    void deletarVaga() {
        Long vagaId = validarInteiro("Digite o ID da vaga que deseja deletar: ")
        manipularEntidade(vagaId, "Vaga",
                { id -> getEmpresaService().getVagaDAO().vagaExiste(id) },
                { id -> getEmpresaService().deletarVaga(id) },
                "deletada"
        )
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

    void atualizarCompetencia() {
        Long competenciaId = validarInteiro("Digite o ID da competência que deseja editar: ")
        manipularEntidade(competenciaId, "Competência",
                { id -> getEmpresaService().getCompetenciaDAO().competenciaExiste(id) },
                { id -> editarCompetencia(id) },
                "atualizada"
        )
    }

    void deletarCompetencia() {
        Long competenciaId = validarInteiro("Digite o ID da competência que deseja deletar: ")
        manipularEntidade(competenciaId, "Competência",
                { id -> getEmpresaService().getCompetenciaDAO().competenciaExiste(id) },
                { id -> getEmpresaService().deletarCompetencia(id) },
                "deletada"
        )
    }


    private void cadastrarVaga(Long empresaId){

        print "================== CADASTRO DE VAGA ==================\n"
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
