package com.aczg.service

import com.aczg.DAO.CandidatoDAO
import com.aczg.DAO.CompetenciaDAO
import com.aczg.model.Candidato
import spock.lang.Specification

import java.sql.Date

class CandidatoServiceSpec extends Specification{

    CandidatoDAO candidatoDAO = Mock()
    CompetenciaDAO competenciaDAO = Mock()
    CandidatoService candidatoService = new CandidatoService(candidatoDAO, competenciaDAO)

    def "Deve adicionar um novo candidato com sucesso"() {
        given: "Um Mock do Candidato Service, CandidatoDAO e um candidato"
        Candidato candidato = Mock(Candidato)

        when: "O serviço de adicionar candidato é chamado"
        candidatoService.adicionarCandidato(candidato)

        then: "O método salvar do DAO é chamado uma vez com o candidato correto e retorna o ID do candidato cadastrado"
        1 * candidatoDAO.cadastrar(candidato) >> 1L
    }

    def "Deve listar todos os candidatos"() {
        given: "Uma lista mockada de candidatos"
        List<Candidato> candidatosEsperados = [new Candidato("Daniel", "Costa", "daniel@test.com", "(79)99911-0213", "https://linkedin.com/in/daniel", "03658426560", Date.valueOf("1990-08-06"), "SE", "49400000", "Descrição", "Superior", "senha123")]
        candidatoDAO.listar() >> candidatosEsperados

        when: "Mostrar candidatos"
        List<Candidato> candidatos = candidatoService.listarCandidados()

        then: "A lista de candidatos deve ser a esperada"
        candidatos == candidatosEsperados
    }

    def "Deve atualizar um candidato existente"() {
        given: "Um candidato para ser atualizado"
        Candidato candidato = new Candidato("Daniel", "Costa", "daniel@test.com", "(79)99911-0213", "https://linkedin.com/in/daniel", "03658426560", Date.valueOf("1990-08-06"), "SE", "49400000", "Descrição", "Superior", "senha123")

        when: "Atualizar candidato"
        candidatoService.atualizarCandidato(candidato)

        then: "O método de atualizar deve ser chamado corretamente"
        1 * candidatoDAO.editar(candidato)
    }

    def "Deve cadastrar competências para o candidato"() {
        given: "Uma lista de competências"
        List<String> competencias = ["Java", "Groovy", "SQL"]

        when: "Cadastrar competências para um candidato"
        candidatoService.adicionarCompetencia(competencias, 1L)

        then: "As competências devem ser inseridas corretamente"
        3 * competenciaDAO.adicionarCompetencia(_, 1L, _)
    }

    def "Deve deletar um candidato pelo ID"() {
        given: "Um ID de candidato para ser deletado"
        Long candidatoId = 1L

        when: "Deletar candidato"
        candidatoService.removerCandidato(candidatoId)

        then: "O método de deletar deve ser chamado com o ID correto"
        1 * candidatoDAO.remover(candidatoId)
    }

}


