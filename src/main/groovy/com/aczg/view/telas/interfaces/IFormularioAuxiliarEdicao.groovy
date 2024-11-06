package com.aczg.view.telas.interfaces

interface IFormularioAuxiliarEdicao<T> {

    boolean validarExistencia(Long entidadeId)
    T coletarDadosParaEdicao(Long entidadeaId)
    void atualizar(T entidadeAtualizada)

}