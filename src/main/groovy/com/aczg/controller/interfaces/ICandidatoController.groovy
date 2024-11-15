package com.aczg.controller.interfaces


import com.aczg.interfaces.ICadastroEListagem
import com.aczg.interfaces.IEdicaoEExclusao
import com.aczg.interfaces.IEntidadeVerificarExistencia
import com.aczg.model.Candidato
import com.aczg.model.Empresa

interface ICandidatoController<T> extends ICadastroEListagem<T>, IEdicaoEExclusao<T>, IEntidadeVerificarExistencia{
    Candidato buscarPorId(Long id)
}