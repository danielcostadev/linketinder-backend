package com.aczg.view

import com.aczg.DAO.EmpresaDAO
import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.controller.EmpresaController
import com.aczg.controller.interfaces.IEntidadeController
import com.aczg.service.EmpresaService
import com.aczg.service.interfaces.IEntidadeService
import com.aczg.view.telas.EmpresaView
import com.aczg.view.telas.interfaces.IEntidadeView
import com.aczg.view.menus.MenuCandidato
import com.aczg.view.menus.MenuCompetencia
import com.aczg.view.menus.MenuEmpresa
import com.aczg.view.menus.MenuPrincipal
import com.aczg.view.menus.MenuVaga
import com.aczg.view.menus.interfaces.IMenu
import com.aczg.view.menus.interfaces.IMenuPrincipal

class ConfiguracaoMenu {

    static MenuPrincipal configurar() {
        IMenu menuEmpresa = new MenuEmpresa()
        IMenu menuCandidato = new MenuCandidato()
        IMenu menuVaga = new MenuVaga()
        IMenu menuCompetencia = new MenuCompetencia()
        IMenuPrincipal menuPrincipal = new MenuPrincipal()

        IEntidadeDAO empresaDAO = new EmpresaDAO()
        IEntidadeService empresaService = new EmpresaService(empresaDAO)
        IEntidadeController empresaController = new EmpresaController(empresaService)
        IEntidadeView empresaView = new EmpresaView(empresaController)

        menuPrincipal.setMenuEmpresa(menuEmpresa)
        menuPrincipal.setMenuCandidato(menuCandidato)
        menuPrincipal.setMenuVaga(menuVaga)
        menuPrincipal.setMenuCompetencia(menuCompetencia)

        menuEmpresa.setMenuPrincipal(menuPrincipal)
        menuCandidato.setMenuPrincipal(menuPrincipal)
        menuVaga.setMenuPrincipal(menuPrincipal)
        menuCompetencia.setMenuPrincipal(menuPrincipal)

        menuEmpresa.setEmpresaView(empresaView)

        menuPrincipal.exibirMenuPrincipal()

        return menuPrincipal
    }

}
