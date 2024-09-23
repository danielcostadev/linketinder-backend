package com.aczg.testes

import com.aczg.controller.validadorEntrada
import spock.lang.Specification


class ValidarEntradaDadosTeste extends Specification implements validadorEntrada {

    def "Valida se as entradas de textos estão sendo tratadas corretamente" (){
        given:"uma simulação de entrada de texto para validar a resposta"
        def entradaScanner = new Scanner(new ByteArrayInputStream("João\n".bytes))

        when: "validando resposta"
        def nome = validarTexto("Digite seu nome: ", entradaScanner)

        then: "A resposta deve ser uma String"
        nome instanceof String
    }

    def "Valida se as entradas de inteiros estão sendo tratadas corretamente" (){
        given: "uma simulação de entrada de inteiros para validar a resposta"
        def entradaScanner = new Scanner(new ByteArrayInputStream("25\n".bytes))

        when: "validando a resposta"
        def idade = validarInteiro("Digite sua idade: ", entradaScanner)

        then: "A resposta deve ser um inteiro"
        idade instanceof Integer

    }

    def "Valida se as entradas de texto estão sendo tratadas, separads e adicionadas a lista corretamente" (){
        given: "uma simulação de entrada de textos, separados por virgula"
        def entradaScanner = new Scanner(new ByteArrayInputStream("competencia1, competencia2, competencia3, outra competencia\n".bytes))

        when: "validando a resposta"
        def competencias = validarTexto("Digite as competências separadas por virgula: ", entradaScanner)

        and: "O texto deve ser convertido em elementos da lista de competências, tendo como delimitador a virgula"
        List<String> listaCompetencias = competencias.split(",\\s*")

        then: "A resposta deve ser uma String"
        competencias instanceof String

        and: "A lista deve conter as compencias adicionadas"
        listaCompetencias.contains("competencia1")
        listaCompetencias.contains("competencia3")
    }

}
