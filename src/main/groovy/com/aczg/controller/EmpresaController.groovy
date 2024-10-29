package com.aczg.controller


import com.aczg.model.Competencia
import com.aczg.model.Empresa
import com.aczg.model.Vaga
import com.aczg.interfaces.IEntidade

class EmpresaController implements IEntidade<Empresa> {

    IEntidade empresaService

    EmpresaController(IEntidade empresaService){
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



























    void adicionarVaga(){
        Long empresaId = validarInteiro("Digite o ID da empresa que está cadastrando a vaga: ")
        manipularEntidade(empresaId, "Empresa",
                { id -> getEmpresaService().getEmpresaDAO().verificarExistencia('empresas',id) },
                { id -> exibirFormularioParaAdicionarVaga(id) },
                "cadastrada",
                "Vaga"
        )
    }

    private void exibirFormularioParaAdicionarVaga(Long empresaId){

        print "================== CADASTRO DE VAGA ==================\n"
        String nome = validarTexto("Digite o TITULO da vaga: ")
        String descricao = validarTexto("Digite a DESCRIÇÃO da vaga: ")
        String local = validarTexto("Digite o LOCAL da vaga: ")

        try {
            Long vagaId = empresaService.adicionarVaga(nome,descricao,local,empresaId)
            println("Vaga '${nome}' cadastrada com sucesso!");

            if (vagaId){
                adicionarCompetencia(vagaId)
            }

        } catch (Exception e) {
            println("Erro ao cadastrar vaga '${nome}': ${e.message}");
        }

    }

    void listarVagas(){
        List<Vaga> vagas = getEmpresaService().listarVagas()
        vagas.each { vaga ->
            println "ID: ${vaga.getId()}, Nome: ${vaga.getNome()}, Descrição: ${vaga.getDescricao()}, Local: ${vaga.getLocal()}"
        }
    }

    void atualizarVaga() {
        Long vagaId = validarInteiro("Digite o ID da vaga que deseja atualizar: ")
        manipularEntidade(vagaId, "Vaga",
                { id -> getEmpresaService().getVagaDAO().verificarExistencia('vagas',id) },
                { id -> exibirFormularioParaAtualizarVaga(id) },
                "atualizada"
        )
    }

    private void exibirFormularioParaAtualizarVaga(Long vagaId){
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

    void removerVaga() {
        Long vagaId = validarInteiro("Digite o ID da vaga que deseja deletar: ")
        manipularEntidade(vagaId, "Vaga",
                { id -> getEmpresaService().getVagaDAO().verificarExistencia('vagas',id) },
                { id -> getEmpresaService().removerVaga(id) },
                "deletada"
        )
    }



    void adicionarCompetencia(Long vagaId){

        exibirFormularioParaAdicionarCompetencia()

    }

    private void exibirFormularioParaAdicionarCompetencia(){

        String competencias = validarTexto("Digite as competências separadas por vírgula: ");
        List<String> listaCompetencias = competencias.split(",\\s*");

        try {
            empresaService.adicionarCompetencia(listaCompetencias, vagaId)
            println("Competencia cadastrada com sucesso!");

        } catch (Exception e) {
            println("Erro ao cadastrar dados': ${e.message}");
        }

    }

    void atualizarCompetencia() {
        Long competenciaId = validarInteiro("Digite o ID da competência que deseja editar: ")
        manipularEntidade(competenciaId, "Competência",
                { id -> getEmpresaService().getCompetenciaDAO().verificarExistencia('competencias',id) },
                { id -> exibirFormularioParaAtualizarCompetencia(id) },
                "atualizada"
        )
    }

    private void exibirFormularioParaAtualizarCompetencia(Long competenciaId){
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

    void removerCompetencia() {
        Long competenciaId = validarInteiro("Digite o ID da competência que deseja deletar: ")
        manipularEntidade(competenciaId, "Competência",
                { id -> getEmpresaService().getCompetenciaDAO().verificarExistencia('competencias',id) },
                { id -> getEmpresaService().removerCompetencia(id) },
                "deletada"
        )
    }






}
