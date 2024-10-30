package com.aczg.DAO

import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.DAO.interfaces.VerificarExistenciaDeEntidadeTrait
import com.aczg.model.Empresa
import groovy.sql.Sql

class EmpresaDAO implements IEntidadeDAO<Empresa>, VerificarExistenciaDeEntidadeTrait{

    private ConexaoDAO conexaoDAO = new ConexaoDAO()

    Sql sql = conexaoDAO.getSql()

    @Override
    List<Empresa> listar() {
        List<Empresa> empresas = []

        try {

            String query = '''
                SELECT id, nome, email, estado, cnpj, pais, cep, descricao, senha
                FROM empresas 
                ORDER BY id
            '''

            sql.eachRow(query) { row ->
                Long id = row['id']
                String nome = row['nome']
                String email = row['email']
                String estado = row['estado']
                String cnpj = row['cnpj']
                String pais = row['pais']
                String cep = row['cep']
                String descricao = row['descricao']
                String senha = null

                Empresa empresa = new Empresa(
                        id,
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

    @Override
    Long cadastrar(Empresa empresa) {
        Long empresaId = null

        try {
            String queryVerificaEmpresa = '''
            SELECT id FROM empresas WHERE email = ? OR cnpj = ?
        '''
            empresaId = sql.firstRow(queryVerificaEmpresa, [empresa.email, empresa.cnpj])?.id

            if (empresaId == null) {
                String queryEmpresa = '''
                INSERT INTO empresas (nome,email,estado,cnpj,pais,cep,descricao,senha)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                RETURNING id
            '''

                empresaId = sql.firstRow(queryEmpresa, [
                        empresa.nome,
                        empresa.email,
                        empresa.estado,
                        empresa.cnpj,
                        empresa.pais,
                        empresa.cep,
                        empresa.descricao,
                        empresa.senha
                ]).id
            } else {
                println "Empresa já existe com o e-mail ou CNPJ fornecido. ID: ${empresaId}"
            }

        } catch (Exception e) {
            println "Erro ao cadastrar empresa: ${e.message}"
        }

        return empresaId
    }

    @Override
    void editar(Empresa empresa) {

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

    @Override
    void remover(Long empresaId) {

        String queryDeleteEmpresa = '''
        DELETE FROM empresas 
        WHERE id = ? 
        '''

        try {
            int rowsAffected = sql.executeUpdate(queryDeleteEmpresa, [empresaId])

            if(rowsAffected == 0){
                println "Nenhuma empresa encontrada com o ID ${empresaId}."
            } else {
                println "Empresa com ID ${empresaId} removida com sucesso."
            }

        } catch (Exception e) {
            println "Erro ao tentar remover empresa: ${e.message}"
        }

    }

}
