package com.aczg.service


import com.aczg.DAO.interfaces.IVagaDAO
import com.aczg.model.Vaga
import com.aczg.service.interfaces.IVagaService
import spock.lang.Specification

class VagaServiceSpec extends Specification{

    IVagaDAO vagaDAO = Mock()
    IVagaService vagaService = new VagaService(vagaDAO)

    def "Deve adicionar uma nova vaga com sucesso"() {
        given: "Um Mock de Vaga"
        Vaga vaga = Mock(Vaga)

        when: "O serviço de adicionar vaga é chamado"
        vagaService.cadastrar(vaga)

        then: "O método salvar do DAO é chamado uma vez com o candidato correto e retorna o ID da vaga cadastrada"
        1 * vagaDAO.cadastrar(vaga) >> 1L
    }

    def "Deve listar todos os vagas"() {
        given: "Uma lista mockada de vaga"
        List<Vaga> vagasEsperados = [new Vaga("Vaga teste", "Descrição", "lolca",1L )]
        vagaDAO.listar() >> vagasEsperados

        when: "Mostrar vagas"
        List<Vaga> vagas = vagaService.listar()

        then: "A lista de vagas deve ser a esperada"
        vagas == vagasEsperados
    }

    def "Deve editar vaga quando ela existe"() {
        given: "Uma vaaga mockada com um ID válido"
        Vaga vaga = Mock(Vaga)
        vaga.id >> 1L
        vaga.nome >> "Vaga Teste"

        and: "Simulação da verificação de existência como verdadeiro"
        vagaService.metaClass.verificarExistencia = { Long id -> true }

        when: "chamando o método editar service"
        vagaService.editar(vaga)

        then: "O método editar do vagaDAO deve ser chamado uma vez com a vaga"
        1 * vagaDAO.editar(vaga)
    }

    def "Deve deletar uma vaga pelo ID"() {
        given: "Uma vaga mockada com um ID válido"
        Vaga vaga = Mock(Vaga)
        vaga.id >> 1L
        vaga.nome >> "Vaga Teste"

        and: "Simulação da verificação de existência como verdadeiro"
        vagaService.metaClass.verificarExistencia = { Long id -> true }

        when: "Deletar vaga"
        vagaService.remover(vaga.id)

        then: "O método de deletar deve ser chamado com o ID correto"
        1 * vagaDAO.remover(vaga.id)
    }

}
