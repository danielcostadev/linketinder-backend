package com.aczg.view

import com.aczg.DAO.CandidatoDAO
import com.aczg.DAO.EmpresaDAO
import com.aczg.controller.CandidatoController
import com.aczg.controller.EmpresaController
import com.aczg.controller.validadorEntrada
import com.aczg.service.CandidatoService
import com.aczg.service.EmpresaService

class Menu implements validadorEntrada{

    CandidatoDAO candidatoDAO = new CandidatoDAO()
    CandidatoService candidatoService = new CandidatoService(candidatoDAO)
    CandidatoController candidatoController = new CandidatoController(candidatoService)

    EmpresaDAO empresaDAO = new EmpresaDAO()
    EmpresaService empresaService = new EmpresaService(empresaDAO)
    EmpresaController empresaController = new EmpresaController(empresaService)

    Boolean menuAtivo = true

    void gerarMenu(){
        while (menuAtivo){
            print """
            ================== MENU PRINCIPAL ==================
            1 - Cadastrar Empresa
            2 - Cadastrar Candidato
            3 - Listar Empresas
            4 - Listar Candidatos
            5 - Encerrar Aplicação
            ====================================================            
            """.stripIndent()

            try{
                String opcaoSelecionada = validarTexto("Digite um número entre 1 e 5:")

                switch (opcaoSelecionada){

                    case '1':
                        cadastrarEmpresa()
                        break
                    case '2':
                        cadastrarCandidato()
                        break

                }

            } catch (Exception e) {
                println "Erro ao ler a entrada: ${e.message}"
            }
        }
    }

    private void cadastrarEmpresa(){
        empresaController.adicionarEmpresa()
    }
    private void cadastrarCandidato(){
        candidatoController.adicionarCandidato()
    }



}
