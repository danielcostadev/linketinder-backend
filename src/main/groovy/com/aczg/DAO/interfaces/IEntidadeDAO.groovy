package com.aczg.DAO.interfaces

interface IEntidadeDAO<T> {

    Long cadastrar(T entidade)
    List<T> listar()
    void editar(T entidade)
    void remover(Long entidadeId)

}