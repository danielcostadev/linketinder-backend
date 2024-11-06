package com.aczg.DAO.interfaces

import com.aczg.DAO.ConexaoDAO
import com.aczg.DAO.factory.ConexaoFactory
import groovy.sql.Sql

trait VerificarExistenciaDeEntidadeTrait {

    //TODO Recriar a lógica de veificação de existencia de entidades.

    private IConexaoDAO conexaoDAO = ConexaoFactory.getConexao("PostgreSQL")
    private Sql sql = conexaoDAO.getSql()

    boolean verificarExistencia(String tabela, Long id) {
        String query = "SELECT COUNT(*) FROM ${tabela} WHERE id = ?"
        int count = sql.firstRow(query, [id])?.count ?: 0
        return count > 0
    }

}