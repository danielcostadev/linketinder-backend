package com.aczg.service

import com.aczg.DAO.CandidatoDAO
import com.aczg.model.Candidato

import java.sql.Date

class CandidatoService {

    CandidatoDAO candidatoDAO

    CandidatoService(CandidatoDAO candidatoDAO){
        this.candidatoDAO = candidatoDAO
    }

    void cadastrarCandidato(String nome, String sobrenome, String email, String telefone, String linkedin, String cpf, Date dataNascimento, String estado, String cep, String descricao, String formacao, String senha){

        Candidato candidato = new Candidato(nome,sobrenome,email,telefone,linkedin,cpf,dataNascimento,estado,cep,descricao,formacao,senha)
        candidatoDAO.createCandidato(candidato)
    }
}
