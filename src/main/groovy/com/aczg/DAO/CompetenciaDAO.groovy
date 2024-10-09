package com.aczg.DAO

import com.aczg.model.Competencia
import groovy.sql.Sql

class CompetenciaDAO {

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
        } finally {
            conexaoDAO.close()
        }

        return candidatos
    }

    void createCompetencia(Competencia competencia) {



    }

    void updateCompetencia(Competencia competencia) {



    }

    void deleteCompetencia(Competencia competencia) {



    }

    boolean existeCompetencia(String nome) {


        return true
    }


}
