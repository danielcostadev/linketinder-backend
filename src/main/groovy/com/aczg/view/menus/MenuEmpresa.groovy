package com.aczg.view.menus

import com.aczg.view.menus.interfaces.IMenuEmpresa
import com.aczg.view.menus.interfaces.IMenuVaga

class MenuEmpresa implements IMenuEmpresa, GeradorDeMenus{

    private IMenuVaga menuVaga

    MenuEmpresa(IMenuVaga menuVaga){
        this.menuVaga = menuVaga
    }

    @Override
    void menuGerenciarEmpresas() {

        List<String> opcoes = [
                "1  - Cadastrar Empresa",
                "2  - Listar Empresas",
                "3  - Editar Empresa",
                "4  - Remover Empresa",
                "5  - Gerenciar Vagas"
        ]

        exibirMenu("Empresa", opcoes, { String opcao ->
            switch (opcao) {
                case '1': cadastrarEmpresa(); break
                case '2': listarEmpresas(); break
                case '3': editarEmpresa(); break
                case '4': removerEmpresa(); break
                case '5': menuVaga.menuGerenciarVagas(); break
                default: println "Entrada inválida"; break
            }
        }, { menuPrincipal() })

    }

    @Override
    void cadastrarEmpresa() {
        getEmpresaController().adicionarEmpresa()
    }

    @Override
    void listarEmpresas() {
        getEmpresaController().listarEmpresas()
    }

    @Override
    void editarEmpresa() {
        getEmpresaController().atualizarEmpresa()
    }

    @Override
    void removerEmpresa() {
        getEmpresaController().removerEmpresa()
    }
}
