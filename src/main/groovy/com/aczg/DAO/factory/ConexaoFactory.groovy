package com.aczg.DAO.factory

import com.aczg.DAO.interfaces.IConexaoDAO

class ConexaoFactory {

    static IConexaoDAO getConexao(String tipo){
        switch (tipo){
            case "PostgreSQL" :
                return ConexaoPostgresDAO.getInstance()
            case "MySQL" :
                return null
            case "Oracle" :
                return null
            default:
                print "Tipo de conexão desconhecido: ${tipo}"
        }
    }

}
