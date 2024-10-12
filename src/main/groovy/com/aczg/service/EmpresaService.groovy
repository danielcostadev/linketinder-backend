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


    Long cadastrarEmpresa(String nome, String email, String estado, String cnpj, String pais, String cep, String descricao, String senha){

        Sql sql = conexaoDAO.getSql()

        try {
            Empresa empresa = new Empresa(nome,email,estado,cnpj,pais,cep,descricao,senha)
            Long empresaId = empresaDAO.insertEmpresa(empresa)

            return empresaId

        } catch (Exception e) {
            println "Erro ao cadastrar empresa e vagas: ${e.message}"
            return null
        } finally {
            sql.close()
        }
    }

    Long cadastrarVaga(String nome, String descricao, String local, Long empresaId) {

        try {
            Vaga vaga = new Vaga(nome, descricao, local)
            Long vagaId = vagaDAO.insertVaga(vaga.nome, vaga.descricao, vaga.local, empresaId)
            println "Vaga '${nome}' cadastrada para a empresa ID: ${empresaId}"

            return vagaId

        } catch (Exception e) {
            println "Erro ao cadastrar vaga: ${e.message}"
            return null
        }
    }

    void cadastrarCompetencia(List<String> competencias, Long vagaId){

        try {

            competencias.each { nomeCompetencia ->
                Competencia novaCompetencia = new Competencia(nomeCompetencia)
                competenciaDAO.insertCompetencia(novaCompetencia.nome, null, vagaId)
            }

        } catch (Exception e) {
            println "Erro ao cadastrar competências: ${e.message}"
        }
    }


    List<Empresa> mostrarEmpresas(){
        return getEmpresaDAO().readEmpresas()
    }

    List<Vaga> mostrarVagas(){
        return getVagaDAO().readVagas()
    }


    void atualizarEmpresa(Empresa empresa){
        getEmpresaDAO().updateEmpresa(empresa)
    }

    void atualizarVaga(Vaga vaga){
        getVagaDAO().updateVaga(vaga)
    }

    void atualizarCompetencia(Competencia competencia){
        getCompetenciaDAO().updateCompetencia(competencia)
    }


    void deletarEmpresa(Long empresaId) {
        getEmpresaDAO().deleteEmpresa(empresaId)
    }

    void deletarVaga(Long vagaId){
        getVagaDAO().deleteVaga(vagaId)
    }

    void deletarCompetencia(Long competenciaId){
        getCompetenciaDAO().deleteCompetencia(competenciaId)
    }

}
