package com.aczg.DAO

import com.aczg.DAO.factory.ConexaoFactory
import com.aczg.DAO.interfaces.ICandidatoDAO
import com.aczg.DAO.interfaces.IConexaoDAO
import com.aczg.DAO.interfaces.VerificarExistenciaDeEntidadeTrait
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.exceptions.EntidadeNaoEncontradaException
import com.aczg.model.Candidato
import groovy.sql.Sql

import java.sql.SQLException
import java.time.LocalDate

class CandidatoDAO implements ICandidatoDAO, VerificarExistenciaDeEntidadeTrait {

    private IConexaoDAO conexaoDAO = ConexaoFactory.getConexao("PostgreSQL")
    private Sql sql = conexaoDAO.getSql()

    @Override
    Long cadastrar(Candidato candidato) throws EntidadeJaExisteException, DatabaseException {
        Long candidatoId = null

        try {
            if (cpfJaCadastrado(candidato) || emailJaCadastrado(candidato)) {
                throw new EntidadeJaExisteException();
            } else {
                candidatoId = inserirCandidato(candidato)
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
        try {
            String query = obterQueryCandidatos()
            return executarConsultaCandidatos(query)
        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }
    }

    @Override
    void editar(Candidato candidato) throws DatabaseException {
        try {
            if (emailJaCadastrado(candidato)) {
                throw new EntidadeJaExisteException()
            } else {
                atualizarCandidato(candidato)
            }
        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }
    }

    @Override
    void remover(Long candidatoId) throws EntidadeNaoEncontradaException, DatabaseException {

        String queryDeleteCandidato = '''
        DELETE FROM candidatos 
        WHERE id = ? 
        '''

        try {
            int rowsAffected = sql.executeUpdate(queryDeleteCandidato, [candidatoId])

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
    Candidato buscarPorId(Long entidadeId) throws EntidadeNaoEncontradaException, DatabaseException {
        Candidato candidato = null

        try {
            String queryBuscaPorId = '''
            SELECT * FROM candidatos WHERE id = ?
            '''
            sql.eachRow(queryBuscaPorId, [entidadeId]) { row ->

                Long id = row["id"] as Long
                String nome = row['nome']
                String sobrenome = row['sobrenome']
                String email = row['email']
                String telefone = row['telefone']
                String linkedin = row['linkedin']
                String cpf = row['cpf']

                LocalDate dataNascimento = row['data_nascimento']?.toLocalDate()

                String estado = row['estado']
                String cep = row['cep']
                String descricao = row['descricao']
                String formacao = row['formacao']
                String senha = null

                candidato = new Candidato(
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

            }

            return candidato

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

    }


    private boolean cpfJaCadastrado(Candidato candidato) {
        String queryVerificaCpf = '''
        SELECT id FROM candidatos WHERE cpf = ?
    '''
        return sql.firstRow(queryVerificaCpf, [candidato.cpf])?.id != null
    }

    private boolean emailJaCadastrado(Candidato candidato) {
        String queryVerificaEmail = '''
        SELECT id FROM candidatos WHERE email = ? AND id != ?
    '''
        return sql.firstRow(queryVerificaEmail, [candidato.email, candidato.id])?.id != null
    }

    private Long inserirCandidato(Candidato candidato) {
        String queryCandidato = '''
        INSERT INTO candidatos (nome, sobrenome, email, telefone, linkedin, cpf, data_nascimento, estado, cep, descricao, formacao, senha)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        RETURNING id
    '''
        return sql.firstRow(queryCandidato, [
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
                candidato.senha
        ]).id
    }

    private void atualizarCandidato(Candidato candidato) {
        String query = '''
            UPDATE candidatos
            SET nome = ?, sobrenome = ?, email = ?, telefone = ?, linkedin = ?, 
                data_nascimento = ?, estado = ?, cep = ?, descricao = ?, formacao = ?, senha = ?
            WHERE id = ?
        '''
        sql.execute(query, [
                candidato.nome,
                candidato.sobrenome,
                candidato.email,
                candidato.telefone,
                candidato.linkedin,
                candidato.dataNascimento ? java.sql.Date.valueOf(candidato.dataNascimento) : null,
                candidato.estado,
                candidato.cep,
                candidato.descricao,
                candidato.formacao,
                candidato.senha,
                candidato.id
        ])
    }

    private String obterQueryCandidatos() {
        return '''
            SELECT id, nome, sobrenome, email, telefone, linkedin, cpf, data_nascimento, estado, cep, descricao, formacao, senha
            FROM candidatos 
            ORDER BY id
        '''
    }

    private List<Candidato> executarConsultaCandidatos(String query) {

        List<Candidato> candidatos = []
        sql.eachRow(query) { row ->

            Long id = row["id"]
            String nome = row['nome']
            String sobrenome = row['sobrenome']
            String email = row['email']
            String telefone = row['telefone']
            String linkedin = row['linkedin']
            String cpf = row['cpf']
            LocalDate dataNascimento = row.getDate("data_nascimento").toLocalDate()
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
        return candidatos
    }

}
