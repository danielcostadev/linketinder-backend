package com.aczg.view

import com.aczg.DAO.CandidatoDAO
import com.aczg.DAO.CompetenciaDAO
import com.aczg.DAO.EmpresaDAO
import com.aczg.DAO.VagaDAO
import com.aczg.controller.CandidatoController

import com.aczg.controller.EmpresaController
import com.aczg.controller.validadorEntrada
import com.aczg.service.CandidatoService
import com.aczg.service.EmpresaService

class Menu implements validadorEntrada{

    CandidatoDAO candidatoDAO = new CandidatoDAO()
    CompetenciaDAO competenciaDAO = new CompetenciaDAO()
    EmpresaDAO empresaDAO = new EmpresaDAO()
    VagaDAO vagaDAO = new VagaDAO()

    CandidatoService candidatoService = new CandidatoService(candidatoDAO, competenciaDAO)
    EmpresaService empresaService = new EmpresaService(empresaDAO, vagaDAO, competenciaDAO)

    CandidatoController candidatoController = new CandidatoController(candidatoService)
    EmpresaController empresaController = new EmpresaController(empresaService)

    Boolean menuAtivo = true

    private void exibirMenu(String tipo, List<String> opcoes, Closure<?> acoes, Closure<?> onVoltar) {
        while (menuAtivo) {
            print "================== MENU ${tipo.toUpperCase()} ==================\n"
            print "${opcoes.join('\n')}"
            print "\n0  - Voltar\n"
            52.times { print "="}
            println("")

            try {
                String opcao = validarTexto("Digite o número correspondente a opção desejada: ")

                switch (opcao) {
                    case '0': onVoltar.call(); break
                    default: acoes.call(opcao); break
                }
            } catch (Exception e) {
                println "Erro ao ler a entrada: ${e.message}"
            }
        }
    }

    void menuPrincipal(){
        while (menuAtivo){
            print """
            ================== MENU PRINCIPAL ==================
            1  - Gerenciar Empresas
            2  - Gerenciar Candidatos
            0  - Encerrar Aplicação
            ====================================================            
            """.stripIndent()

            try {
                String menuPrincipalOpcao = validarTexto("Digite o número correspondente a opção desejada: ")

                switch (menuPrincipalOpcao){

                    case '1': menuGerenciarEmpresas(); break
                    case '2': menuGerenciarCandidatos(); break
                    case '0': encerrarAplicacao(); break
                    default: println "Entrada inválida"; break

                }
            } catch (Exception e) {
                println "Erro ao ler a entrada: ${e.message}"
            }

        }
    }

    private void menuGerenciarEmpresas(){
        List<String> opcoes = [
                "1  - Cadastrar Empresa",
                "2  - Listar Empresas",
                "3  - Editar Empresa",
                "4  - Remover Empresa",
                "5  - Gerenciar Vagas"
        ]

        exibirMenu("Empresa", opcoes, { String opcao ->
            switch (opcao) {
                case '1': cadastrarEmpresa(); break
                case '2': listarEmpresas(); break
                case '3': editarEmpresa(); break
                case '4': deletarEmpresa(); break
                case '5': menuGerenciarVagas(); break
                default: println "Entrada inválida"; break
            }
        }, { menuPrincipal() })
    }
    private void menuGerenciarCandidatos(){
        List<String> opcoes = [
                "1  - Cadastrar Candidato",
                "2  - Listar Candidatos",
                "3  - Editar Candidato",
                "4  - Remover Candidato",
                "5  - Gerenciar Competências"
        ]

        exibirMenu("Candidato", opcoes, { String opcao ->
            switch (opcao) {
                case '1': cadastrarCandidato(); break
                case '2': listarCandidatos(); break
                case '3': editarCandidato(); break
                case '4': deletarCandidato(); break
                case '5': menuGerenciarCompetencias(); break
                default: println "Entrada inválida"; break
            }
        }, { menuPrincipal() })
    }
    private void menuGerenciarVagas(){
        List<String> opcoes = [
                "1  - Listar Vagas",
                "2  - Editar Vaga",
                "3  - Remover Vaga"
        ]

        exibirMenu("Vaga", opcoes, { String opcao ->
            switch (opcao) {
                case '1': listarVagas(); break
                case '2': editarVaga(); break
                case '3': deletarVaga(); break
                default: println "Entrada inválida"; break
            }
        }, { menuGerenciarEmpresas() })
    }
    private void menuGerenciarCompetencias(){
        List<String> opcoes = [
                "1  - Listar Competências",
                "2  - Editar Competência",
                "3  - Remover Competência"
        ]

        exibirMenu("Competência", opcoes, { String opcao ->
            switch (opcao) {
                case '1': listarCompetencias(); break
                case '2': editarCompetencia(); break
                case '3': deletarCompetencia(); break
                default: println "Entrada inválida"; break
            }
        }, { menuGerenciarCandidatos() })
    }

    private void cadastrarEmpresa(){
        getEmpresaController().adicionarEmpresa()
    }
    private void listarEmpresas(){
        getEmpresaController().exibirEmpresa()
    }
    private void editarEmpresa(){
        getEmpresaController().atualizarEmpresa()
    }
    private void deletarEmpresa(){
        getEmpresaController().deletarEmpresa()
    }

    private void cadastrarCandidato(){
        getCandidatoController().adicionarCandidato()
    }
    private void listarCandidatos(){
        getCandidatoController().exibirCandidado()
    }
    private void editarCandidato(){
        getCandidatoController().atualizarCandidato()
    }
    private void deletarCandidato(){
        getCandidatoController().deletarCandidato()
    }

    private void listarVagas(){
        getEmpresaController().exibirVaga()
    }
    private void editarVaga(){getEmpresaController().atualizarVaga()}
    private void deletarVaga(){
        getEmpresaController().deletarVaga()
    }

    private void listarCompetencias(){
        getCandidatoController().exibirCompetencias()
    }
    private void editarCompetencia(){getEmpresaController().atualizarCompetencia()}
    private void deletarCompetencia(){
        getEmpresaController().deletarCompetencia()
    }



    private void encerrarAplicacao(){
        println "Encerrando aplicação..."
        menuAtivo = false
        fecharScanner()
        System.exit(0);
    }


}
