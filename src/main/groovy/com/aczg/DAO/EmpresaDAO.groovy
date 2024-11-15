package com.aczg.DAO

import com.aczg.DAO.factory.ConexaoFactory
import com.aczg.DAO.interfaces.IConexaoDAO
import com.aczg.DAO.interfaces.IEmpresaDAO
import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.DAO.interfaces.VerificarExistenciaDeEntidadeTrait
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.exceptions.EntidadeNaoEncontradaException
import com.aczg.model.Candidato
import com.aczg.model.Empresa
import groovy.sql.Sql

import java.sql.SQLException
import java.time.LocalDate

class EmpresaDAO implements IEmpresaDAO, VerificarExistenciaDeEntidadeTrait{

    private IConexaoDAO conexaoDAO = ConexaoFactory.getConexao("PostgreSQL")
    private Sql sql = conexaoDAO.getSql()

    @Override
    Long cadastrar(Empresa empresa) throws EntidadeJaExisteException, DatabaseException {
        Long empresaId = null

        try {
            if (cnpjJaCadastrado(empresa) || emailJaCadastrado(empresa)) {
                throw new EntidadeJaExisteException();
            } else {
                empresaId = inserirEmpresa(empresa)
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
        try {
            String query = obterQueryEmpresas()
            return executarConsultaEmpresas(query)
        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }
    }

    @Override
    void editar(Empresa empresa) throws DatabaseException {
        try {
            if (emailJaCadastrado(empresa)) {
                throw new EntidadeJaExisteException()
            } else {
                atualizarEmpresa(empresa)
            }
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
                throw new EntidadeNaoEncontradaException()
            }

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

    }

    @Override
    Empresa buscarPorId(Long empresaId) throws EntidadeNaoEncontradaException, DatabaseException{
        Empresa empresa = null

        try {
            String queryBuscaPorId = '''
            SELECT * FROM empresas WHERE id = ?
            '''
            sql.eachRow(queryBuscaPorId, [empresaId]) { row ->

                Long id = row["id"] as Long
                String nome = row['nome']
                String email = row['email']
                String estado = row['estado']
                String cnpj = row['cnpj']
                String pais = row['pais']
                String cep = row['cep']
                String descricao = row['descricao']
                String senha = null

                empresa = new Empresa(
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

            }

            return empresa

        } catch (SQLException e) {
            throw new DatabaseException(e)
        } catch (Exception e) {
            throw e
        }

    }



    private boolean cnpjJaCadastrado(Empresa empresa) {
        String queryVerificaCnpj = '''
        SELECT id FROM empresas WHERE cnpj = ?
    '''
        return sql.firstRow(queryVerificaCnpj, [empresa.cnpj])?.id != null
    }

    private boolean emailJaCadastrado(Empresa empresa) {
        String queryVerificaEmail = '''
        SELECT id FROM empresas WHERE email = ? AND id != ?
    '''
        return sql.firstRow(queryVerificaEmail, [empresa.email, empresa.id])?.id != null
    }

    private Long inserirEmpresa(Empresa empresa) {
        String queryEmpresa = '''
        INSERT INTO candidatos (nome, email, estado, cnpj, pais, cep, descricao, senha)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        RETURNING id
    '''
        return sql.firstRow(queryEmpresa, [
                empresa.nome,
                empresa.email,
                empresa.estado,
                empresa.cnpj,
                empresa.pais,
                empresa.cep,
                empresa.descricao,
                empresa.senha
        ]).id
    }

    private void atualizarEmpresa(Empresa empresa) {
        String query = '''
            UPDATE empresas
            SET nome = ?, email = ?, estado = ?, cnpj = ?, pais = ?, 
                cep = ?, descricao = ?, senha = ?
            WHERE id = ?
        '''
        sql.execute(query, [
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
    }

    private String obterQueryEmpresas() {
        return '''
            SELECT id, nome, email, estado, cnpj, pais, cep, descricao, senha
            FROM empresas 
            ORDER BY id
        '''
    }

    private List<Empresa> executarConsultaEmpresas(String query) {

        List<Empresa> empresas = []
        sql.eachRow(query) { row ->

            Long id = row["id"]
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
        return empresas
    }





}
