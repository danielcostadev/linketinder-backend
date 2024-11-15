package com.aczg.service

import com.aczg.DAO.interfaces.IEmpresaDAO
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.model.Empresa
import com.aczg.service.interfaces.IEmpresaService
import com.aczg.service.interfaces.ManipulaEntidadeTrait
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EmpresaService implements IEmpresaService<Empresa>, ManipulaEntidadeTrait{

    private static final Logger log = LoggerFactory.getLogger(CandidatoService)

    IEmpresaDAO empresaDAO

    EmpresaService(IEmpresaDAO empresaDAO){
        this.empresaDAO = empresaDAO
    }

    @Override
    Long cadastrar(Empresa empresa) throws EntidadeJaExisteException, DatabaseException {

        try {
            Long empresaId = empresaDAO.cadastrar(empresa)
            log.info("Empresa cadastrada com sucesso")
            return empresaId
        } catch (EntidadeJaExisteException | DatabaseException e) {
            log.error("Erro: ${e.getMessage()}")
            throw e
        }
    }

    @Override
    List<Empresa> listar() throws DatabaseException {
        try {
            return getEmpresaDAO().listar()
        } catch (DatabaseException e) {
            log.error("Erro: ${e.getMessage()}")
            throw e
        }
    }

    @Override
    void editar(Empresa empresa) throws DatabaseException {

        try {
            empresaDAO.editar(empresa)
        } catch (DatabaseException e) {
            throw e
        } catch (Exception e) {
            log.error("Erro: ${e.getMessage()}")
            throw e
        }
    }

    @Override
    void remover(Long empresaId) throws DatabaseException {
        try {
            empresaDAO.remover(empresaId)
        } catch (DatabaseException e) {
            throw e
        } catch (Exception e) {
            log.error("Erro: ${e.getMessage()}")
            throw e
        }
    }

    @Override
    boolean verificarExistencia(Long empresaId) {
        try {
            return empresaDAO.verificarExistencia('empresas', empresaId)
        } catch (Exception e) {
            log.warn("Erro: ${e.getMessage()}")
        }
    }

    @Override
    Empresa buscarPorId(Long id) {
        try {
            return empresaDAO.buscarPorId(id)
        } catch (Exception e) {
            log.warn("Erro ao buscar empresa com ID: ${id} - ${e.message}")
            throw e
        }
    }
}
