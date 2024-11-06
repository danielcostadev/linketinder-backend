package com.aczg.controller

import com.aczg.controller.interfaces.ICandidatoController
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.model.Candidato
import com.aczg.service.interfaces.ICandidatoService

class CandidatoController implements ICandidatoController<Candidato> {

    ICandidatoService candidatoService

    CandidatoController(ICandidatoService candidatoService) {
        this.candidatoService = candidatoService
    }

    @Override
    Long cadastrar(Candidato candidato) throws EntidadeJaExisteException, DatabaseException {
            return getCandidatoService().cadastrar(candidato)
    }

    @Override
    List<Candidato> listar() throws DatabaseException{
        return getCandidatoService().listar()
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
