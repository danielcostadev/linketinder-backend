package com.aczg.view.menus

import com.aczg.view.menus.interfaces.IMenuVaga

class MenuVaga implements IMenuVaga, GeradorDeMenus{

    @Override
    void menuGerenciarVagas() {
        List<String> opcoes = [
                "1  - Cadastrar Vaga",
                "2  - Listar Vagas",
                "3  - Editar Vaga",
                "4  - Remover Vaga"
        ]
        exibirMenu("Vaga", opcoes, { String opcao ->
            switch (opcao) {
                case '1': cadastrarVaga(); break
                case '2': listarVagas(); break
                case '3': editarVaga(); break
                case '4': removerVaga(); break
                default: println "Entrada inválida"; break
            }
        }, { menuGerenciarEmpresas() })
    }

    @Override
    void cadastrarVaga(){
        getEmpresaController().adicionarVaga()
    }

    @Override
    void listarVagas(){
        getEmpresaController().listarVagas()
    }

    @Override
    void editarVaga(){
        getEmpresaController().atualizarVaga()
    }

    @Override
    void removerVaga() {
        getEmpresaController().removerVaga()
    }
}
