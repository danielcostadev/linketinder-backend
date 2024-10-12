package com.aczg.DAOTestes

import com.aczg.DAO.ConexaoDAO
import groovy.sql.Sql
import spock.lang.Specification

class ConexaoDAOTest extends Specification{

     def "Testa a conexão com o Mock do banco de dados" (){
        given: "Um mock da classe Sql"
        def sqlMock = Mock(Sql)
        ConexaoDAO dao = new ConexaoDAO()

        when: "Chama o método que usa o Sql"
        dao.getSql() >> sqlMock

        then: "A conexão deve ser fechada"
        assert dao.getSql() != null
        dao.close()
    }

}
