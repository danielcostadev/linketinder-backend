package com.aczg.view.menus


import com.aczg.view.telas.interfaces.IEntidadeView
import com.aczg.view.menus.interfaces.GeradorDeMenus
import com.aczg.view.menus.interfaces.IMenu
import com.aczg.view.menus.interfaces.IMenuPrincipal

class MenuEmpresa implements IMenu, GeradorDeMenus{

    private IMenuPrincipal menuPrincipal
    private IEntidadeView empresaView

    void setEmpresaView(IEntidadeView empresaView){
        this.empresaView = empresaView
    }

    @Override
    void setMenuPrincipal(IMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal
    }

    @Override
    void menuGerenciar() {

        List<String> opcoes = [
                "1  - Cadastrar Empresa",
                "2  - Listar Empresas",
                "3  - Editar Empresa",
                "4  - Remover Empresa"
        ]

        exibirMenu("Empresa", opcoes, { String opcao ->
            switch (opcao) {
                case '1': cadastrar(); break
                case '2': listar(); break
                case '3': editar(); break
                case '4': remover(); break
                default: println "Entrada inválida"; break
            }
        }, { menuPrincipal.exibirMenuPrincipal() })

    }

    @Override
    void cadastrar() {
        empresaView.exibirFormularioDeCadastro()
    }

    @Override
    void listar() {
        empresaView.exibirListaDeEmpresas()
    }

    @Override
    void editar() {
        empresaView.exibirFormulariodeEdicao()
    }

    @Override
    void remover() {
        empresaView.exibirFormulariodeExclusao()
    }

}
