package com.aczg.controller

import com.aczg.model.Candidato
import com.aczg.model.Competencia
import com.aczg.model.Empresa
import com.aczg.service.CandidatoService

import java.sql.Date

class CandidatoController implements validadorEntrada, EntidadeTrait{

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

            Long candidatoId = candidatoService.cadastrarCandidato(nome, sobrenome, email, telefone, linkedin, cpf, dataNascimento, estado, cep, descricao, formacao, senha)
            println("Candidato '${nome}' cadastrado com sucesso!");

            if (candidatoId){
                adicionarCompetencia(candidatoId)
            }

        } catch (Exception e) {
            println("Erro ao cadastrar dados': ${e.message}");
        }
    }

    void adicionarCompetencia(Long candidatoId){

        String competencias = validarTexto("Digite as competências separadas por vírgula: ");
        List<String> listaCompetencias = competencias.split(",\\s*");

        try {
            candidatoService.cadastrarCompetencia(listaCompetencias, candidatoId)
            println("Competencia cadastrada com sucesso!");

        } catch (Exception e) {
            println("Erro ao cadastrar dados': ${e.message}");
        }

    }

    void exibirCandidado(){
        List<Candidato> candidatos = getCandidatoService().mostrarCandidados()
        candidatos.each { candidato ->
            println "ID: ${candidato.getId()}, Formação ${candidato.getFormacao()}, Descrição: ${candidato.getDescricao()}"
        }
    }

    void exibirCompetencias(){
        List<Competencia> competencias = getCandidatoService().mostrarCompetencias()
        competencias.each { competencia ->
            println "ID: ${competencia.getId()}, Nome: ${competencia.getNome()}"
        }
    }

    void atualizarCandidato() {
        Long candidatoId = validarInteiro("Digite o ID do candidato que deseja editar: ")
        manipularEntidade(candidatoId, "Candidato",
                { id -> getCandidatoService().getCandidatoDAO().candidatoExiste(id) },
                { id -> editarCandidato(id) },
                "atualizada"
        )
    }

    private void editarCandidato(Long candidatoId){

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

        Candidato candidatoAtualizada = new Candidato(newNome,newSobrenome,newEmail,newTelefone,newLinkedin,newCpf,newDataNascimento,newEstado,newCep,newDescricao,newFormacao,newSenha)
        candidatoAtualizada.id = candidatoId

        try {
            getCandidatoService().atualizarCandidato(candidatoAtualizada)
            println "candidato atualizado com sucesso!"
        } catch (Exception e) {
            println "Erro ao atualizar empresa: ${e.message}"
        }
    }

    void deletarCandidato(){
        Long candidatoId = validarInteiro("Digite o ID do candidato que deseja deletar: ")
        manipularEntidade(candidatoId, "Candidato",
                { id -> getCandidatoService().getCandidatoDAO().candidatoExiste(id) },
                { id -> getCandidatoService().deletarCandidato(id) },
                "deletada"
        )
    }


}
