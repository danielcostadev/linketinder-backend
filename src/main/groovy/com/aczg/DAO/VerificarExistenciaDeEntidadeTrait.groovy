package com.aczg.DAO

import groovy.sql.Sql

trait VerificarExistenciaDeEntidadeTrait {

    private ConexaoDAO conexaoDAO = new ConexaoDAO()

    Sql sql = conexaoDAO.getSql()

    boolean verificarExistencia(String tabela, Long id) {
        String query = "SELECT COUNT(*) FROM ${tabela} WHERE id = ?"
        def count = sql.firstRow(query, [id])?.count ?: 0
        return count > 0
    }

}