package com.aczg.service

import com.aczg.DAO.VagaDAO
import com.aczg.model.Vaga

class VagaService {

    VagaDAO vagaDAO

    VagaService(VagaDAO vagaDAO){
        this.vagaDAO = vagaDAO
    }

    void cadastrarVaga(String nome, String descricao, String local){

        Vaga vaga = new Vaga(nome,descricao,local)
        vagaDAO.createVaga(Vaga)

    }

}
