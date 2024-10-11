package com.aczg.DAO

import com.aczg.model.Empresa
import groovy.sql.Sql

class EmpresaDAO {

    private ConexaoDAO conexaoDAO = new ConexaoDAO()

    Sql sql = conexaoDAO.getSql()

    List<Empresa> readEmpresas() {
        List<Empresa> empresas = []

        try {

            String query = '''
                SELECT nome, email, estado, cnpj, pais, cep, descricao, senha
                FROM empresas
            '''

            sql.eachRow(query) { row ->

                String nome = row['nome']
                String email = row['email']
                String estado = row['estado']
                String cnpj = row['cnpj']
                String pais = row['pais']
                String cep = row['cep']
                String descricao = row['descricao']
                String senha = null

                Empresa empresa = new Empresa(
                        nome,
                        email,
                        estado,
                        cnpj,
                        pais,
                        cep,
                        descricao,
                        senha
                )

                empresas << empresa
            }
        } catch (Exception e) {
            println "Erro ao buscar empresas: ${e.message}"
        }

        return empresas
    }

    Long insertEmpresa(Empresa empresa) {

        try {

            String queryEmpresa = '''
        INSERT INTO empresas (nome,email,estado,cnpj,pais,cep,descricao,senha)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        RETURNING id
        '''

            Long empresaId = sql.firstRow(queryEmpresa, [
                    empresa.nome,
                    empresa.email,
                    empresa.estado,
                    empresa.cnpj,
                    empresa.pais,
                    empresa.cep,
                    empresa.descricao,
                    empresa.senha
            ]).id

            return empresaId

        } catch (Exception e) {
            println "Erro ao cadastrar empresa: ${e.message}"
            return null
        }
    }

    void updateEmpresa(Empresa empresa) {

        String queryUpdateEmpresa = '''
        UPDATE empresas
        SET nome = ?, email = ?, estado = ?, cnpj = ?, pais = ?, cep = ?, descricao = ?, senha = ?
        WHERE id = ?
        '''

        try {
            sql.execute(queryUpdateEmpresa, [
                    empresa.nome,
                    empresa.email,
                    empresa.estado,
                    empresa.cnpj,
                    empresa.pais,
                    empresa.cep,
                    empresa.descricao,
                    empresa.senha,
                    empresa.id
            ])
        } catch (Exception e) {
            println "Erro ao atualizar empresa: ${e.message}"
        }
    }

    void deleteEmpresa(Empresa empresa) {



    }

    boolean empresaExiste(Long empresaId) {
        String query = 'SELECT COUNT(*) FROM empresas WHERE id = ?'
        def count = sql.firstRow(query, [empresaId])?.count ?: 0
        return count > 0
    }
}
