package com.aczg.view.menus

import com.aczg.view.menus.interfaces.GeradorDeMenus
import com.aczg.view.menus.interfaces.IMenu
import com.aczg.view.menus.interfaces.IMenuPrincipal

class MenuPrincipal implements IMenuPrincipal, GeradorDeMenus{

    private IMenu menuEmpresa
    private IMenu menuCandidato
    private IMenu menuCompetencia
    private IMenu menuVaga

    MenuPrincipal() {}

    void setMenuEmpresa(IMenu menuEmpresa) {
        this.menuEmpresa = menuEmpresa
    }

    void setMenuCandidato(IMenu menuCandidato) {
        this.menuCandidato = menuCandidato
    }

    void setMenuCompetencia(IMenu menuCompetencia){
        this.menuCompetencia = menuCompetencia
    }

    void setMenuVaga(IMenu menuVaga){
        this.menuVaga = menuVaga
    }

    @Override
    void exibirMenuPrincipal(){
        while (aplicacaoExecutando){
            print """
            ================== MENU PRINCIPAL ==================
            1  - Gerenciar Empresas
            2  - Gerenciar Candidatos
            3  - Gerencias Vagas
            4  - Gerenciar Competências
            0  - Encerrar Aplicação
            ====================================================            
            """.stripIndent()

            try {
                String menuPrincipalOpcaoEscolhida = validarTexto("Digite o número correspondente a opção desejada: ")

                switch (menuPrincipalOpcaoEscolhida){

                    case '1': menuEmpresa.menuGerenciar(); break
                    case '2': menuCandidato.menuGerenciar(); break
                    case '3': menuVaga.menuGerenciar(); break
                    case '4': menuCompetencia.menuGerenciar(); break
                    case '0': encerrarAplicacao(); break
                    default: println "Entrada inválida"; break

                }
            } catch (Exception e) {
                println "Erro ao ler a entrada: ${e.message}"
            }

        }
    }

    private void encerrarAplicacao(){
        println "Encerrando aplicação..."
        aplicacaoExecutando = false
        fecharScanner()
        System.exit(0);
    }


}
