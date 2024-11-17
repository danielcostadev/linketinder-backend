package com.aczg.view;

import com.aczg.DAO.*;
import com.aczg.DAO.interfaces.*;
import com.aczg.controller.*
import com.aczg.controller.interfaces.ICandidatoController
import com.aczg.controller.interfaces.IEmpresaController
import com.aczg.controller.interfaces.IVagaController;
import com.aczg.interfaces.*;
import com.aczg.service.*
import com.aczg.service.interfaces.ICandidatoService
import com.aczg.service.interfaces.IEmpresaService
import com.aczg.service.interfaces.IVagaService;
import com.aczg.view.menus.*;
import com.aczg.view.menus.interfaces.*;
import com.aczg.view.telas.*;
import com.aczg.view.telas.interfaces.*;

class ConfiguracaoMenu {
    static MenuPrincipal configurar() {
        IMenu menuEmpresa = new MenuEmpresa();
        IMenu menuCandidato = new MenuCandidato();
        IMenu menuVaga = new MenuVaga();
        IMenuCompetencia menuCompetencia = new MenuCompetencia();
        IMenuPrincipal menuPrincipal = new MenuPrincipal();

        IEmpresaDAO empresaDAO = new EmpresaDAO();
        IEmpresaService empresaService = new EmpresaService(empresaDAO);
        IEmpresaController empresaController = new EmpresaController(empresaService);
        ITela empresaView = new EmpresaView(empresaController);

        ICandidatoDAO candidatoDAO = new CandidatoDAO();
        ICandidatoService candidatoService = new CandidatoService(candidatoDAO);
        ICandidatoController candidatoController = new CandidatoController(candidatoService);
        ITela candidatoView = new CandidatoView(candidatoController);

        IVagaDAO vagaDAO = new VagaDAO();
        IVagaService vagaService = new VagaService(vagaDAO);
        IVagaController vagaController = new VagaController(vagaService);
        ITela vagaView = new VagaView(vagaController, empresaController);

        ICompetenciaDAO competenciaDAO = new CompetenciaDAO();
        ICompetencia competenciaService = new CompetenciaService(competenciaDAO);
        ICompetencia competenciaController = new CompetenciaController(competenciaService);
        ICompetenciaView competenciaView = new CompetenciaView(competenciaController, candidatoController, vagaController);

        menuPrincipal.setMenuEmpresa(menuEmpresa);
        menuPrincipal.setMenuCandidato(menuCandidato);
        menuPrincipal.setMenuVaga(menuVaga);
        menuPrincipal.setMenuCompetencia(menuCompetencia);
        menuEmpresa.setMenuPrincipal(menuPrincipal);
        menuCandidato.setMenuPrincipal(menuPrincipal);
        menuVaga.setMenuPrincipal(menuPrincipal);
        menuCompetencia.setMenuPrincipal(menuPrincipal);
        menuEmpresa.setEmpresaView(empresaView);
        menuCandidato.setCandidatoView(candidatoView);
        menuVaga.setVagaview(vagaView);
        menuCompetencia.setCompetenciaView(competenciaView);
        menuPrincipal.exibirMenuPrincipal();

        return menuPrincipal;
    }
}
