package com.aczg.DAO

import com.aczg.model.Empresa
import groovy.sql.Sql

class EmpresaDAO {

    private ConexaoDAO conexaoDAO = new ConexaoDAO()

    List<Empresa> readEmpresas() {
        List<Empresa> empresas = []

        Sql sql = conexaoDAO.getSql()

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
        } finally {
            conexaoDAO.close()
        }

        return empresas
    }

    void createEmpresa(Empresa empresa) {

        Sql sql = conexaoDAO.getSql()

        try {

            String query = '''
        INSERT INTO empresas (nome,email,estado,cnpj,pais,cep,descricao,senha)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        '''

            sql.execute(query, [
                    empresa.nome,
                    empresa.email,
                    empresa.estado,
                    empresa.cnpj,
                    empresa.pais,
                    empresa.cep,
                    empresa.descricao,
                    empresa.senha
            ])

        } catch (Exception e) {
            println "Erro ao cadastrar empresa: ${e.message}"
        } finally {
            conexaoDAO.close()
        }

    }

    void updateEmpresa(Empresa empresa) {



    }

    void deleteEmpresa(Empresa empresa) {



    }

    boolean existeEmpresa(String nome) {

        // fazer um select no cnpj para verificar se já existe

        return true
    }

}
