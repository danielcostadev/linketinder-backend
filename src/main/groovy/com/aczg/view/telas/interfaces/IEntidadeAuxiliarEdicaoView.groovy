package com.aczg.view.telas.interfaces

interface IEntidadeAuxiliarEdicaoView<T> {

    boolean validarExistencia(Long entidadeId)
    T coletarDados(Long entidadeaId)
    void atualizar(T entidadeAtualizada)

}