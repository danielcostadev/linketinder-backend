package com.aczg.service.interfaces


import com.aczg.interfaces.ICadastroEListagem
import com.aczg.interfaces.IEdicaoEExclusao
import com.aczg.interfaces.IEntidadeVerificarExistencia
import com.aczg.model.Empresa

interface IEmpresaService<T> extends ICadastroEListagem<T>, IEdicaoEExclusao<T>, IEntidadeVerificarExistencia{
    Empresa buscarPorId(Long id)

}