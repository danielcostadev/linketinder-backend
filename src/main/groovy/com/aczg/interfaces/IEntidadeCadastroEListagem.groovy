package com.aczg.interfaces

interface IEntidadeCadastroEListagem<T> {
    Long cadastrar(T entidade)
    List<T> listar()
}