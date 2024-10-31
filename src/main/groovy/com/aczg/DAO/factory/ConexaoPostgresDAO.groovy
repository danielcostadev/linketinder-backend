package com.aczg.DAO.factory


import com.aczg.DAO.interfaces.IConexaoDAO
import groovy.sql.Sql

class ConexaoPostgresDAO implements IConexaoDAO {

    private static final IConexaoDAO instance = new ConexaoPostgresDAO()
    private Sql sql

    private ConexaoPostgresDAO(){}

    static ConexaoPostgresDAO getInstance(){
        return instance
    }

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
    Sql getSql(){
        if(!sql){
            sql = newInstance()
        }
        return sql
    }

    @Override
    void close(){
        if(sql){
            sql.close()
        }
    }
}
