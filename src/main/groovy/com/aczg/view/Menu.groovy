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

    void gerarMenu(){
        while (menuAtivo){
            print """
            ================== MENU PRINCIPAL ==================
            1  - Cadastrar Empresa
            2  - Cadastrar Candidato
            3  - Mostrar Empresas
            4  - Mostrar Candidatos
            5  - Mostrar Vagas
            6  - Mostrar Competências
            7  - Editar Empresa
            8  - Editar Candidato
            9  - Deletar Empresa
            10 - Deletar Candidato
            11 - Deletar Vaga
            12 - Deletar Competência
            0 - Encerrar Aplicação
            ====================================================            
            """.stripIndent()

            try{
                String opcaoSelecionada = validarTexto("Digite um número entre 1 e 12: ")

                switch (opcaoSelecionada){

                    case '1': cadastrarEmpresa(); break
                    case '2': cadastrarCandidato(); break
                    case '3': mostrarEmpresas(); break
                    case '4': mostrarCandidatos(); break
                    case '5': mostrarVagas(); break
                    case '6': mostrarCompetencias(); break
                    case '7': editarEmpresa(); break
                    case '8': editarCandidato(); break
                    case '9': deletarEmpresa(); break
                    case '10': deletarCandidato(); break
                    case '11': deletarVaga(); break
                    case '12': deletarCompetencia(); break
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



}
