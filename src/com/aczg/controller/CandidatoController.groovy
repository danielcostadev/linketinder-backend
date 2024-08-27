package com.aczg.controller

import com.aczg.model.Candidato
import com.aczg.repository.CandidatoRepository

class CandidatoController implements Renderizavel{
    CandidatoRepository candidatoRepository = new CandidatoRepository()

    @Override
    void mostrarDados(){
        List<Candidato> candidatosPreCadastrados = candidatoRepository.obterCandidatosCadastrados()

        for(candidato in candidatosPreCadastrados){
            candidato.mostrarInformacoes()
            50.times {print '-'}
            println " "
        }
    }
}
