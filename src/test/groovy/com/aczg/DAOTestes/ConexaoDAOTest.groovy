package com.aczg.DAOTestes

import com.aczg.DAO.ConexaoDAO
import com.aczg.DAO.factory.ConexaoFactory
import com.aczg.DAO.interfaces.IConexaoDAO
import groovy.sql.Sql
import spock.lang.Specification

class ConexaoDAOTest extends Specification{

     def "Testa a conexão Postgres com o Mock do banco de dados" (){
        given: "Um mock da classe Sql"
        Sql sqlMock = Mock(Sql)
        IConexaoDAO dao = ConexaoFactory.getConexao("PostgreSQL")

        when: "Chama o método que usa o Sql"
        dao.getSql() >> sqlMock

        then: "A conexão deve ser fechada"
        assert dao.getSql() != null
        dao.close()
    }

}
