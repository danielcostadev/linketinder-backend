package com.aczg.service.interfaces


import com.aczg.interfaces.ICadastroEListagem
import com.aczg.interfaces.IEdicaoEExclusao
import com.aczg.interfaces.IEntidadeVerificarExistencia
import com.aczg.model.Candidato

interface ICandidatoService<T> extends ICadastroEListagem<T>, IEdicaoEExclusao<T>, IEntidadeVerificarExistencia{
    Candidato buscarPorId(Long id)
}