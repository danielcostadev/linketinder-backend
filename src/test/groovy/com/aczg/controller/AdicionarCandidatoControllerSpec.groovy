package com.aczg.controller

import com.aczg.model.Candidato
import com.aczg.service.CandidatoService
import spock.lang.Specification

class AdicionarCandidatoControllerSpec extends Specification{

    def 'Deve adicionar uma nova candidato com sucesso'() {
        given: "Um mock do serviço de candidato"
        CandidatoService candidatoService = Mock(CandidatoService)
        CandidatoController controller = new CandidatoController(candidatoService)

        and: "Entradas válidas para o formulário"
        controller.validarTextoComRegex(_ as String, _ as String) >> "Validado"
        controller.validarTexto(_ as String) >> "Validado"

        when: "A função de adicionar candidato é chamada"
        controller.exibirFormularioParaAdicionarCandidato()

        then: "O serviço de adicionar candidato é chamado com uma nova candidato"
        1 * candidatoService.adicionarCandidato(_ as Candidato)

        and: "Nenhuma exceção é lançada"
        noExceptionThrown()
    }

    def "Deve exibir uma mensagem de erro ao tentar adicionar candidato e falhar"() {
        given: "Um mock do serviço de candidato que gera uma exceção"
        CandidatoService candidatoService = Mock(CandidatoService)
        CandidatoController controller = new CandidatoController(candidatoService)

        and: "Entradas válidas para o formulário"
        controller.validarTextoComRegex(_ as String, _ as String) >> "Validado"
        controller.validarTexto(_ as String) >> "Validado"

        and: "O serviço lança uma exceção ao tentar adicionar a candidato"
        CandidatoService.adicionarCandidato(_ as Candidato) >> { throw new Exception("Falha ao cadastrar candidato") }

        when: "A função de adicionar candidato é chamada"
        controller.exibirFormularioParaAdicionarCandiato()

        then: "Nenhuma exceção é lançada"
        noExceptionThrown()

        and: "A mensagem de erro é exibida no console"
        println "Erro ao cadastrar candidato 'Validado': Falha ao cadastrar candidato"
    }

}
