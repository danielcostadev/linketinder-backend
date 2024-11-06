package com.aczg.view.menus

import com.aczg.view.menus.interfaces.GeradorDeMenus
import com.aczg.view.menus.interfaces.IMenu
import com.aczg.view.menus.interfaces.IMenuPrincipal
import com.aczg.view.telas.interfaces.ITela

class MenuVaga implements IMenu, GeradorDeMenus{

    private IMenuPrincipal menuPrincipal
    private ITela vagaView

    void setVagaview(ITela vagaView){
        this.vagaView = vagaView
    }

    @Override
    void setMenuPrincipal(IMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal
    }

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
        }, { menuPrincipal.exibirMenuPrincipal() })
    }

    @Override
    void cadastrar(){
        vagaView.exibirFormularioDeCadastro()
    }

    @Override
    void listar(){
        vagaView.exibirLista()
    }

    @Override
    void editar(){
        vagaView.exibirFormulariodeEdicao()
    }

    @Override
    void remover() {
        vagaView.exibirFormulariodeExclusao()
    }
}
