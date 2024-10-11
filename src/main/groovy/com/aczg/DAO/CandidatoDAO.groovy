package com.aczg.DAO

import com.aczg.model.Candidato
import groovy.sql.Sql

class CandidatoDAO {

    private ConexaoDAO conexaoDAO = new ConexaoDAO()

    Sql sql = conexaoDAO.getSql()

    List<Candidato> readCandidados() {
        List<Candidato> candidatos = []

        try {

            String query = '''
                SELECT nome, sobrenome, email, telefone, linkedin, cpf, data_nascimento, estado, cep, descricao, formacao, senha
                FROM candidatos
            '''

            sql.eachRow(query) { row ->

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
                String senha = row['senha']

                Candidato candidato = new Candidato(
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
        } catch (Exception e) {
            println "Erro ao buscar candidatos: ${e.message}"
        }

        return candidatos
    }

    Long insertCandidato(Candidato candidato) {

        try {

            String queryCandidato = '''
        INSERT INTO candidatos (nome, sobrenome, data_nascimento, email, telefone, linkedin, cpf, estado, cep, descricao, formacao, senha)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        RETURNING id
        '''

            Long candidatoId = sql.firstRow(queryCandidato, [
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

            return candidatoId

        } catch (Exception e) {
        println "Erro ao cadastrar candidato: ${e.message}"
        }

    }

    void updateCandidato(Candidato candidato) {

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
        } catch (Exception e) {
            println "Erro ao atualizar candidato: ${e.message}"
        }
    }

    void deleteCandidato(Candidato candidato) {



    }

    boolean candidatoExiste(Long candidatoId) {
        String query = 'SELECT COUNT(*) FROM candidatos WHERE id = ?'
        def count = sql.firstRow(query, [candidatoId])?.count ?: 0
        return count > 0
    }


}
