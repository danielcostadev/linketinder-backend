package com.aczg.controller

import com.aczg.model.Candidato
import com.aczg.repository.CandidatoRepository

class CandidatoController implements Renderizavel, validadorEntrada{
    CandidatoRepository candidatoRepository = new CandidatoRepository()

    //TODO: Realizar devidas validações de acodo com os campos, quando me aprofundar em REGEX
    void adicionarCandidato(){
        String nome = validarResposta("Digite o NOME do candidato: ")
        String email = validarResposta("Digite o EMAIL pessoal do candidato: ")
        String cpf = validarResposta("Digite o CPF da candidato: ")
        Integer idade = validarInt("Digite a IDADE da candidato: ")
        String estado = validarResposta("Digite o estado do candidato: ")
        String cep = validarResposta("Digite o CEP do candidato: ")
        String descricao = validarResposta("Digite uma breve descrição do candidato: ")
        String competencias = validarResposta("Digite as competéncias separadas por virgula: ")
        List<String> listaCompetencias = competencias.split(",\\s*")

        cadastrarCandidato(nome,email,cpf,idade,estado,cep,descricao,listaCompetencias)

    }

    void cadastrarCandidato(String nome, String email, String cpf, Integer idade, String estado, String cep, String descricao, List<String> competencias){
        Candidato novoCandidato = new Candidato(nome,email,cpf,idade,estado,cep,descricao,competencias)
        candidatoRepository.cadastrarNovoCandidato(novoCandidato)
        println " "
        println "Candidato '${nome}' cadastrado com sucesso!"

    }

    @Override
    void mostrarDados(){
        List<Candidato> candidatosPreCadastrados = candidatoRepository.obterCandidatosCadastrados()
        println " "
        println "================== CANDIDATOS CADASTRADOS =================="
        for(candidato in candidatosPreCadastrados){
            candidato.mostrarInformacoes()
            50.times {print '-'}
        }
    }
}
