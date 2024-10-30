package com.aczg.service

import com.aczg.DAO.interfaces.ICompetenciaDAO
import com.aczg.interfaces.ICompetencia
import com.aczg.model.Competencia
import com.aczg.model.Vaga
import spock.lang.Specification

class CompetenciaServiceSpec extends Specification{

    ICompetenciaDAO competenciaDAO = Mock()
    ICompetencia competenciaService = new CompetenciaService(competenciaDAO)

    def "Deve adicionar compentencia a um candidato com sucesso"() {
        given: "Um lista de Competencias"
        List<String> competencias = ["Groovy", "Java", "php"]

        and: "Um ID de candidato"
        Long candidatoId = 1L
        Long vagaId = null

        when: "O serviço de adicionar competência é chamado"
        competenciaService.cadastrar(competencias, candidatoId, vagaId)

        then:"O método salvar do DAO é chamado uma vez e cadastra cada competência associa ao candidato e retorna o ID"
        competencias.each { competencia ->
            Competencia novaCompetencia = new Competencia(competencia)
            competenciaDAO.cadastrar(novaCompetencia.nome, candidatoId, vagaId) >> 1L
        }
    }

    def "Deve adicionar compentencia a uma vaga com sucesso"() {
        given: "Um lista de Competencias"
        List<String> competencias = ["Organização", "Proatividade", "Trabalho em equipe"]

        and: "Um ID de candidato"
        Long candidatoId = null
        Long vagaId = 1L

        when: "O serviço de adicionar competência é chamado"
        competenciaService.cadastrar(competencias, candidatoId, vagaId)

        then:"O método salvar do DAO é chamado uma vez e cadastra cada competência associa a vaga e retorna o ID"
        competencias.each { competencia ->
            Competencia novaCompetencia = new Competencia(competencia)
            competenciaDAO.cadastrar(novaCompetencia.nome, candidatoId, vagaId) >> 1L
        }
    }

    def "Deve listar todas as competencias"() {
        given: "Uma lista mockada de competencia"
        List<Vaga> competenciasEsperados = ["Organização", "Proatividade", "Trabalho em equipe"]
        competenciaDAO.listar() >> competenciasEsperados

        when: "Mostrar vagas"
        List<Vaga> competencias = competenciaService.listar()

        then: "A lista de vagas deve ser a esperada"
        competencias == competenciasEsperados
    }

}
