package com.aczg.service

import com.aczg.DAO.CandidatoDAO
import com.aczg.DAO.CompetenciaDAO
import com.aczg.DAO.ConexaoDAO
import com.aczg.model.Candidato
import com.aczg.model.Competencia
import groovy.sql.Sql

import java.sql.Date

class CandidatoService {

    CandidatoDAO candidatoDAO
    CompetenciaDAO competenciaDAO
    ConexaoDAO conexaoDAO = new ConexaoDAO()

    CandidatoService(CandidatoDAO candidatoDAO, CompetenciaDAO competenciaDAO){
        this.candidatoDAO = candidatoDAO
        this.competenciaDAO = competenciaDAO
    }


    Long adicionarCandidato(String nome, String sobrenome, String email, String telefone, String linkedin, String cpf, Date dataNascimento, String estado, String cep, String descricao, String formacao, String senha){

        Sql sql = conexaoDAO.getSql()

        try {
            Candidato candidato = new Candidato(nome,sobrenome,email,telefone,linkedin,cpf,dataNascimento,estado,cep,descricao,formacao,senha)
            Long candidatoId = candidatoDAO.adicionarCandidato(candidato)

            return candidatoId

        } catch (Exception e){
            println "Erro ao cadastrar candidato: ${e.message}"
        } finally {
            sql.close()
        }
    }

    List<Candidato> listarCandidados(){
        return getCandidatoDAO().listarCandidatos()
    }

    void atualizarCandidato(Candidato candidato){
        getCandidatoDAO().atualizarCandidato(candidato)
    }

    void removerCandidato(Long candidatoId) {
        getCandidatoDAO().removerCandidato(candidatoId)
    }


    void adicionarCompetencia(List<String> competencias, Long candidatoId){

        try {

            competencias.each { nomeCompetencia ->
                Competencia novaCompetencia = new Competencia(nomeCompetencia)
                competenciaDAO.adicionarCompetencia(novaCompetencia.nome, candidatoId, null)
            }

        } catch (Exception e) {
            println "Erro ao cadastrar competências: ${e.message}"
        }
    }

    List<Competencia> listarCompetencias(){
        return getCompetenciaDAO().listarCompetencias()
    }




}
