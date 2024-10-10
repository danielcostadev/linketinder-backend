package com.aczg.DAO

import com.aczg.model.Candidato
import groovy.sql.Sql

class CandidatoDAO {

    private ConexaoDAO conexaoDAO = new ConexaoDAO()

    List<Candidato> readCandidados() {
        List<Candidato> candidatos = []

        Sql sql = conexaoDAO.getSql()

        try {

            String query = '''
                SELECT nome, sobrenome, email, telefone, linkedin, cpf, data_nascimento, estado, cep, descricao, formacao
                FROM candidatos
            '''

            sql.eachRow(query) { row ->

                String nome = row['nome']
                String sobrenome = row['sobrenome']
                String email = row['email']
                String telefone = row['telefone']
                String linkedin = row['linkedin']
                String cpf = row['cpf']
                String dataNascimento = row['data_nascimento'] as Date
                String estado = row['estado']
                String cep = row['cep']
                String descricao = row['descricao']
                String formacao = row['formacao']
                String senha = null

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
        } finally {
            conexaoDAO.close()
        }

        return candidatos
    }

    Long inserirCandidato(Candidato candidato) {

        Sql sql = conexaoDAO.getSql()

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



    }

    void deleteCandidato(Candidato candidato) {



    }

    boolean existeCandidato(String nome) {

        // fazer um select no cpf para verificar se já existe

        return true
    }


}
