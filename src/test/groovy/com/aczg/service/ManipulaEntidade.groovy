package com.aczg.service

import com.aczg.DAO.interfaces.IEntidadeDAO

import com.aczg.service.interfaces.ICandidatoService
import com.aczg.service.interfaces.IEmpresaService
import spock.lang.Specification

class ManipulaEntidade extends Specification{

    IEntidadeDAO empresaDAO = Mock()
    IEntidadeDAO candidatoDAO = Mock()
    IEmpresaService empresaService = new EmpresaService(empresaDAO)
    ICandidatoService candidatoService = new CandidatoService(candidatoDAO)

    def "Deve chamar acaoEntidade e exibir mensagem de sucesso quando a entidade existe"() {
        given: "Um ID de entidade e as closures para verificar existência e realizar ação"
        Long idEntidade = 1L
        String nomeEntidadePrincipal = "Candidato"
        String tipoAcao = "atualizado(a)"
        def entidadeExiste = { Long id -> true }
        def acaoEntidade = Mock(Closure)

        when: "Chamamos o método manipularEntidade"
        candidatoService.manipularEntidade(idEntidade, nomeEntidadePrincipal, entidadeExiste, acaoEntidade, tipoAcao)

        then: "acaoEntidade deve ser chamada uma vez com o idEntidade"
        1 * acaoEntidade.call(idEntidade)

        and: "A mensagem de sucesso deve ser exibida"
        println("${nomeEntidadePrincipal} com ID '${idEntidade}' foi ${tipoAcao} com sucesso!")
    }

    def "Deve chamar acaoEntidade e exibir mensagem de sucesso quando a entidade existe"() {
        given: "Um ID de entidade e as closures para verificar existência e realizar ação"
        Long idEntidade = 1L
        String nomeEntidadePrincipal = "Empresa"
        String tipoAcao = "atualizado(a)"
        def entidadeExiste = { Long id -> true }
        def acaoEntidade = Mock(Closure)

        when: "Chamamos o método manipularEntidade"
        empresaService.manipularEntidade(idEntidade, nomeEntidadePrincipal, entidadeExiste, acaoEntidade, tipoAcao)

        then: "acaoEntidade deve ser chamada uma vez com o idEntidade"
        1 * acaoEntidade.call(idEntidade)

        and: "A mensagem de sucesso deve ser exibida"
        println("${nomeEntidadePrincipal} com ID '${idEntidade}' foi ${tipoAcao} com sucesso!")
    }

    def "Deve exibir mensagem de erro quando a entidade não existe"() {
        given: "Um ID de entidade e as closures para verificar existência e realizar ação"
        Long idEntidade = 1L
        String nomeEntidadePrincipal = "Candidato"
        String tipoAcao = "atualizado(a)"
        def entidadeExiste = { Long id -> false }
        def acaoEntidade = Mock(Closure)

        when: "Chamamos o método manipularEntidade"
        candidatoService.manipularEntidade(idEntidade, nomeEntidadePrincipal, entidadeExiste, acaoEntidade, tipoAcao)

        then: "acaoEntidade não deve ser chamada"
        0 * acaoEntidade.call(_)

        and: "A mensagem de erro deve ser exibida"
        println("Erro: ${nomeEntidadePrincipal} com ID '${idEntidade}' não existe.")
    }

    def "Deve exibir mensagem de erro quando a entidade não existe"() {
        given: "Um ID de entidade e as closures para verificar existência e realizar ação"
        Long idEntidade = 1L
        String nomeEntidadePrincipal = "Empresa"
        String tipoAcao = "atualizado(a)"
        def entidadeExiste = { Long id -> false }
        def acaoEntidade = Mock(Closure)

        when: "Chamamos o método manipularEntidade"
        empresaService.manipularEntidade(idEntidade, nomeEntidadePrincipal, entidadeExiste, acaoEntidade, tipoAcao)

        then: "acaoEntidade não deve ser chamada"
        0 * acaoEntidade.call(_)

        and: "A mensagem de erro deve ser exibida"
        println("Erro: ${nomeEntidadePrincipal} com ID '${idEntidade}' não existe.")
    }


}
