package com.aczg.service

import com.aczg.DAO.CandidatoDAO
import com.aczg.DAO.CompetenciaDAO
import com.aczg.DAO.ConexaoDAO
import com.aczg.model.Candidato
import groovy.sql.Sql
import spock.lang.Specification

import java.sql.Date

class CandidatoServiceSpec extends Specification {

    CandidatoDAO candidatoDAO = Mock()
    CompetenciaDAO competenciaDAO = Mock()
    ConexaoDAO conexaoDAO = Mock()
    CandidatoService candidatoService = new CandidatoService(candidatoDAO, competenciaDAO)

    def setup() {
        candidatoService.conexaoDAO = conexaoDAO
    }

    def "Deve cadastrar um candidato e retornar o ID"() {
        given: "Um SQL mockado e um candidato"
        Sql sql = Mock()
        conexaoDAO.getSql() >> sql
        Candidato candidato = new Candidato("Daniel", "Costa", "daniel@test.com", "(79)99911-0213", "https://linkedin.com/in/daniel", "03658426560", Date.valueOf("1990-08-06"), "SE", "49400000", "Descrição", "Superior", "senha123")

        when: "Cadastrar um candidato"
        Long candidatoId = candidatoService.cadastrarCandidato(candidato.nome, candidato.sobrenome, candidato.email, candidato.telefone, candidato.linkedin, candidato.cpf, candidato.dataNascimento, candidato.estado, candidato.cep, candidato.descricao, candidato.formacao, candidato.senha)

        then: "O candidato deve ser inserido e retornar o ID corretamente"
        1 * candidatoDAO.insertCandidato(_) >> 1L
        candidatoId == 1L
        1 * sql.close()
    }

    def "Deve cadastrar competências para o candidato"() {
        given: "Uma lista de competências"
        List<String> competencias = ["Java", "Groovy", "SQL"]

        when: "Cadastrar competências para um candidato"
        candidatoService.cadastrarCompetencia(competencias, 1L)

        then: "As competências devem ser inseridas corretamente"
        3 * competenciaDAO.insertCompetencia(_, 1L, _)
    }

    def "Deve listar todos os candidatos"() {
        given: "Uma lista mockada de candidatos"
        List<Candidato> candidatosEsperados = [new Candidato("Daniel", "Costa", "daniel@test.com", "(79)99911-0213", "https://linkedin.com/in/daniel", "03658426560", Date.valueOf("1990-08-06"), "SE", "49400000", "Descrição", "Superior", "senha123")]
        candidatoDAO.readCandidados() >> candidatosEsperados

        when: "Mostrar candidatos"
        List<Candidato> candidatos = candidatoService.mostrarCandidados()

        then: "A lista de candidatos deve ser a esperada"
        candidatos == candidatosEsperados
    }

    def "Deve atualizar um candidato existente"() {
        given: "Um candidato para ser atualizado"
        Candidato candidato = new Candidato("Daniel", "Costa", "daniel@test.com", "(79)99911-0213", "https://linkedin.com/in/daniel", "03658426560", Date.valueOf("1990-08-06"), "SE", "49400000", "Descrição", "Superior", "senha123")

        when: "Atualizar candidato"
        candidatoService.atualizarCandidato(candidato)

        then: "O método de atualizar deve ser chamado corretamente"
        1 * candidatoDAO.updateCandidato(candidato)
    }

    def "Deve deletar um candidato pelo ID"() {
        given: "Um ID de candidato para ser deletado"
        Long candidatoId = 1L

        when: "Deletar candidato"
        candidatoService.deletarCandidato(candidatoId)

        then: "O método de deletar deve ser chamado com o ID correto"
        1 * candidatoDAO.deleteCandidato(candidatoId)
    }
}
