package com.aczg.controller.interfaces

import com.aczg.interfaces.ICadastroEListagem
import com.aczg.interfaces.IEdicaoEExclusao
import com.aczg.interfaces.IEntidadeVerificarExistencia
import com.aczg.model.Empresa
import com.aczg.model.Vaga

interface IVagaController<T> extends ICadastroEListagem<T>, IEdicaoEExclusao<T>, IEntidadeVerificarExistencia {
    Vaga buscarPorId(Long id)
}