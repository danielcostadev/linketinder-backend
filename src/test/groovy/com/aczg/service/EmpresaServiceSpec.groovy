package com.aczg.service

import com.aczg.DAO.interfaces.IEntidadeDAO
import com.aczg.interfaces.IEntidade
import com.aczg.model.Empresa
import spock.lang.Specification

class EmpresaServiceSpec extends Specification{

    IEntidadeDAO empresaDAO = Mock()
    IEntidade empresaService = new EmpresaService(empresaDAO)

    def "Deve adicionar uma nova empresa com sucesso"() {
        given: "Um Mock de Empresa"
        Empresa empresa = Mock(Empresa)

        when: "O serviço de adicionar empresa é chamado"
        empresaService.cadastrar(empresa)

        then: "O método salvar do DAO é chamado uma vez com a empresa correta e retorna o ID da empresa cadastrada"
        1 * empresaDAO.cadastrar(empresa) >> 1L
    }

    def "Deve listar todos as empresas"() {
        given: "Uma lista mockada de empresas"
        List<Empresa> empresasEsperadas = [new Empresa('Pastelsoft Tech', '12345678000100', 'contato@pastelsoft.com', 'Brasil', 'SP', '98765432', 'Empresa de desenvolvimento de ERPs', 'pastel123')]
        empresaDAO.listar() >> empresasEsperadas

        when: "Mostrar empresas"
        List<Empresa> empresas = empresaService.listar()

        then: "A lista de empresas deve ser a esperada"
        empresas == empresasEsperadas
    }

    def "Deve editar uma empresa quando ela existe"() {
        given: "Uma empresa mockado com um ID válido"
        Empresa empresa = Mock(Empresa)
        empresa.id >> 1L
        empresa.nome >> "Empresa Teste"

        and: "Simulação da verificação de existência como verdadeiro"
        empresaService.metaClass.verificarExistencia = { Long id -> true }

        when: "chamando o método editar service"
        empresaService.editar(empresa)

        then: "O método editar do empresaDAO deve ser chamado uma vez com a empresa"
        1 * empresaDAO.editar(empresa)
    }

    def "Deve deletar uma empresa pelo ID"() {
        given: "Uma empresa mockada com um ID válido"
        Empresa empresa = Mock(Empresa)
        empresa.id >> 1L
        empresa.nome >> "Empresa Teste"

        and: "Simulação da verificação de existência como verdadeiro"
        empresaService.metaClass.verificarExistencia = { Long id -> true }

        when: "Deletar empresa"
        empresaService.remover(empresa.id)

        then: "O método de deletar deve ser chamado com o ID correto"
        1 * empresaDAO.remover(empresa.id)
    }

}
