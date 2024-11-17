package com.aczg.DAO

import com.aczg.DAO.factory.ConexaoFactory
import com.aczg.DAO.interfaces.ICompetenciaDAO
import com.aczg.DAO.interfaces.IConexaoDAO
import com.aczg.DAO.interfaces.VerificarExistenciaDeEntidadeTrait
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeNaoEncontradaException
import com.aczg.model.Competencia
import groovy.sql.Sql

import java.sql.SQLException

class CompetenciaDAO implements ICompetenciaDAO, VerificarExistenciaDeEntidadeTrait{

    private IConexaoDAO conexaoDAO = ConexaoFactory.getConexao("PostgreSQL")
    private Sql sql = conexaoDAO.getSql()

    @Override
    Long cadastrar(String nomeCompetencia, Long candidatoId = null, Long vagaId = null) throws DatabaseException {
        try {
            Long competenciaId = buscarCompetenciaPorNome(nomeCompetencia)

            if (competenciaId == null) {
                competenciaId = inserirCompetencia(nomeCompetencia)
            }

            if (candidatoId) {
                associarCompetenciaComCandidato(candidatoId, competenciaId)
            }
            if (vagaId) {
                associarCompetenciaComVaga(vagaId, competenciaId)
            }

            return competenciaId

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }
    }


    @Override
    List<Competencia> listar() throws DatabaseException {
        try {
            String query = obterQueryCompetencias()
            return executarConsultaCompetencia(query)
        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }
    }

    @Override
    void editar(Competencia competencia) throws DatabaseException {

        try {
            atualizarCompetencia(competencia)
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
                throw new EntidadeNaoEncontradaException()
            }

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

    }

    @Override
    Competencia buscarPorId(Long competenciaId) {
        Competencia competencia = null

        try {
            String queryBuscaPorId = '''
            SELECT * FROM competencias WHERE id = ?
            '''
            sql.eachRow(queryBuscaPorId, [competenciaId]) { row ->

                Long id = row["id"] as Long
                String nome = row['nome']

                competencia = new Competencia(
                        id,
                        nome
                )
            }

            return competencia

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }
    }


    private Long buscarCompetenciaPorNome(String nomeCompetencia) throws SQLException {
        String queryCompetenciaExistente = '''
    SELECT id FROM competencias WHERE nome = ?
    '''
        return sql.firstRow(queryCompetenciaExistente, [nomeCompetencia])?.id
    }

    private Long inserirCompetencia(String nomeCompetencia) throws SQLException {
        String queryInserirCompetencia = '''
    INSERT INTO competencias (nome)
    VALUES (?)
    RETURNING id
    '''
        return sql.firstRow(queryInserirCompetencia, [nomeCompetencia]).id
    }


    private void associarCompetenciaComCandidato(Long candidatoId, Long competenciaId) throws SQLException {
        String queryAssociacaoCandidato = '''
        INSERT INTO candidato_competencias (candidato_id, competencia_id)
        VALUES (?, ?)
    '''
        sql.execute(queryAssociacaoCandidato, [candidatoId, competenciaId]);
    }

    private void associarCompetenciaComVaga(Long vagaId, Long competenciaId) throws SQLException {
        String queryAssociacaoVaga = '''
        INSERT INTO vaga_competencia (vaga_id, competencia_id)
        VALUES (?, ?)
    '''
        sql.execute(queryAssociacaoVaga, [vagaId, competenciaId]);
    }

    private void atualizarCompetencia(Competencia competencia) {
        String query = '''
            UPDATE competencias
            SET nome = ?
            WHERE id = ?
        '''
        sql.execute(query, [
                competencia.nome,
                competencia.id
        ])
    }

    private String obterQueryCompetencias() {
        return '''
            SELECT id, nome
            FROM competencias
            ORDER BY id
        '''
    }

    private List<Competencia> executarConsultaCompetencia(String query) {

        List<Competencia> competencias = []
        sql.eachRow(query) { row ->

            Long id = row["id"] as Long
            String nome = row['nome']

            Competencia competencia = new Competencia(
                    id,
                    nome
            )

            competencias << competencia
        }
        return competencias
    }

}
