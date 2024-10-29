package com.aczg.view.menus

import com.aczg.view.menus.interfaces.GeradorDeMenus
import com.aczg.view.menus.interfaces.IMenu
import com.aczg.view.menus.interfaces.IMenuCompetencia
import com.aczg.view.menus.interfaces.IMenuPrincipal

class MenuPrincipal implements IMenuPrincipal, GeradorDeMenus {

    private IMenu menuEmpresa
    private IMenu menuCandidato
    private IMenuCompetencia menuCompetencia
    private IMenu menuVaga

    MenuPrincipal() {}

    void setMenuEmpresa(IMenu menuEmpresa) {
        this.menuEmpresa = menuEmpresa
    }

    void setMenuCandidato(IMenu menuCandidato) {
        this.menuCandidato = menuCandidato
    }

    void setMenuCompetencia(IMenuCompetencia menuCompetencia) {
        this.menuCompetencia = menuCompetencia
    }

    void setMenuVaga(IMenu menuVaga) {
        this.menuVaga = menuVaga
    }


    private boolean aplicacaoExecutando = true

    void iniciar() {
        while (aplicacaoExecutando) {
            exibirMenuPrincipal()
        }
    }

    @Override
    void exibirMenuPrincipal() {
        println """            ================== MENU PRINCIPAL ==================
            1  - Gerenciar Empresas
            2  - Gerenciar Candidatos
            3  - Gerenciar Vagas
            4  - Gerenciar Competências
            0  - Encerrar Aplicação
            ====================================================                        
            """.stripIndent()
        try {
            String menuPrincipalOpcaoEscolhida = validarTexto("Digite o número correspondente a opção desejada: ")
            processarOpcao(menuPrincipalOpcaoEscolhida)
        } catch (Exception e) {
            println "Erro ao ler a entrada: ${e.message}"
        }
    }

    void processarOpcao(String opcao) {
        switch (opcao) {
            case '1': menuEmpresa.menuGerenciar(); break
            case '2': menuCandidato.menuGerenciar(); break
            case '3': menuVaga.menuGerenciar(); break
            case '4': menuCompetencia.menuGerenciar(); break
            case '0': encerrarAplicacao(); break
            default: println "Entrada inválida"; break
        }
    }

    void encerrarAplicacao() {
        aplicacaoExecutando = false
        println "Aplicação encerrada"
    }

}
