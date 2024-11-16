package com.aczg.DAO

import com.aczg.DAO.factory.ConexaoFactory
import com.aczg.DAO.interfaces.IConexaoDAO
import com.aczg.DAO.interfaces.IVagaDAO
import com.aczg.DAO.interfaces.VerificarExistenciaDeEntidadeTrait
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeNaoEncontradaException
import com.aczg.model.Vaga
import groovy.sql.Sql

import java.sql.SQLException

class VagaDAO implements IVagaDAO, VerificarExistenciaDeEntidadeTrait {

    private IConexaoDAO conexaoDAO = ConexaoFactory.getConexao("PostgreSQL")
    private Sql sql = conexaoDAO.getSql()

    @Override
    Long cadastrar(Vaga vaga) throws DatabaseException {
        Long vagaId = null

        try {
            vagaId = inserirVaga(vaga)

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

        return vagaId

    }

    @Override
    List<Vaga> listar() throws DatabaseException {
        try {
            String query = obterQueryVagas()
            return executarConsultaVagas(query)
        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }
    }

    @Override
    void editar(Vaga vaga) throws DatabaseException {

        try {
            atualizarVaga(vaga)
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

            if (rowsAffected == 0) {
                throw new EntidadeNaoEncontradaException()
            }

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

    }

    @Override
    Vaga buscarPorId(Long vagaId) {
        Vaga vaga = null

        try {
            String queryBuscaPorId = '''
            SELECT * FROM vagas WHERE id = ?
            '''
            sql.eachRow(queryBuscaPorId, [vagaId]) { row ->

                Long id = row["id"] as Long
                String nome = row['nome']
                String descricao = row['descricao']
                String local = row['local']
                Long empresaId = row['empresa_id'] as Long

                vaga = new Vaga(
                        id,
                        nome,
                        descricao,
                        local,
                        empresaId
                )

            }

            return vaga

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }
    }

    private Long inserirVaga(Vaga vaga) {
        String queryCandidato = '''
        INSERT INTO vagas (nome, descricao, local, empresa_id)
        VALUES (?, ?, ?, ?)
        RETURNING id
    '''
        return sql.firstRow(queryCandidato, [
                vaga.nome,
                vaga.descricao,
                vaga.local,
                vaga.empresaId
        ]).id
    }

    private void atualizarVaga(Vaga vaga) {
        String query = '''
            UPDATE vagas
            SET nome = ?, descricao = ?, local = ?
            WHERE id = ?
        '''
        sql.execute(query, [
                vaga.nome,
                vaga.descricao,
                vaga.local,
                vaga.id
        ])
    }

    private String obterQueryVagas() {
        return '''
            SELECT id, nome, descricao, local, empresa_id
            FROM vagas 
            ORDER BY id
        '''
    }

    private List<Vaga> executarConsultaVagas(String query) {

        List<Vaga> vagas = []
        sql.eachRow(query) { row ->

            Long id = row["id"] as Long
            String nome = row['nome']
            String descricao = row['descricao']
            String local = row['local']
            Long empresaId = row['empresa_id'] as Long

            Vaga vaga = new Vaga(
                    id,
                    nome,
                    descricao,
                    local,
                    empresaId
            )

            vagas << vaga
        }
        return vagas
    }

}
