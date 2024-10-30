package com.aczg.DAO

import com.aczg.DAO.interfaces.IConexaoDAO
import groovy.sql.Sql

class ConexaoDAO implements IConexaoDAO{

    private Sql sql

    @Override
    Sql newInstance(){
        final String url = 'jdbc:postgresql://localhost:5432/linketinder_testes'
        final String user = 'user123'
        final String password = 'pass123'
        final String driver = 'org.postgresql.Driver'

        try{
            return Sql.newInstance(url, user, password, driver)
        } catch (Exception e){
            print("Erro ao estabelecer conexão com o banco de dados! ${e.getMessage()}")
            return null
        }
    }
    @Override
    void close(){
        if(sql){
            sql.close()
        }
    }

    @Override
    Sql getSql(){
        if(!sql){
            sql = newInstance()
        }
        return sql
    }

}
