package com.aczg.DAO

import com.aczg.DAO.factory.ConexaoFactory
import com.aczg.DAO.interfaces.IConexaoDAO
import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.DAO.interfaces.VerificarExistenciaDeEntidadeTrait
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.exceptions.EntidadeNaoEncontradaException
import com.aczg.model.Empresa
import groovy.sql.Sql

import java.sql.SQLException

class EmpresaDAO implements IEntidadeDAO<Empresa>, VerificarExistenciaDeEntidadeTrait{

    private IConexaoDAO conexaoDAO = ConexaoFactory.getConexao("PostgreSQL")
    private Sql sql = conexaoDAO.getSql()

    @Override
    Long cadastrar(Empresa empresa) throws EntidadeJaExisteException, DatabaseException {
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
                throw new EntidadeJaExisteException();
            }

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

        return empresaId
    }

    @Override
    List<Empresa> listar() throws DatabaseException {
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
        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

        return empresas
    }

    @Override
    void editar(Empresa empresa) throws DatabaseException {

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
        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }
    }

    @Override
    void remover(Long empresaId) throws EntidadeNaoEncontradaException, DatabaseException {

        String queryDeleteEmpresa = '''
        DELETE FROM empresas 
        WHERE id = ? 
        '''

        try {
            int rowsAffected = sql.executeUpdate(queryDeleteEmpresa, [empresaId])

            if(rowsAffected == 0){
                throw EntidadeNaoEncontradaException()
            }

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

    }

}
