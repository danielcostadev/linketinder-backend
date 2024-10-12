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
                SELECT id, nome, descricao, local
                FROM vagas
                ORDER BY id
            '''

            sql.eachRow(query) { row ->

                Long id = row["id"]
                String nome = row['nome']
                String descricao = row['descricao']
                String local = row['local']

                Vaga vaga = new Vaga(
                        id,
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

    void deleteVaga(Long vagaId) {

        String queryDeleteVaga = '''
        DELETE FROM vagas 
        WHERE id = ? 
        '''

        try {
            int rowsAffected = sql.executeUpdate(queryDeleteVaga, [vagaId])

            if(rowsAffected == 0){
                println "Nenhuma vaga encontrada com o ID ${vagaId}."
            } else {
                println "Vaga com ID ${vagaId} removida com sucesso."
            }

        } catch (Exception e) {
            println "Erro ao tentar remover vaga: ${e.message}"
        }

    }

    boolean vagaExiste(Long vagaId) {
        String query = 'SELECT COUNT(*) FROM vagas WHERE id = ?'
        def count = sql.firstRow(query, [vagaId])?.count ?: 0
        return count > 0
    }
}
