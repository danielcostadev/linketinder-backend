package com.aczg.DAO

import com.aczg.model.Competencia
import groovy.sql.Sql

class CompetenciaDAO {

    private ConexaoDAO conexaoDAO = new ConexaoDAO()

    List<Competencia> readCompetencias() {
        List<Competencia> competencias = []

        Sql sql = conexaoDAO.getSql()

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

        Sql sql = conexaoDAO.getSql()

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



    }

    void deleteCompetencia(Competencia competencia) {



    }

    boolean existeCompetencia(String nome) {


        return true
    }


}
