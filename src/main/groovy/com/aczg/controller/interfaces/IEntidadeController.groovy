package com.aczg.controller.interfaces

interface IEntidadeController<T> {

    void cadastrar(T entidade)
    List<T> listar()
    void editar(T entidade)
    void remover(Long entidadeId)
    boolean verificarExistencia(Long entidadeId)

}
