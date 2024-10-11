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

    void menuPrincipal(){
        while (menuAtivo){
            /*print """
            ================== MENU PRINCIPAL ==================
            1  -
            2  -
            3  -
            4  -
            5  - Mostrar Vagas
            6  - Mostrar Competências
            7  -
            8  -
            9  -
            10 -
            11 - Deletar Vaga
            12 - Deletar Competência
            0 - Encerrar Aplicação
            ====================================================            
            """.stripIndent()*/

            print """
            ================== MENU PRINCIPAL ==================
            1  - Gerenciar Empresas
            2  - Gerenciar Candidatos

            0 - Encerrar Aplicação
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
        while (menuAtivo){
            print """
            =================== MENU EMPRESA ===================
            1  - Cadastrar Empresa
            2  - Listar Empresas
            3  - Editar Empresa
            4  - Remover Empresa
            5  - Gerenciar Vagas

            0 - Voltar
            ====================================================            
            """.stripIndent()

            try {
                String menuEmpresaOpcao = validarTexto("Digite o número correspondente a opção desejada: ")

                switch (menuEmpresaOpcao){

                    case '1': cadastrarEmpresa(); break
                    case '2': mostrarEmpresas(); break
                    case '3': editarEmpresa(); break
                    case '4': deletarEmpresa(); break
                    case '5': menuGerenciarVagas(); break
                    case '0': menuPrincipal(); break
                    default: println "Entrada inválida"; break

                }
            } catch (Exception e) {
                println "Erro ao ler a entrada: ${e.message}"
            }
        }
    }
    private void menuGerenciarCandidatos(){
        while (menuAtivo){
            print """
            ================== MENU CANDIDATO ==================
            1  - Cadastrar Candidato
            2  - Listar Candidatos
            3  - Editar Candidato
            4  - Remover Candidato
            5  - Gerenciar Competências

            0 - Voltar
            ====================================================            
            """.stripIndent()

            try {
                String menuCandidatoOpcao = validarTexto("Digite o número correspondente a opção desejada: ")

                switch (menuCandidatoOpcao){

                    case '1': cadastrarCandidato(); break
                    case '2': mostrarCandidatos(); break
                    case '3': editarCandidato(); break
                    case '4': deletarCandidato(); break
                    case '5': menuGerenciarCompetencias(); break
                    case '0': menuPrincipal(); break
                    default: println "Entrada inválida"; break

                }
            } catch (Exception e) {
                println "Erro ao ler a entrada: ${e.message}"
            }
        }
    }
    private void menuGerenciarVagas(){
        while (menuAtivo){
            print """
            ================== MENU VAGA ==================
            1  - Listar Vagas
            2  - Editar Vaga
            3  - Remover Vaga

            0 - Voltar
            ====================================================            
            """.stripIndent()

            try {
                String menuVagaOpcao = validarTexto("Digite o número correspondente a opção desejada: ")

                switch (menuVagaOpcao){

                    case '1': mostrarVagas(); break
                    case '2': editarVaga(); break
                    case '3': deletarVaga(); break
                    case '0': menuGerenciarEmpresas(); break
                    default: println "Entrada inválida"; break

                }
            } catch (Exception e) {
                println "Erro ao ler a entrada: ${e.message}"
            }
        }
    }
    private void menuGerenciarCompetencias(){
        while (menuAtivo){
            print """
            ================== MENU VAGA ==================
            1  - Listar Competencias
            2  - Editar Competencia
            3  - Remover Competencia

            0 - Voltar
            ====================================================            
            """.stripIndent()

            try {
                String menuCompetenciasOpcao = validarTexto("Digite o número correspondente a opção desejada: ")

                switch (menuCompetenciasOpcao){

                    case '1': mostrarCompetencias(); break
                    case '2': editarCompetencia(); break
                    case '3': deletarCompetencia(); break
                    case '0': menuGerenciarCandidatos(); break
                    default: println "Entrada inválida"; break

                }
            } catch (Exception e) {
                println "Erro ao ler a entrada: ${e.message}"
            }
        }
    }

    private void cadastrarEmpresa(){
        getEmpresaController().adicionarEmpresa()
    }
    private void cadastrarCandidato(){
        getCandidatoController().adicionarCandidato()
    }
    private void mostrarEmpresas(){
        getEmpresaController().exibirEmpresa()
    }
    private void mostrarCandidatos(){
        getCandidatoController().exibirCandidado()
    }
    private void mostrarVagas(){
        getEmpresaController().exibirVaga()
    }
    private void mostrarCompetencias(){
        getCandidatoController().exibirCompetencias()
    }
    private void editarEmpresa(){
        getEmpresaController().atualizarEmpresa()
    }
    private void editarCandidato(){
        getCandidatoController().atualizarCandidato()
    }

    private void encerrarAplicacao(){
        println "Encerrando aplicação..."
        menuAtivo = false
        fecharScanner()
        System.exit(0);
    }


}
