package com.aczg.DAO

import com.aczg.model.Vaga
import groovy.sql.Sql

class VagaDAO {

    private ConexaoDAO conexaoDAO = new ConexaoDAO()

    List<Vaga> readVagas() {
        List<Vaga> vagas = []

        Sql sql = conexaoDAO.getSql()

        try {

            String query = '''
                SELECT nome, descricao, local
                FROM vagas
            '''

            sql.eachRow(query) { row ->

                String nome = row['nome']
                String descricao = row['descricao']
                String local = row['local']

                Vaga vaga = new Vaga(
                        nome,
                        descricao,
                        local
                )

                vagas << vaga
            }
        } catch (Exception e) {
            println "Erro ao buscar vagas: ${e.message}"
        } finally {
            conexaoDAO.close()
        }

        return empresas
    }

    Long insertVaga(String nomeVaga, String nomeDescricao, String nomeLocal, Long empresaId) {

        Sql sql = conexaoDAO.getSql()

        try {

            String queryVagas = '''
            INSERT INTO vagas (nome, descricao, local, empresa_id)
            VALUES (?, ? , ?, ?)
            RETURNING id
        '''
            Long vagaId = sql.firstRow(queryVagas, [nomeVaga, nomeDescricao, nomeLocal, empresaId]).id

            return vagaId

           // println "Vaga '${nomeVaga}' cadastrada com sucesso"

        } catch (Exception e) {
            println "Erro ao cadastrar vaga: ${e.message}"

        }



    }

    void updateVaga(Vaga vaga) {



    }

    void deleteVaga(Vaga vaga) {



    }
}
