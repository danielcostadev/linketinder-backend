package com.aczg.DAO.interfaces

interface IEntidadeDAO<T> {

    List<T> listar()
    Long cadastrar(T entidade)
    void editar(T entidade)
    void remover(Long entidadeId)

}