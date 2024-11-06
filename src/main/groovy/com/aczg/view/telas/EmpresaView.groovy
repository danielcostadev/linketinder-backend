package com.aczg.view.telas

import com.aczg.controller.interfaces.IEmpresaController
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.exceptions.EntidadeNaoEncontradaException
import com.aczg.model.Empresa
import com.aczg.service.interfaces.ManipulaEntidadeTrait
import com.aczg.view.interfaces.ValidadorEntradaTrait
import com.aczg.view.telas.interfaces.IFormularioAuxiliarEdicao
import com.aczg.view.telas.interfaces.ITela

class EmpresaView implements ITela, IFormularioAuxiliarEdicao<Empresa>, ManipulaEntidadeTrait, ValidadorEntradaTrait {

    IEmpresaController empresaController

    EmpresaView(IEmpresaController empresaController) {
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

            Long empresaId = getEmpresaController().cadastrar(new Empresa(nome, email, estado, cnpj, pais, cep, descricao, senha))

            if (empresaId != null) {
                println "Empresa cadastrada com sucesso! ID: ${empresaId}"
            }
        } catch (EntidadeJaExisteException e) {
            println "Erro: Já existe uma empresa cadastrada com esses dados."
        } catch (DatabaseException e) {
            println "Erro: Houve um problema ao acessar o banco de dados."
        } catch (Exception e) {
            println "Erro inesperado: ${e.getMessage()}"
        }
    }

    @Override
    void exibirLista() {

        try {
            List<Empresa> empresas = getEmpresaController().listar()
            empresas.each { empresa ->
                println "ID: ${empresa.getId()}, Descrição: ${empresa.getDescricao()}, Estado: ${empresa.getEstado()}"
            }
        } catch (DatabaseException e) {
            println "Erro: Houve um problema ao acessar o banco de dados."
        } catch (Exception e) {
            println "Erro inesperado: ${e.getMessage()}"
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

            Empresa empresaAtualizada = coletarDadosParaEdicao(empresaId)
            atualizar(empresaAtualizada)

        } catch (EntidadeNaoEncontradaException e) {
            println "Erro: Empresa não encontrada."
        } catch (DatabaseException e) {
            println "Erro: Houve um problema ao acessar o banco de dados."
        } catch (Exception e) {
            println "Erro inesperado: ${e.message}"
        }
    }

    @Override
    boolean validarExistencia(Long empresaId) {
        return empresaController.verificarExistencia(empresaId)
    }

    @Override
    Empresa coletarDadosParaEdicao(Long empresaId) throws Exception {
        String newNome = validarTextoComRegex("nome", "Digite o NOME da empresa: ")
        String newEmail = validarTextoComRegex("email", "Digite o EMAIL da empresa: ")
        String newEstado = validarTextoComRegex("estado", "Digite o estado da empresa: ").toUpperCase()
        String newCnpj = validarTextoComRegex("cnpj", "Digite o CNPJ da empresa: ")
        String newPais = validarTexto("Digite o PAÍS da empresa: ")
        String newCep = validarTextoComRegex("cep", "Digite o CEP da empresa: ").replaceAll(/\D/, '')
        String newDescricao = validarTextoComRegex("descricao", "Digite uma breve descrição da empresa: ")
        String newSenha = validarTexto("Digite a SENHA da empresa: ")

        new Empresa(newNome, newEmail, newEstado, newCnpj, newPais, newCep, newDescricao, newSenha).with {
            empresa.id = empresaId;
            return empresa.id
        }
    }

    @Override
    void atualizar(Empresa empresaAtualizada) throws Exception {

            empresaController.editar(empresaAtualizada)
            println "Empresa atualizada com sucesso!"
    }

    @Override
    void exibirFormulariodeExclusao() {
        Long empresaId = validarInteiro("Digite o ID da empresa que deseja remover: ")
        try {
            empresaController.remover(empresaId)
            println "Empresa removida com sucesso!"

        } catch (EntidadeNaoEncontradaException e) {
            println "Erro: Empresa não encontrada: ${e.getMessage()}"
        } catch (DatabaseException e) {
            println "Erro: Houve um problema ao acessar o banco de dados: ${e.getMessage()}"
        } catch (Exception e) {
            println "Erro inesperado: ${e.getMessage()}"
        }
    }
}
