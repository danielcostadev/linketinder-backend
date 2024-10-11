package com.aczg.DAO

import com.aczg.model.Vaga
import groovy.sql.Sql

class VagaDAO {

    private ConexaoDAO conexaoDAO = new ConexaoDAO()

    Sql sql = conexaoDAO.getSql()

    List<Vaga> readVagas() {
        List<Vaga> vagas = []

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
        }

        return vagas
    }

    Long insertVaga(String nomeVaga, String nomeDescricao, String nomeLocal, Long empresaId) {

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
            return null
        }
    }

    void updateVaga(Vaga vaga) {

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
        } catch (Exception e) {
            println "Erro ao atualizar vaga: ${e.message}"
        }

    }

    void deleteVaga(Vaga vaga) {



    }

    boolean vagaExiste(Long vagaId) {
        String query = 'SELECT COUNT(*) FROM vagas WHERE id = ?'
        def count = sql.firstRow(query, [vagaId])?.count ?: 0
        return count > 0
    }
}
