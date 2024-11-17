package com.aczg.DAO.interfaces

import com.aczg.model.Vaga

interface IVagaDAO {

    Long cadastrar(Vaga vaga)
    List<Vaga> listar()
    void editar(Vaga vaga)
    void remover(Long vagaId)
    Vaga buscarPorId(Long vagaId)

}