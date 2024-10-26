package com.aczg.view.menus

import com.aczg.view.menus.interfaces.IMenuCandidato
import com.aczg.view.menus.interfaces.IMenuCompetencia
import com.aczg.view.menus.interfaces.IMenuEmpresa
import com.aczg.view.menus.interfaces.IMenuPrincipal

class MenuPrincipal implements IMenuPrincipal, GeradorDeMenus{

    private IMenuEmpresa menuEmpresa
    private IMenuCandidato menuCandidato
    private IMenuCompetencia menuCompetencia

    MenuPrincipal(IMenuEmpresa menuEmpresa, IMenuCandidato menuCandidato, IMenuCompetencia menuCompetencia) {
        this.menuEmpresa = menuEmpresa
        this.menuCandidato = menuCandidato
        this.menuCompetencia = menuCompetencia
    }

    @Override
    void exibirMenuPrincipal(){
        while (aplicacaoExecutando){
            print """
            ================== MENU PRINCIPAL ==================
            1  - Gerenciar Empresas
            2  - Gerenciar Candidatos
            3  - Gerenciar Competências
            0  - Encerrar Aplicação
            ====================================================            
            """.stripIndent()

            try {
                String menuPrincipalOpcaoEscolhida = validarTexto("Digite o número correspondente a opção desejada: ")

                switch (menuPrincipalOpcaoEscolhida){

                    case '1': menuEmpresa.menuGerenciarEmpresas(exibirMenuPrincipal()); break
                    case '2': menuCandidato.menuGerenciarCandidatos(exibirMenuPrincipal()); break
                    case '3': menuCompetencia.menuGerenciarCompetencias(exibirMenuPrincipal()); break
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
