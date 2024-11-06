package com.aczg.service.interfaces

import com.aczg.interfaces.ICadastroEListagem
import com.aczg.interfaces.IEdicaoEExclusao
import com.aczg.interfaces.IEntidadeVerificarExistencia

interface IEmpresaService<T> extends ICadastroEListagem<T>, IEdicaoEExclusao<T>, IEntidadeVerificarExistencia {

}