package com.aczg.DAO

import com.aczg.DAO.factory.ConexaoFactory
import com.aczg.DAO.interfaces.IConexaoDAO
import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.DAO.interfaces.VerificarExistenciaDeEntidadeTrait
import com.aczg.exceptions.DatabaseException
import com.aczg.model.Vaga
import groovy.sql.Sql

import java.sql.SQLException

class VagaDAO implements IEntidadeDAO<Vaga>, VerificarExistenciaDeEntidadeTrait{

    private IConexaoDAO conexaoDAO = ConexaoFactory.getConexao("PostgreSQL")
    private Sql sql = conexaoDAO.getSql()

    @Override
    Long cadastrar(Vaga vaga) throws DatabaseException {

        try {

            String queryVagas = '''
            INSERT INTO vagas (nome, descricao, local, empresa_id)
            VALUES (?, ? , ?, ?)
            RETURNING id
        '''
            Long vagaId = sql.firstRow(queryVagas, [vaga.nome, vaga.descricao, vaga.local, vaga.empresaId]).id

            return vagaId

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }
    }

    @Override
    List<Vaga> listar() throws DatabaseException {
        List<Vaga> vagas = []

        try {

            String query = '''
                SELECT *
                FROM vagas
                ORDER BY id
            '''

            sql.eachRow(query) { row ->

                Long id = row["id"]
                String nome = row['nome']
                String descricao = row['descricao']
                String local = row['local']
                Long empresaId = row['empresa_id']

                Vaga vaga = new Vaga(
                        id,
                        nome,
                        descricao,
                        local,
                        empresaId
                )

                vagas << vaga
            }
        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

        return vagas
    }

    @Override
    void editar(Vaga vaga) throws DatabaseException {

        String queryUpdateVaga = '''
        UPDATE vagas
        SET nome = ?, descricao = ?, local = ?
        WHERE id = ?
        '''

        try {
            sql.execute(queryUpdateVaga, [
                    vaga.nome,
                    vaga.descricao,
                    vaga.local,
                    vaga.id,
            ])
        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

    }

    @Override
    void remover(Long vagaId) {

        String queryDeleteVaga = '''
        DELETE FROM vagas 
        WHERE id = ? 
        '''

        try {
            int rowsAffected = sql.executeUpdate(queryDeleteVaga, [vagaId])

            if(rowsAffected == 0){
                throw EntidadeNaoEncontradaException()
            }

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

    }

}
