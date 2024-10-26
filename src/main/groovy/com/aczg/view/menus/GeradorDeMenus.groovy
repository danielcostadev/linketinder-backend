package com.aczg.view.menus

import com.aczg.DAO.CandidatoDAO
import com.aczg.DAO.CompetenciaDAO
import com.aczg.DAO.EmpresaDAO
import com.aczg.DAO.VagaDAO
import com.aczg.controller.CandidatoController
import com.aczg.controller.EmpresaController
import com.aczg.controller.validadorEntradaTrait
import com.aczg.service.CandidatoService
import com.aczg.service.EmpresaService

trait GeradorDeMenus implements validadorEntradaTrait{

    CandidatoDAO candidatoDAO = new CandidatoDAO()
    CompetenciaDAO competenciaDAO = new CompetenciaDAO()
    EmpresaDAO empresaDAO = new EmpresaDAO()
    VagaDAO vagaDAO = new VagaDAO()

    CandidatoService candidatoService = new CandidatoService(candidatoDAO)
    EmpresaService empresaService = new EmpresaService(empresaDAO)

    CandidatoController candidatoController = new CandidatoController(candidatoService)
    EmpresaController empresaController = new EmpresaController(empresaService)

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