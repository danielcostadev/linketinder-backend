package com.aczg.DAO

import com.aczg.model.Competencia
import groovy.sql.Sql

class CompetenciaDAO {

    private ConexaoDAO conexaoDAO = new ConexaoDAO()

    Sql sql = conexaoDAO.getSql()

    List<Competencia> readCompetencias() {
        List<Competencia> competencias = []

        try {

            String query = '''
                SELECT nome
                FROM competencias
            '''

            sql.eachRow(query) { row ->

                String nome = row['nome']


                Competencia competencia = new Competencia(
                        nome,
                )

                competencias << competencia
            }
        } catch (Exception e) {
            println "Erro ao buscar competencia: ${e.message}"
        }

        return competencias
    }

    void insertCompetencia(String nomeCompetencia, Long candidatoId = null, Long vagaId = null) {

        try {
            String queryCompetencia = '''
            INSERT INTO competencias (nome)
            VALUES (?)
            RETURNING id
        '''
            Long competenciaId = sql.firstRow(queryCompetencia, [nomeCompetencia]).id

            if (candidatoId) {
                String queryAssociacaoCandidato = '''
                INSERT INTO candidato_competencias (candidato_id, competencia_id)
                VALUES (?, ?)
            '''
                sql.execute(queryAssociacaoCandidato, [candidatoId, competenciaId])
                println "Competência '${nomeCompetencia}' cadastrada e associada ao candidato ID: ${candidatoId}"

            } else if (vagaId) {
                String queryAssociacaoVaga = '''
                INSERT INTO vaga_competencia (vaga_id, competencia_id)
                VALUES (?, ?)
            '''
                sql.execute(queryAssociacaoVaga, [vagaId, competenciaId])
                println "Competência '${nomeCompetencia}' cadastrada e associada à vaga ID: ${vagaId}"
            } else {
                throw new IllegalArgumentException("Nenhum candidatoId ou vagaId fornecido.")
            }

        } catch (Exception e) {
            println "Erro ao cadastrar dados: ${e.message}"
        }
    }

    void updateCompetencia(Competencia competencia) {

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
        } catch (Exception e) {
            println "Erro ao atualizar competência: ${e.message}"
        }

    }

    void deleteCompetencia(Long competenciaId) {

        String queryDeleteCompetencia = '''
        DELETE FROM competencias 
        WHERE id = ? 
        '''

        try {
            int rowsAffected = sql.executeUpdate(queryDeleteCompetencia, [competenciaId])

            if(rowsAffected == 0){
                println "Nenhuma competência encontrada com o ID ${competenciaId}."
            } else {
                println "Competência com ID ${competenciaId} removida com sucesso."
            }

        } catch (Exception e) {
            println "Erro ao tentar remover Competência: ${e.message}"
        }

    }

    boolean competenciaExiste(Long competenciaId) {
        String query = 'SELECT COUNT(*) FROM competencias WHERE id = ?'
        def count = sql.firstRow(query, [competenciaId])?.count ?: 0
        return count > 0
    }


}
