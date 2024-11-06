package com.aczg.interfaces

interface ICadastroEListagem<T> {
    Long cadastrar(T entidade)
    List<T> listar()
}