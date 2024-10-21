package com.aczg.service

import com.aczg.DAO.CompetenciaDAO
import com.aczg.DAO.ConexaoDAO
import com.aczg.DAO.EmpresaDAO
import com.aczg.DAO.VagaDAO
import com.aczg.model.Competencia
import com.aczg.model.Empresa
import com.aczg.model.Vaga
import groovy.sql.Sql

class EmpresaService {

    EmpresaDAO empresaDAO
    VagaDAO vagaDAO
    CompetenciaDAO competenciaDAO
    ConexaoDAO conexaoDAO = new ConexaoDAO()

    EmpresaService(EmpresaDAO empresaDAO, VagaDAO vagaDAO, CompetenciaDAO competenciaDAO){
        this.empresaDAO = empresaDAO
        this.vagaDAO = vagaDAO
        this.competenciaDAO = competenciaDAO
    }


    Long adicionarEmpresa(Empresa empresa){

        Sql sql = conexaoDAO.getSql()

        try {
            Long empresaId = empresaDAO.adicionarEmpresa(empresa)

            return empresaId

        } catch (Exception e) {
            println "Erro ao cadastrar empresa e vagas: ${e.message}"
            return null
        } finally {
            sql.close()
        }
    }

    List<Empresa> listarEmpresas(){
        return getEmpresaDAO().listarEmpresas()
    }

    void atualizarEmpresa(Empresa empresa){
        getEmpresaDAO().atualizarEmpresa(empresa)
    }

    void removerEmpresa(Long empresaId) {
        getEmpresaDAO().removerEmpresa(empresaId)
    }


    Long adicionarVaga(String nome, String descricao, String local, Long empresaId) {

        try {
            Vaga vaga = new Vaga(nome, descricao, local)
            Long vagaId = vagaDAO.adicionarVaga(vaga.nome, vaga.descricao, vaga.local, empresaId)
            println "Vaga '${nome}' cadastrada para a empresa ID: ${empresaId}"

            return vagaId

        } catch (Exception e) {
            println "Erro ao cadastrar vaga: ${e.message}"
            return null
        }
    }

    List<Vaga> listarVagas(){
        return getVagaDAO().listarVagas()
    }

    void atualizarVaga(Vaga vaga){
        getVagaDAO().atualizarVaga(vaga)
    }

    void removerVaga(Long vagaId){
        getVagaDAO().removerVaga(vagaId)
    }


    void adicionarCompetencia(List<String> competencias, Long vagaId){

        try {

            competencias.each { nomeCompetencia ->
                Competencia novaCompetencia = new Competencia(nomeCompetencia)
                competenciaDAO.adicionarCompetencia(novaCompetencia.nome, null, vagaId)
            }

        } catch (Exception e) {
            println "Erro ao cadastrar competências: ${e.message}"
        }
    }

    void atualizarCompetencia(Competencia competencia){
        getCompetenciaDAO().atualizarCompetencia(competencia)
    }

    void removerCompetencia(Long competenciaId){
        getCompetenciaDAO().removerCompetencia(competenciaId)
    }

}
