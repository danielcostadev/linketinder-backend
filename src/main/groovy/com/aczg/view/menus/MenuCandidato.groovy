package com.aczg.view.menus

import com.aczg.view.menus.interfaces.GeradorDeMenus
import com.aczg.view.menus.interfaces.IMenu
import com.aczg.view.menus.interfaces.IMenuPrincipal
import com.aczg.view.telas.interfaces.IEntidadeCRUDView

class MenuCandidato implements IMenu, GeradorDeMenus{

    private IMenuPrincipal menuPrincipal
    private IEntidadeCRUDView candidatoView

    void setCandidatoView(IEntidadeCRUDView candidatoView){
        this.candidatoView = candidatoView
    }

    @Override
    void setMenuPrincipal(IMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal
    }

    @Override
    void menuGerenciar(){
        List<String> opcoes = [
                "1  - Cadastrar Candidato",
                "2  - Listar Candidatos",
                "3  - Editar Candidato",
                "4  - Remover Candidato"
        ]

        exibirMenu("Candidato", opcoes, { String opcao ->
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
        candidatoView.exibirFormularioDeCadastro()
    }

    @Override
    void listar(){
        candidatoView.exibirLista()
    }

    @Override
    void editar(){
        candidatoView.exibirFormulariodeEdicao()
    }

    @Override
    void remover(){
        candidatoView.exibirFormulariodeExclusao()
    }
}
