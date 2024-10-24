package com.aczg.DAO

import com.aczg.model.Competencia
import groovy.sql.Sql

class CompetenciaDAO {

    private ConexaoDAO conexaoDAO = new ConexaoDAO()

    Sql sql = conexaoDAO.getSql()

    List<Competencia> listarCompetencias() {
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
        } catch (Exception e) {
            println "Erro ao buscar competencia: ${e.message}"
        }

        return competencias
    }

    void adicionarCompetencia(String nomeCompetencia, Long candidatoId = null, Long vagaId = null) {
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
                println "Competência '${nomeCompetencia}' cadastrada com sucesso."
            } else {
                println "Competência '${nomeCompetencia}' já existe com ID: ${competenciaId}."
            }

            if (candidatoId) {
                String queryAssociacaoCandidato = '''
                INSERT INTO candidato_competencias (candidato_id, competencia_id)
                VALUES (?, ?)
            '''
                sql.execute(queryAssociacaoCandidato, [candidatoId, competenciaId])
                println "Competência '${nomeCompetencia}' associada ao candidato ID: ${candidatoId}"

            } else if (vagaId) {
                String queryAssociacaoVaga = '''
                INSERT INTO vaga_competencia (vaga_id, competencia_id)
                VALUES (?, ?)
            '''
                sql.execute(queryAssociacaoVaga, [vagaId, competenciaId])
                println "Competência '${nomeCompetencia}' associada à vaga ID: ${vagaId}"

            } else {
                throw new IllegalArgumentException("Nenhum candidatoId ou vagaId fornecido.")
            }

        } catch (Exception e) {
            println "Erro ao cadastrar dados: ${e.message}"
        }
    }

    void atualizarCompetencia(Competencia competencia) {

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

    void removerCompetencia(Long competenciaId) {

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

}
