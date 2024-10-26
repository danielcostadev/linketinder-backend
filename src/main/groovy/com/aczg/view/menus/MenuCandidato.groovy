package com.aczg.view.menus

import com.aczg.view.menus.interfaces.IMenuCandidato

class MenuCandidato implements IMenuCandidato, GeradorDeMenus{

    @Override
    void menuGerenciarCandidatos(){
        List<String> opcoes = [
                "1  - Cadastrar Candidato",
                "2  - Listar Candidatos",
                "3  - Editar Candidato",
                "4  - Remover Candidato"
        ]

        exibirMenu("Candidato", opcoes, { String opcao ->
            switch (opcao) {
                case '1': cadastrarCandidato(); break
                case '2': listarCandidatos(); break
                case '3': editarCandidato(); break
                case '4': removerCandidato(); break
                default: println "Entrada inválida"; break
            }
        }, { menuPrincipal() })
    }

    @Override
    void cadastrarCandidato(){
        getCandidatoController().adicionarCandidato()
    }

    @Override
    void listarCandidatos(){
        getCandidatoController().listarCandidatos()
    }

    @Override
    void editarCandidato(){
        getCandidatoController().atualizarCandidato()
    }

    @Override
    void removerCandidato(){
        getCandidatoController().removerCandidato()
    }
}
