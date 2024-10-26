package com.aczg.view.menus

import com.aczg.view.menus.interfaces.IMenu

class MenuEmpresa implements IMenu, GeradorDeMenus{

    private IMenu menuVaga

    MenuEmpresa(IMenu menuVaga){
        this.menuVaga = menuVaga
    }

    @Override
    void menuGerenciar() {

        List<String> opcoes = [
                "1  - Cadastrar Empresa",
                "2  - Listar Empresas",
                "3  - Editar Empresa",
                "4  - Remover Empresa",
                "5  - Gerenciar Vagas"
        ]

        exibirMenu("Empresa", opcoes, { String opcao ->
            switch (opcao) {
                case '1': cadastrar(); break
                case '2': listar(); break
                case '3': editar(); break
                case '4': remover(); break
                case '5': menuVaga.menuGerenciarVagas(); break
                default: println "Entrada inválida"; break
            }
        }, { menuPrincipal() })

    }

    @Override
    void cadastrar() {
        getEmpresaController().adicionarEmpresa()
    }

    @Override
    void listar() {
        getEmpresaController().listarEmpresas()
    }

    @Override
    void editar() {
        getEmpresaController().atualizarEmpresa()
    }

    @Override
    void remover() {
        getEmpresaController().removerEmpresa()
    }
}
