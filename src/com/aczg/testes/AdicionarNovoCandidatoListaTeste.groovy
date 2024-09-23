package com.aczg.testes

import com.aczg.model.Candidato
import com.aczg.repository.CandidatoRepository
import spock.lang.Specification

class AdicionarNovoCandidatoListaTeste extends Specification {

    def "Testa se um novo candidato é adicionado corretamente a lista" (){
        given: "Um instância de CandidatoRepository que contém um lista de candidatos pré cadastrados"
        def candidatoRepository = new CandidatoRepository()

        and: "Novo Candidato"
        def candidato1 = new Candidato("João", "joao@example.com", "12345678900", 30, "SP", "12345-678", "Descrição", ["Competencia1", "Competencia2"])

        when: "Um novo candidato é adicionado a lista"
        candidatoRepository.cadastrarNovoCandidato(candidato1)

        then: "o candidato deve ter sido adicionado corretamente a lista"
        candidatoRepository.obterCandidatosCadastrados().contains(candidato1)

        and: "A lista de candidatos deve ter um novo candidato"
        candidatoRepository.obterCandidatosCadastrados().size() == old(candidatoRepository.obterCandidatosCadastrados().size()) + 1
    }
}

/*

def "Descrição do teste"
given: "Comentário"
when: "Comentário"
then: "Comentário"

Entendendo
given: Aqui você define o contexto do teste, como a criação de objetos.
when: Descreve a ação que você está testando.
then: Define a expectativa ou resultado esperado da ação.

 */