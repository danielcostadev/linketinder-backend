package com.aczg.interfaces

interface IEntidadeEdicaoEExclusao<T> {
    void editar(T entidade)
    void remover(Long entidadeId)
}