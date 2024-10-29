package com.aczg.view.telas

import com.aczg.interfaces.IEntidade
import com.aczg.service.interfaces.ManipulaEntidadeTrait

import com.aczg.model.Empresa
import com.aczg.view.telas.interfaces.IEntidadeAuxiliarEdicaoView
import com.aczg.view.telas.interfaces.IEntidadeCRUDView
import com.aczg.view.interfaces.ValidadorEntradaTrait

class EmpresaView implements IEntidadeCRUDView, IEntidadeAuxiliarEdicaoView<Empresa>, ManipulaEntidadeTrait, ValidadorEntradaTrait {

    IEntidade empresaController

    EmpresaView(IEntidade empresaController) {
        this.empresaController = empresaController
    }

    @Override
    void exibirFormularioDeCadastro() {
        try {
            String nome = validarTextoComRegex("nome", "Digite o NOME da empresa: ")
            String email = validarTextoComRegex("email", "Digite o EMAIL da empresa: ")
            String estado = validarTextoComRegex("estado", "Digite o estado da empresa: ").toUpperCase()
            String cnpj = validarTextoComRegex("cnpj", "Digite o CNPJ da empresa: ")
            String pais = validarTexto("Digite o PAÍS da empresa: ")
            String cep = validarTextoComRegex("cep", "Digite o CEP da empresa: ").replaceAll(/\D/, '')
            String descricao = validarTextoComRegex("descricao", "Digite uma breve descrição da empresa: ")
            String senha = validarTexto("Digite a SENHA da empresa: ")

            getEmpresaController().cadastrar(new Empresa(nome, email, estado, cnpj, pais, cep, descricao, senha))
            println "empresa cadastrada com sucesso!"
        } catch (Exception e) {
            println "Erro ao cadastrar empresa: ${e.message}"
        }
    }

    @Override
    void exibirLista() {

        try {
            List<Empresa> empresas = getEmpresaController().listar()
            empresas.each { empresa ->
                println "ID: ${empresa.getId()}, Descrição: ${empresa.getDescricao()}, Estado: ${empresa.getEstado()}"
            }
        } catch (Exception e) {
            println "erro ao recuperar lista de empresas: ${e.message}"
        }

    }

    @Override
    void exibirFormulariodeEdicao() {
        try {
            Long empresaId = validarInteiro("Digite o ID da empresa que deseja atualizar: ")

            if (!validarExistencia(empresaId)) {
                println "Empresa com ID ${empresaId} não encontrada."
                return
            }

            Empresa empresaAtualizada = coletarDados(empresaId)
            atualizar(empresaAtualizada)
        } catch (Exception e) {
            println "Não foi possível exibir o formulário: ${e.message}"
        }
    }

    @Override
    boolean validarExistencia(Long empresaId) {
        return empresaController.verificarExistencia(empresaId)
    }

    @Override
    Empresa coletarDados(Long empresaId) {
        try {
            String newNome = validarTextoComRegex("nome", "Digite o NOME da empresa: ")
            String newEmail = validarTextoComRegex("email", "Digite o EMAIL da empresa: ")
            String newEstado = validarTextoComRegex("estado", "Digite o estado da empresa: ").toUpperCase()
            String newCnpj = validarTextoComRegex("cnpj", "Digite o CNPJ da empresa: ")
            String newPais = validarTexto("Digite o PAÍS da empresa: ")
            String newCep = validarTextoComRegex("cep", "Digite o CEP da empresa: ").replaceAll(/\D/, '')
            String newDescricao = validarTextoComRegex("descricao", "Digite uma breve descrição da empresa: ")
            String newSenha = validarTexto("Digite a SENHA da empresa: ")

            return new Empresa(newNome, newEmail, newEstado, newCnpj, newPais, newCep, newDescricao, newSenha).with {
                it.id = empresaId;
                it
            }
        } catch (Exception e) {
            println "Erro ao editar empresa: ${e.message}"
        }
    }

    @Override
    void atualizar(Empresa empresaAtualizada) {
        try {
            empresaController.editar(empresaAtualizada)
            println "Empresa atualizada com sucesso!"
        } catch (Exception e) {
            println "Erro ao atualizar empresa: ${e.message}"
        }
    }

    @Override
    void exibirFormulariodeExclusao() {
        Long empresaId = validarInteiro("Digite o ID da empresa que deseja remover: ")
        try {
            empresaController.remover(empresaId)
            println "Empresa removida com sucesso!"
        } catch (Exception e) {
            println "Erro ao remover empresa: ${e.message}"
        }
    }
}
