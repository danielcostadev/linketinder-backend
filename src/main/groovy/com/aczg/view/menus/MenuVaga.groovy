package com.aczg.view.menus

import com.aczg.view.menus.interfaces.IMenu

class MenuVaga implements IMenu, GeradorDeMenus{

    @Override
    void menuGerenciar() {
        List<String> opcoes = [
                "1  - Cadastrar Vaga",
                "2  - Listar Vagas",
                "3  - Editar Vaga",
                "4  - Remover Vaga"
        ]
        exibirMenu("Vaga", opcoes, { String opcao ->
            switch (opcao) {
                case '1': cadastrar(); break
                case '2': listar(); break
                case '3': editar(); break
                case '4': remover(); break
                default: println "Entrada inválida"; break
            }
        }, { menuGerenciarEmpresas() })
    }

    @Override
    void cadastrar(){
        getEmpresaController().adicionarVaga()
    }

    @Override
    void listar(){
        getEmpresaController().listarVagas()
    }

    @Override
    void editar(){
        getEmpresaController().atualizarVaga()
    }

    @Override
    void remover() {
        getEmpresaController().removerVaga()
    }
}
