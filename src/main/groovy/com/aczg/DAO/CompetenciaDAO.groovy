package com.aczg.DAO

import com.aczg.DAO.factory.ConexaoFactory
import com.aczg.DAO.interfaces.ICompetenciaDAO
import com.aczg.DAO.interfaces.IConexaoDAO
import com.aczg.DAO.interfaces.VerificarExistenciaDeEntidadeTrait
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.exceptions.EntidadeNaoEncontradaException
import com.aczg.model.Competencia
import groovy.sql.Sql

import java.sql.SQLException

class CompetenciaDAO implements ICompetenciaDAO, VerificarExistenciaDeEntidadeTrait{

    private IConexaoDAO conexaoDAO = ConexaoFactory.getConexao("PostgreSQL")
    private Sql sql = conexaoDAO.getSql()

    @Override
    Long cadastrar(String nomeCompetencia, Long candidatoId = null, Long vagaId = null) throws EntidadeJaExisteException, DatabaseException {
        try {
            String queryCompetenciaExistente = '''
            SELECT id FROM competencias WHERE nome = ?
        '''
            Long competenciaId = sql.firstRow(queryCompetenciaExistente, [nomeCompetencia])?.id

            if (competenciaId == null) {
                String queryInserirCompetencia = '''
                INSERT INTO competencias (nome)
                VALUES (?)
                RETURNING id
            '''
                competenciaId = sql.firstRow(queryInserirCompetencia, [nomeCompetencia]).id
            } else {
                throw new EntidadeJaExisteException();
            }

            if (candidatoId) {
                String queryAssociacaoCandidato = '''
                INSERT INTO candidato_competencias (candidato_id, competencia_id)
                VALUES (?, ?)
            '''
                sql.execute(queryAssociacaoCandidato, [candidatoId, competenciaId])

            } else if (vagaId) {
                String queryAssociacaoVaga = '''
                INSERT INTO vaga_competencia (vaga_id, competencia_id)
                VALUES (?, ?)
            '''
                sql.execute(queryAssociacaoVaga, [vagaId, competenciaId])

            } else {
                throw new IllegalArgumentException("Nenhum candidatoId ou vagaId fornecido.")
            }

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

        return competenciaId
    }

    @Override
    List<Competencia> listar() throws DatabaseException {
        List<Competencia> competencias = []

        try {

            String query = '''
                SELECT id, nome
                FROM competencias
                ORDER BY id
            '''

            sql.eachRow(query) { row ->
                Long id = row["id"]
                String nome = row['nome']


                Competencia competencia = new Competencia(
                        id,
                        nome,
                )

                competencias << competencia
            }
        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

        return competencias
    }

    @Override
    void editar(Competencia competencia) throws DatabaseException {

        String queryUpdateCompetencia = '''
        UPDATE competencias
        SET nome = ?
        WHERE id = ?
        '''

        try {
            sql.execute(queryUpdateCompetencia, [
                    competencia.nome,
                    competencia.id
            ])
        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

    }

    @Override
    void remover(Long competenciaId) throws EntidadeNaoEncontradaException, DatabaseException {

        String queryDeleteCompetencia = '''
        DELETE FROM competencias 
        WHERE id = ? 
        '''

        try {
            int rowsAffected = sql.executeUpdate(queryDeleteCompetencia, [competenciaId])

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
