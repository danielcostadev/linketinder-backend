package com.aczg.DAO

import com.aczg.model.Vaga

interface IVagaDAO {

    List<Vaga> listarVagas()
    Long adicionarVaga(String nomeVaga, String nomeDescricao, String nomeLocal, Long empresaId)
    void atualizarVaga(Vaga vaga)
    void removerVaga(Long vagaId)

}