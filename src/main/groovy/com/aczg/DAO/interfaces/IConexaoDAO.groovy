package com.aczg.DAO.interfaces

import groovy.sql.Sql

interface IConexaoDAO {

    Sql newInstance()
    Sql getSql()
    void close()

}