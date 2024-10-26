package com.aczg.view.menus

import com.aczg.view.menus.interfaces.IMenu

class MenuCompetencia implements IMenu, GeradorDeMenus{

    @Override
    void menuGerenciar(){
        List<String> opcoes = [
                "1  - Listar Competências",
                "2  - Editar Competência",
                "3  - Remover Competência"
        ]

        exibirMenu("Competência", opcoes, { String opcao ->
            switch (opcao) {
                case '1': listar(); break
                case '2': editar(); break
                case '3': remover(); break
                default: println "Entrada inválida"; break
            }
        }, { menuPrincipal() })
    }

    @Override
    void cadastrar(){
        getCompetenciaController().listarCompetencias()
    }

    @Override
    void listar(){
        getCompetenciaController().listarCompetencias()
    }

    @Override
    void editar(){
        getCompetenciaController().atualizarCompetencia()
    }

    @Override
    void remover(){
        getCompetenciaController().removerCompetencia()
    }
}
