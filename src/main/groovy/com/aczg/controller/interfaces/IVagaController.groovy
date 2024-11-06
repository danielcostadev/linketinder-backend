package com.aczg.controller.interfaces

import com.aczg.interfaces.ICadastroEListagem
import com.aczg.interfaces.IEdicaoEExclusao
import com.aczg.interfaces.IEntidadeVerificarExistencia

interface IVagaController<T> extends ICadastroEListagem<T>, IEdicaoEExclusao<T>, IEntidadeVerificarExistencia {

}