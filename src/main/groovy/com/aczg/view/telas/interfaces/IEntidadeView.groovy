package com.aczg.view.telas.interfaces

interface IEntidadeView<T> {

    void exibirFormularioDeCadastro()
    void exibirListaDeEmpresas()
    void exibirFormulariodeEdicao()
    void exibirFormulariodeExclusao()
    boolean validarExistencia(Long entidadeId)
    T coletarDados(Long entidadeaId)
    void atualizar(T entidadeAtualizada)

}