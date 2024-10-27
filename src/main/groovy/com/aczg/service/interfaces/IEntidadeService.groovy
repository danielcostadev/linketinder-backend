package com.aczg.service.interfaces

interface IEntidadeService<T> {

    Long cadastrar(T entidade)
    List<T> listar()
    void editar(T entidade)
    void remover(Long entidadeId)
    boolean verificarExistencia(Long entidadeId)
}
