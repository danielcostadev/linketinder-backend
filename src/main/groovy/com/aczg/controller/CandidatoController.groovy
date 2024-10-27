package com.aczg.controller

import com.aczg.model.Candidato
import com.aczg.service.CandidatoService
import com.aczg.service.interfaces.EntidadeTrait

import java.sql.Date

class CandidatoController implements validadorEntradaTrait, EntidadeTrait{

    CandidatoService candidatoService

    CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService
    }

    void adicionarCandidato(){

        String nome = validarTextoComRegex("nome","Digite o NOME do candidato: ")
        String sobrenome = validarTextoComRegex("sobrenome","Digite O SOBRENOME do candidato ")
        String email = validarTextoComRegex("email","Digite o EMAIL pessoal do candidato: ")
        String telefone = validarTextoComRegex("telefone","Digite o TELEFONE do candidato: ").replaceAll(/\D/, '')
        String linkedin = validarTextoComRegex("linkedin","Digite o LINKEDIN do candidato: ")
        String cpf = validarTextoComRegex("cpf","Digite o CPF da candidato: ").replaceAll(/\D/, '')
        Date dataNascimento = validarData("Digite a DATA DE NASCIMENTO do candidato: ")
        String estado = validarTextoComRegex("estado","Digite o estado do candidato: ").toUpperCase()
        String cep = validarTextoComRegex("cep","Digite o CEP do candidato: ").replaceAll(/\D/, '')
        String descricao = validarTextoComRegex("descricao","Digite uma breve descrição do candidato: ")
        String formacao = validarTexto("Digite a FORMAÇÃO do candidato: ")
        String senha = validarTexto("Digite a SENHA do candidato: ")

        try {

            Long candidatoId = candidatoService.adicionarCandidato(new Candidato(nome, sobrenome, email, telefone, linkedin, cpf, dataNascimento, estado, cep, descricao, formacao, senha))
            println("Candidato '${nome}' cadastrado com sucesso!");

            if (candidatoId){
                adicionarCompetencia(candidatoId)
            }

        } catch (Exception e) {
            println("Erro ao cadastrar dados': ${e.message}");
        }

    }

    void listarCandidatos(){
        List<Candidato> candidatos = getCandidatoService().listarCandidados()
        candidatos.each { candidato ->
            println "ID: ${candidato.getId()}, Formação ${candidato.getFormacao()}, Descrição: ${candidato.getDescricao()}"
        }
    }

    void atualizarCandidato() {
        Long candidatoId = validarInteiro("Digite o ID do candidato que deseja editar: ")
        manipularEntidade(candidatoId, "Candidato",
                { id -> getCandidatoService().getCandidatoDAO().verificarExistencia('candidatos',id) },
                { id -> exibirFormularioParaEdicaoDeCandidato(id) },
                "atualizada"
        )
    }

    private void exibirFormularioParaEdicaoDeCandidato(Long candidatoId){

        String newNome = validarTexto("Digite o NOME do candidato: ")
        String newSobrenome = validarTexto("Digite O SOBRENOME do candidato ")
        String newEmail = validarTexto("Digite o EMAIL pessoal do candidato: ")
        String newTelefone = validarTexto("Digite o TELEFONE do candidato: ")
        String newLinkedin = validarTexto("Digite o LINKEDIN do candidato: ")
        String newCpf = validarTexto("Digite o CPF da candidato: ")
        Date newDataNascimento = validarData("Digite a DATA DE NASCIMENTO do candidato: ")
        String newEstado = validarTexto("Digite o estado do candidato: ")
        String newCep = validarTexto("Digite o CEP do candidato: ")
        String newDescricao = validarTexto("Digite uma breve descrição do candidato: ")
        String newFormacao = validarTexto("Digite a FORMAÇÃO do candidato: ")
        String newSenha = validarTexto("Digite a SENHA do candidato: ")

        Candidato candidatoAtualizado = new Candidato(newNome,newSobrenome,newEmail,newTelefone,newLinkedin,newCpf,newDataNascimento,newEstado,newCep,newDescricao,newFormacao,newSenha)
        candidatoAtualizado.id = candidatoId

        try {
            getCandidatoService().atualizarCandidato(candidatoAtualizado)
            println "candidato atualizado com sucesso!"
        } catch (Exception e) {
            println "Erro ao atualizar candidato: ${e.message}"
        }
    }

    void removerCandidato(){
        Long candidatoId = validarInteiro("Digite o ID do(a) candidato(a) que deseja remover: ")
        manipularEntidade(candidatoId, "Candidato(a)",
                { id -> getCandidatoService().getCandidatoDAO().verificarExistencia('candidatos',id) },
                { id -> getCandidatoService().removerCandidato(id) },
                "removido(a)"
        )
    }


}
