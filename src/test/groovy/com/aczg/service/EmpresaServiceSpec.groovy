package com.aczg.service

import com.aczg.DAO.CompetenciaDAO
import com.aczg.DAO.ConexaoDAO
import com.aczg.DAO.EmpresaDAO
import com.aczg.DAO.VagaDAO
import com.aczg.model.Empresa
import groovy.sql.Sql
import spock.lang.Specification

class EmpresaServiceSpec extends Specification {

    EmpresaService empresaService
    EmpresaDAO empresaDAO = Mock()
    VagaDAO vagaDAO = Mock()
    CompetenciaDAO competenciaDAO = Mock()
    ConexaoDAO conexaoDAO = Mock()
    Sql sql = Mock()

    def setup() {
        empresaService = new EmpresaService(empresaDAO, vagaDAO, competenciaDAO)
        conexaoDAO.getSql() >> sql
    }

    def "Deve cadastrar empresa com sucesso"() {
        given: "Dados de uma nova empresa"
        String nome = "Empresa X"
        String email = "empresa@exemplo.com"
        String estado = "SP"
        String cnpj = "12.345.678/0001-99"
        String pais = "Brasil"
        String cep = "01000-000"
        String descricao = "Empresa de tecnologia"
        String senha = "senhaSegura"

        when: "O método cadastrarEmpresa é chamado"
        empresaDAO.adicionarEmpresa(_) >> 1L
        Long empresaId = empresaService.adicionarEmpresa(nome, email, estado, cnpj, pais, cep, descricao, senha)

        then: "A empresa é cadastrada com sucesso"
        empresaId == 1L
    }

    def "Deve cadastrar vaga com sucesso"() {
        given: "Dados de uma nova vaga"
        String nome = "Desenvolvedor"
        String descricao = "Desenvolver software"
        String local = "Remoto"
        Long empresaId = 1L

        when: "O método cadastrarVaga é chamado"
        vagaDAO.adicionarVaga(nome, descricao, local, empresaId) >> 2L  // Parâmetros explícitos
        Long vagaId = empresaService.adicionarVaga(nome, descricao, local, empresaId)

        then: "A vaga é cadastrada com sucesso"
        vagaId == 2L
    }

    def "Deve listar todas as empresas"() {
        given: "Empresas no banco de dados"
        List<Empresa> listaEmpresas = [new Empresa(1L,"Empresa X","abc@abc.com","SE","12345678000199","Brasil","49400000","testando descricao grande","senha000")]
        empresaDAO.listarEmpresas() >> listaEmpresas

        when: "O método mostrarEmpresas é chamado"
        List<Empresa> empresas = empresaService.listarEmpresas()

        then: "A lista de empresas é retornada corretamente"
        empresas.size() == 1
        empresas == listaEmpresas
    }

    def "Deve atualizar uma empresa com sucesso"() {
        given: "Uma empresa existente"
        Empresa empresa = new Empresa(1L,"Empresa X","abc@abc.com","SE","12345678000199","Brasil","49400000","testando descricao grande","senha000")

        when: "O método atualizarEmpresa é chamado"
        empresaService.atualizarEmpresa(empresa)

        then: "A empresa é atualizada"
        1 * empresaDAO.atualizarEmpresa(empresa)
    }

    def "Deve deletar uma empresa com sucesso"() {
        given: "O ID de uma empresa existente"
        Long empresaId = 1L

        when: "O método deletarEmpresa é chamado"
        empresaService.removerEmpresa(empresaId)

        then: "A empresa é deletada"
        1 * empresaDAO.removerEmpresa(empresaId)
    }
}
