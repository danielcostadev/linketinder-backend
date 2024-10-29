package com.aczg.view

import com.aczg.view.menus.MenuPrincipal

class Main {

    static void main(String[] args) {

        MenuPrincipal menuPrincipal = ConfiguracaoMenu.configurar()
        menuPrincipal.iniciar()

    }
}
