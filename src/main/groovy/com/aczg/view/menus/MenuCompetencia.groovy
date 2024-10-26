package com.aczg.view.menus

import com.aczg.view.menus.interfaces.IMenuCompetencia

class MenuCompetencia implements IMenuCompetencia, GeradorDeMenus{

    @Override
    void menuGerenciarCompetencias(){
        List<String> opcoes = [
                "1  - Listar Competências",
                "2  - Editar Competência",
                "3  - Remover Competência"
        ]

        exibirMenu("Competência", opcoes, { String opcao ->
            switch (opcao) {
                case '1': listarCompetencias(); break
                case '2': editarCompetencia(); break
                case '3': removerCompetencia(); break
                default: println "Entrada inválida"; break
            }
        }, { menuPrincipal() })
    }

    @Override
    void listarCompetencias(){
        getEmpresaController().listarCompetencias() // Mudar
    }

    @Override
    void editarCompetencia(){
        getEmpresaController().atualizarCompetencia()
    }

    @Override
    void removerCompetencia(){
        getEmpresaController().removerCompetencia()
    }
}
