package com.aczg.DAO

import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.DAO.interfaces.VerificarExistenciaDeEntidadeTrait
import com.aczg.model.Vaga
import groovy.sql.Sql

class VagaDAO implements IEntidadeDAO<Vaga>, VerificarExistenciaDeEntidadeTrait{

    private ConexaoDAO conexaoDAO = new ConexaoDAO()

    Sql sql = conexaoDAO.getSql()

    @Override
    List<Vaga> listar() {
        List<Vaga> vagas = []

        try {

            String query = '''
                SELECT *
                FROM vagas
                ORDER BY id
            '''

            sql.eachRow(query) { row ->

                Long id = row["id"]
                String nome = row['nome']
                String descricao = row['descricao']
                String local = row['local']
                Long empresaId = row['empresa_id']

                Vaga vaga = new Vaga(
                        id,
                        nome,
                        descricao,
                        local,
                        empresaId
                )

                vagas << vaga
            }
        } catch (Exception e) {
            println "Erro ao buscar vagas: ${e.message}"
        }

        return vagas
    }

    @Override
    Long cadastrar(Vaga vaga) {

        try {

            String queryVagas = '''
            INSERT INTO vagas (nome, descricao, local, empresa_id)
            VALUES (?, ? , ?, ?)
            RETURNING id
        '''
            Long vagaId = sql.firstRow(queryVagas, [vaga.nome, vaga.descricao, vaga.local, vaga.empresaId]).id

            return vagaId

           // println "Vaga '${nomeVaga}' cadastrada com sucesso"

        } catch (Exception e) {
            println "Erro ao cadastrar vaga: ${e.message}"
            return null
        }
    }

    @Override
    void editar(Vaga vaga) {

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

    @Override
    void remover(Long vagaId) {

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

}
