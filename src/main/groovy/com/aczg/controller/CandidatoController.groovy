package com.aczg.controller

import com.aczg.service.CandidatoService

import java.sql.Date

class CandidatoController implements validadorEntrada{

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
        String competencias = validarTexto("Digite as competências separadas por vírgula: ");
        List<String> listaCompetencias = competencias.split(",\\s*");

        try {
            candidatoService.cadastrarCandidato(nome, sobrenome, email, telefone, linkedin, cpf, dataNascimento, estado, cep, descricao, formacao, senha, listaCompetencias)
            println("Candidato '${nome}' cadastrado com sucesso!");

        } catch (Exception e) {
            println("Erro ao cadastrar dados': ${e.message}");
        }
    }

}
