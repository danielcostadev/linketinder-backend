package com.aczg.view.menus.interfaces

import com.aczg.view.interfaces.ValidadorEntradaTrait

trait GeradorDeMenus implements ValidadorEntradaTrait{

    Boolean aplicacaoExecutando = true
    Stack<Closure<?>> historicoMenus = new Stack<>()

    void exibirMenu(String tipo, List<String> opcoes, Closure<?> acoes, Closure<?> onVoltar) {
        historicoMenus.push(onVoltar)
        while (aplicacaoExecutando) {
            print "================== MENU ${tipo.toUpperCase()} ==================\n"
            print "${opcoes.join('\n')}"
            print "\n0  - Voltar\n"
            52.times { print "="}
            println("")

            try {
                String opcao = validarTexto("Digite o número correspondente a opção desejada: ")

                switch (opcao) {
                    case '0': historicoMenus.pop().call(); return
                    default: acoes.call(opcao); break
                }
            } catch (Exception e) {
                println "Erro ao ler a entrada: ${e.message}"
            }
        }
    }

}