package com.aczg.view.telas

import com.aczg.controller.interfaces.IEmpresaController
import com.aczg.controller.interfaces.IVagaController
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.exceptions.EntidadeNaoEncontradaException
import com.aczg.model.Vaga
import com.aczg.service.interfaces.ManipulaEntidadeTrait
import com.aczg.view.interfaces.ValidadorEntradaTrait
import com.aczg.view.telas.interfaces.ITela

class VagaView implements ITela, ManipulaEntidadeTrait, ValidadorEntradaTrait {

    IVagaController vagaController
    IEmpresaController empresaController

    VagaView(IVagaController vagaController, IEmpresaController empresaController) {
        this.vagaController = vagaController
        this.empresaController = empresaController
    }

    @Override
    void exibirFormularioDeCadastro() {
        try {
            Long empresaId = validarInteiro("Digite o ID da empresa para associar a vaga: ")
            if (!empresaController.verificarExistencia(empresaId)) {
                println "Empresa com ID ${empresaId} não encontrada."
                return
            }
            Vaga novaVaga = coletarDadosVaga(empresaId)
            adicionarVaga(novaVaga)

            if (novaVaga.id != null) {
                println "Vaga cadastrada com sucesso! ID: ${novaVaga.id}"
            }
        } catch (DatabaseException e) {
            println "Erro: Houve um problema ao acessar o banco de dados."
        } catch (Exception e) {
            println "Erro inesperado: ${e.getMessage()}"
        }
    }

    @Override
    void exibirLista() {

        try {
            List<Vaga> vagas = getVagaController().listar()
            vagas.each { vaga ->
                println "ID: ${vaga.getId()}, Descrição: ${vaga.getDescricao()}, Estado: ${vaga.getLocal()}"
            }
        } catch (DatabaseException e) {
            println "Erro: Houve um problema ao acessar o banco de dados: ${e.getMessage()}"
        } catch (Exception e) {
            println "Erro inesperado: ${e.getMessage()}"
        }
    }

    @Override
    void exibirFormulariodeEdicao() {
        try {
            Long vagaId = validarInteiro("Digite o ID da vaga que deseja editar: ")

            if (!vagaController.verificarExistencia(vagaId)) {
                println "Vaga com ID ${vagaId} não encontrada."
                return
            }

            String newNome = validarTexto("Digite o TITULO da vaga: ")
            String newDescricao = validarTexto("Digite a DESCRIÇÃO da vaga: ")
            String newLocal = validarTexto("Digite o LOCAL da vaga: ")

            Vaga vagaAtualizada = new Vaga(newNome, newDescricao, newLocal, null)
            vagaAtualizada.id = vagaId

            atualizarVaga(vagaAtualizada)

        } catch (EntidadeNaoEncontradaException e) {
            println "Erro: Vaga não encontrado."
        } catch (DatabaseException e) {
            println "Erro: Houve um problema ao acessar o banco de dados."
        } catch (Exception e) {
            println "Erro inesperado: ${e.message}"
        }
    }

    @Override
    void exibirFormulariodeExclusao() {
        Long vagaId = validarInteiro("Digite o ID da vaga que deseja remover: ")
        try {
            vagaController.remover(vagaId)
            println "Vaga removida com sucesso!"
        } catch (EntidadeNaoEncontradaException e) {
            println "Erro: Candidato(a) não encontrado(a): ${e.getMessage()}"
        } catch (DatabaseException e) {
            println "Erro: Houve um problema ao acessar o banco de dados: ${e.getMessage()}"
        } catch (Exception e) {
            println "Erro inesperado: ${e.getMessage()}"
        }
    }

    Vaga coletarDadosVaga(Long empresaId) throws Exception {

            String nome = validarTexto("Digite o TITULO da vaga: ")
            String descricao = validarTexto("Digite a DESCRIÇÃO da vaga: ")
            String local = validarTexto("Digite o LOCAL da vaga: ")
            return new Vaga(nome, descricao, local, empresaId)
    }

    void adicionarVaga(Vaga vaga) {
        try {
            vagaController.cadastrar(vaga)
            println "Vaga cadastrada com sucesso!"
        } catch (Exception e) {
            println "Erro ao cadastrar vaga: ${e.message}"
        }
    }

    void atualizarVaga(Vaga vagaAtualizada) throws Exception {
        vagaController.editar(vagaAtualizada)
        println "Vaga atualizada com sucesso!"
    }
}