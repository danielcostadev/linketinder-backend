package com.aczg.controller

import com.aczg.model.Candidato
import com.aczg.interfaces.IEntidade

class CandidatoController implements IEntidade<Candidato> {

    IEntidade candidatoService

    CandidatoController(IEntidade candidatoService) {
        this.candidatoService = candidatoService
    }

    @Override
    Long cadastrar(Candidato candidato) {
        try {
            return getCandidatoService().cadastrar(candidato)
        } catch (Exception e) {
            println "Erro ao cadastrar candidato(a): ${e.message}"
        }
    }

    @Override
    List<Candidato> listar() {
        try {
           return getCandidatoService().listar()
        } catch (Exception e) {
            println "Erro ao recuperar lista de candidatos: ${e.message}"
        }
    }

    @Override
    void editar(Candidato candidato) {
        try {
            getCandidatoService().editar(candidato)
        } catch (Exception e) {
            println "Erro ao editar candidato(a): ${e.message}"
        }
    }

    @Override
    void remover(Long candidatoId) {
        try {
            if (verificarExistencia(candidatoId)) {
                getCandidatoService().remover(candidatoId)
            } else {
                println "Candidato(a) com ID ${candidatoId} não encontrado(a)."
            }
        } catch (Exception e) {
            println "Erro ao remover candidato(a): ${e.message}"
        }
    }

    @Override
    boolean verificarExistencia(Long candidatoId) {
        try {
            return getCandidatoService().verificarExistencia(candidatoId)
        } catch (Exception e) {
            println "Erro ao verificar a existência do candidato: ${e.message}"
        }
    }

}
