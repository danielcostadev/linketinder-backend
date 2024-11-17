package com.aczg.DAO.interfaces

import com.aczg.model.Candidato
import com.aczg.model.Empresa

interface IEmpresaDAO {

    Long cadastrar(Empresa empresa)
    List<Empresa> listar()
    void editar(Empresa empresa)
    void remover(Long empresaId)
    Empresa buscarPorId(Long empresaId)

}