package com.aczg.DAO

import com.aczg.DAO.interfaces.IConexaoDAO
import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.DAO.interfaces.VerificarExistenciaDeEntidadeTrait
import com.aczg.DAO.factory.ConexaoFactory
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.exceptions.DatabaseException
import com.aczg.model.Candidato
import groovy.sql.Sql

import java.sql.SQLException

class CandidatoDAO implements IEntidadeDAO<Candidato>, VerificarExistenciaDeEntidadeTrait{

    private IConexaoDAO conexaoDAO = ConexaoFactory.getConexao("PostgreSQL")
    private Sql sql = conexaoDAO.getSql()

    @Override
    Long cadastrar(Candidato candidato) throws EntidadeJaExisteException, DatabaseException {
        Long candidatoId = null

        try {
            String queryVerificaCandidato = '''
            SELECT id FROM candidatos WHERE email = ? OR cpf = ?
        '''
            candidatoId = sql.firstRow(queryVerificaCandidato, [candidato.email, candidato.cpf])?.id

            if (candidatoId == null) {
                String queryCandidato = '''
                INSERT INTO candidatos (nome, sobrenome, data_nascimento, email, telefone, linkedin, cpf, estado, cep, descricao, formacao, senha)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                RETURNING id
            '''

                candidatoId = sql.firstRow(queryCandidato, [
                        candidato.nome,
                        candidato.sobrenome,
                        candidato.dataNascimento,
                        candidato.email,
                        candidato.telefone,
                        candidato.linkedin,
                        candidato.cpf,
                        candidato.estado,
                        candidato.cep,
                        candidato.descricao,
                        candidato.formacao,
                        candidato.senha
                ]).id
            } else {
                throw new EntidadeJaExisteException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

        return candidatoId
    }

    @Override
    List<Candidato> listar() throws DatabaseException {
        List<Candidato> candidatos = []

        try {

            String query = '''
                SELECT id, nome, sobrenome, email, telefone, linkedin, cpf, data_nascimento, estado, cep, descricao, formacao, senha
                FROM candidatos 
                ORDER BY id
            '''

            sql.eachRow(query) { row ->

                Long id = row["id"]
                String nome = row['nome']
                String sobrenome = row['sobrenome']
                String email = row['email']
                String telefone = row['telefone']
                String linkedin = row['linkedin']
                String cpf = row['cpf']
                Date dataNascimento = row['data_nascimento']
                String estado = row['estado']
                String cep = row['cep']
                String descricao = row['descricao']
                String formacao = row['formacao']
                String senha = null

                Candidato candidato = new Candidato(
                        id,
                        nome,
                        sobrenome,
                        email,
                        telefone,
                        linkedin,
                        cpf,
                        dataNascimento,
                        estado,
                        cep,
                        descricao,
                        formacao,
                        senha
                )

                candidatos << candidato
            }

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

        return candidatos
    }

    @Override
    void editar(Candidato candidato) throws DatabaseException {

        String queryUpdateCandidato = '''
        UPDATE candidatos
        SET nome = ?, sobrenome = ?, email = ?, telefone = ?, linkedin = ?, cpf = ?, data_nascimento = ?, estado = ?,
        cep = ?, descricao = ?, formacao = ?, senha = ?
        WHERE id = ?
        '''

        try {
            sql.execute(queryUpdateCandidato, [
                    candidato.nome,
                    candidato.sobrenome,
                    candidato.email,
                    candidato.telefone,
                    candidato.linkedin,
                    candidato.cpf,
                    candidato.dataNascimento,
                    candidato.estado,
                    candidato.cep,
                    candidato.descricao,
                    candidato.formacao,
                    candidato.senha,
                    candidato.id
            ])
        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }
    }

    @Override
    void remover(Long candidatoId) throws DatabaseException{

        String queryDeleteCandidato = '''
        DELETE FROM candidatos 
        WHERE id = ? 
        '''

        try {
            int rowsAffected = sql.executeUpdate(queryDeleteCandidato, [candidatoId])

            if(rowsAffected == 0){
                println "Nenhum candidato encontrada com o ID ${candidatoId}."
            } else {
                println "Candidato com ID ${candidatoId} removido com sucesso."
            }

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

    }

}
