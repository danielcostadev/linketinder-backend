package com.aczg.service


import com.aczg.DAO.interfaces.IEntidadeDAO

import com.aczg.model.Candidato
import com.aczg.service.interfaces.ICandidatoService
import spock.lang.Specification

import java.sql.Date

class CandidatoServiceSpec extends Specification{

    IEntidadeDAO candidatoDAO = Mock()
    ICandidatoService candidatoService = new CandidatoService(candidatoDAO)

    def "Deve adicionar um novo candidato com sucesso"() {
        given: "Um Mock de Candidato"
        Candidato candidato = Mock(Candidato)

        when: "O serviço de adicionar candidato é chamado"
        candidatoService.cadastrar(candidato)

        then: "O método salvar do DAO é chamado uma vez com o candidato correto e retorna o ID do candidato cadastrado"
        1 * candidatoDAO.cadastrar(candidato) >> 1L
    }

    def "Deve listar todos os candidatos"() {
        given: "Uma lista mockada de candidatos"
        List<Candidato> candidatosEsperados = [new Candidato("Daniel", "Costa", "daniel@test.com", "(79)99911-0213", "https://linkedin.com/in/daniel", "03658426560", Date.valueOf("1990-08-06"), "SE", "49400000", "Descrição", "Superior", "senha123")]
        candidatoDAO.listar() >> candidatosEsperados

        when: "Mostrar candidatos"
        List<Candidato> candidatos = candidatoService.listar()

        then: "A lista de candidatos deve ser a esperada"
        candidatos == candidatosEsperados
    }

    def "Deve editar candidato quando ele existe"() {
        given: "Um candidato mockado com um ID válido"
        def candidato = Mock(Candidato)
        candidato.id >> 1L
        candidato.nome >> "Candidato Teste"

        and: "Simulação da verificação de existência como verdadeiro"
        candidatoService.metaClass.verificarExistencia = { Long id -> true }

        when: "chamando o método editar service"
        candidatoService.editar(candidato)

        then: "O método editar do candidatoDAO deve ser chamado uma vez com o candidato"
        1 * candidatoDAO.editar(candidato)
    }

    def "Deve deletar um candidato pelo ID"() {
        given: "Um candidato mockado com um ID válido"
        def candidato = Mock(Candidato)
        candidato.id >> 1L
        candidato.nome >> "Candidato Teste"

        and: "Simulação da verificação de existência como verdadeiro"
        candidatoService.metaClass.verificarExistencia = { Long id -> true }

        when: "Deletar candidato"
        candidatoService.remover(candidato.id)

        then: "O método de deletar deve ser chamado com o ID correto"
        1 * candidatoDAO.remover(candidato.id)
    }

}


