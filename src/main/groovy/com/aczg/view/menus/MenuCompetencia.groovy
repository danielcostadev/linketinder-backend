package com.aczg.view.menus

import com.aczg.view.menus.interfaces.GeradorDeMenus
import com.aczg.view.menus.interfaces.IMenuCompetencia
import com.aczg.view.menus.interfaces.IMenuPrincipal
import com.aczg.view.telas.interfaces.ICompetenciaView

class MenuCompetencia implements IMenuCompetencia, GeradorDeMenus{

    private IMenuPrincipal menuPrincipal
    private ICompetenciaView competenciaView

    void setCompetenciaView(ICompetenciaView competenciaView){
        this.competenciaView = competenciaView
    }

    @Override
    void setMenuPrincipal(IMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal
    }

    @Override
    void menuGerenciar(){
        List<String> opcoes = [
                "1  - Cadastrar Competência em candidato",
                "2  - Cadastrar Competência em vaga",
                "3  - Listar Competencias"
        ]

        exibirMenu("Competência", opcoes, { String opcao ->
            switch (opcao) {
                case '1': cadastrarCompetenciaEmCandidato(); break
                case '2': cadastrarCompetenciaEmVaga(); break
                case '3': listar(); break
                default: println "Entrada inválida"; break
            }
        }, { menuPrincipal.exibirMenuPrincipal() })
    }

    @Override
    void cadastrarCompetenciaEmCandidato() {
        competenciaView.exibirFormularioDeCadastroCandidato()
    }

    @Override
    void cadastrarCompetenciaEmVaga() {
        competenciaView.exibirFormularioDeCadastroVaga()
    }

    @Override
    void listar() {
        competenciaView.exibirLista()
    }
}