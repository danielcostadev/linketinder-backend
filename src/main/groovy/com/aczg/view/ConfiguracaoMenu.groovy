package com.aczg.view;

import com.aczg.DAO.*;
import com.aczg.DAO.interfaces.*;
import com.aczg.controller.*;
import com.aczg.interfaces.*;
import com.aczg.service.*;
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

        IEntidadeDAO empresaDAO = new EmpresaDAO();
        IEntidade empresaService = new EmpresaService(empresaDAO);
        IEntidade empresaController = new EmpresaController(empresaService);
        IEntidadeCRUDView empresaView = new EmpresaView(empresaController);

        IEntidadeDAO candidatoDAO = new CandidatoDAO();
        IEntidade candidatoService = new CandidatoService(candidatoDAO);
        IEntidade candidatoController = new CandidatoController(candidatoService);
        IEntidadeCRUDView candidatoView = new CandidatoView(candidatoController);

        IEntidadeDAO vagaDAO = new VagaDAO();
        IEntidade vagaService = new VagaService(vagaDAO);
        IEntidade vagaController = new VagaController(vagaService);
        IEntidadeCRUDView vagaView = new VagaView(vagaController, empresaController);

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
