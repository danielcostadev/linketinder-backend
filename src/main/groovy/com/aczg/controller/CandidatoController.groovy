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
        String nome = validarTexto("Digite o NOME do candidato: ")
        String sobrenome = validarTexto("Digite O SOBRENOME do candidato ")
        String email = validarTexto("Digite o EMAIL pessoal do candidato: ")
        String telefone = validarTexto("Digite o TELEFONE do candidato: ")
        String linkedin = validarTexto("Digite o LINKEDIN do candidato: ")
        String cpf = validarTexto("Digite o CPF da candidato: ")
        Date dataNascimento = validarData("Digite a DATA DE NASCIMENTO do candidato: ")
        String estado = validarTexto("Digite o estado do candidato: ")
        String cep = validarTexto("Digite o CEP do candidato: ")
        String descricao = validarTexto("Digite uma breve descrição do candidato: ")
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
            println("Competencia '${nome}' cadastrada com sucesso!");

        } catch (Exception e) {

        }

    }

    void exibirCandidado(){
        List<Candidato> candidatos = getCandidatoService().mostrarCandidados()
        candidatos.each { candidato ->
            println "Formação ${candidato.getFormacao()}, Descrição: ${candidato.getDescricao()}"
        }
    }

    void exibirCompetencias(){
        List<Competencia> competencias = getCandidatoService().mostrarCompetencias()
        competencias.each { competencia ->
            println "Nome ${competencia.getNome()}"
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


}
