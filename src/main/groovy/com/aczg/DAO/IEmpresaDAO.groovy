package com.aczg.DAO

import com.aczg.model.Empresa

interface IEmpresaDAO {

    List<Empresa> listarEmpresas()
    Long adicionarEmpresa(Empresa empresa)
    void atualizarEmpresa(Empresa empresa)
    void removerEmpresa(Long empresaId)

}