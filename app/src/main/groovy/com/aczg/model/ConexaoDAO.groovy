package com.aczg.model

import groovy.sql.Sql

class ConexaoDAO {

    private Sql sql

    static Sql newInstance(){
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

    void close(){
        if(sql){
            sql.close()
        }
    }

    Sql getSql(){
        if(!sql){
            sql = newInstance()
        }
        return sql
    }



}
