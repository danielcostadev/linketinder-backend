package com.aczg.view.telas

import com.aczg.controller.interfaces.ICandidatoController
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.model.Candidato
import com.aczg.service.interfaces.ManipulaEntidadeTrait
import com.aczg.view.interfaces.ValidadorEntradaTrait
import com.aczg.view.telas.interfaces.IFormularioAuxiliarEdicao
import com.aczg.view.telas.interfaces.ITela

import java.sql.Date

class CandidatoView implements ITela, IFormularioAuxiliarEdicao<Candidato>, ManipulaEntidadeTrait, ValidadorEntradaTrait {

    ICandidatoController candidatoController

    CandidatoView(ICandidatoController candidatoController) {
        this.candidatoController = candidatoController
    }

    @Override
    void exibirFormularioDeCadastro() {
        try {
            String nome = validarTextoComRegex("nome", "Digite o NOME do candidato: ")
            String sobrenome = validarTextoComRegex("sobrenome", "Digite O SOBRENOME do candidato ")
            String email = validarTextoComRegex("email", "Digite o EMAIL pessoal do candidato: ")
            String telefone = validarTextoComRegex("telefone", "Digite o TELEFONE do candidato: ").replaceAll(/\D/, '')
            String linkedin = validarTextoComRegex("linkedin", "Digite o LINKEDIN do candidato: ")
            String cpf = validarTextoComRegex("cpf", "Digite o CPF da candidato: ").replaceAll(/\D/, '')
            Date dataNascimento = validarData("Digite a DATA DE NASCIMENTO do candidato: ")
            String estado = validarTextoComRegex("estado", "Digite o estado do candidato: ").toUpperCase()
            String cep = validarTextoComRegex("cep", "Digite o CEP do candidato: ").replaceAll(/\D/, '')
            String descricao = validarTextoComRegex("descricao", "Digite uma breve descrição do candidato: ")
            String formacao = validarTexto("Digite a FORMAÇÃO do candidato: ")
            String senha = validarTexto("Digite a SENHA do candidato: ")

            Long candidatoId = getCandidatoController().cadastrar(new Candidato(nome, sobrenome, email, telefone, linkedin, cpf, dataNascimento, estado, cep, descricao, formacao, senha))

            if (candidatoId != null) {
                println "Candidato cadastrado com sucesso! ID: " + candidatoId;
            }
        } catch (EntidadeJaExisteException e) {
            println "Erro: Já existe um candidato cadastrado com o mesmo e-mail ou CPF.";
        } catch (DatabaseException e) {
            println "Erro: Houve um problema ao acessar o banco de dados. Tente novamente mais tarde.";
        } catch (Exception e) {
            println "Erro inesperado: " + e.getMessage();
        }
    }

    @Override
    void exibirLista() {

        try {
            List<Candidato> candidatos = getCandidatoController().listar()
            candidatos.each { candidato ->
                println "ID: ${candidato.getId()}, Formação ${candidato.getFormacao()}, Descrição: ${candidato.getDescricao()}"
            }
        } catch (Exception e) {
            println "Erro ao recuperar lista de candidatos: ${e.message}"
        }

    }

    @Override
    void exibirFormulariodeEdicao() {
        try {
            Long candidatoId = validarInteiro("Digite o ID do candidato que deseja atualizar: ")

            if (!validarExistencia(candidatoId)) {
                println "Candidatoa(a) com ID ${candidatoId} não encontrado(a)."
                return
            }

            Candidato candidatoAtualizado = coletarDadosParaEdicao(candidatoId)
            atualizar(candidatoAtualizado)
        } catch (Exception e) {
            println "Não foi possível exibir o formulário: ${e.message}"
        }
    }

    @Override
    boolean validarExistencia(Long candidatoId) {
        return candidatoController.verificarExistencia(candidatoId)
    }

    @Override
    Candidato coletarDadosParaEdicao(Long candidatoId) {

        try {
            String newNome = validarTextoComRegex("nome", "Digite o NOME do candidato: ")
            String newSobrenome = validarTextoComRegex("sobrenome", "Digite O SOBRENOME do candidato ")
            String newEmail = validarTextoComRegex("email", "Digite o EMAIL pessoal do candidato: ")
            String newTelefone = validarTextoComRegex("telefone", "Digite o TELEFONE do candidato: ").replaceAll(/\D/, '')
            String newLinkedin = validarTextoComRegex("linkedin", "Digite o LINKEDIN do candidato: ")
            String newCpf = validarTextoComRegex("cpf", "Digite o CPF da candidato: ").replaceAll(/\D/, '')
            Date newDataNascimento = validarData("Digite a DATA DE NASCIMENTO do candidato: ")
            String newEstado = validarTextoComRegex("estado", "Digite o estado do candidato: ").toUpperCase()
            String newCep = validarTextoComRegex("cep", "Digite o CEP do candidato: ").replaceAll(/\D/, '')
            String newDescricao = validarTextoComRegex("descricao", "Digite uma breve descrição do candidato: ")
            String newFormacao = validarTexto("Digite a FORMAÇÃO do candidato: ")
            String newSenha = validarTexto("Digite a SENHA do candidato: ")

            return new Candidato(newNome, newSobrenome, newEmail, newTelefone, newLinkedin, newCpf, newDataNascimento, newEstado, newCep, newDescricao, newFormacao, newSenha).with {
                it.id = candidatoId
                it
            }
        } catch (Exception e) {
            println "Erro ao editar candidato: ${e.message}"
        }
    }

    @Override
    void atualizar(Candidato candidatoAtualizado) {

        try {
            candidatoController.editar(candidatoAtualizado)
            println "Candidato atualizado com sucesso!"
        } catch (Exception e) {
            println "Erro ao atualizar candidato: ${e.message}"
        }
    }

    @Override
    void exibirFormulariodeExclusao() {
        Long candidatoId = validarInteiro("Digite o ID do candidato que deseja remover: ")
        try {
            candidatoController.remover(candidatoId)
            println "Candidato removido com sucesso!"
        } catch (Exception e) {
            println "Erro ao remover candidato: ${e.message}"
        }
    }
}
