package com.aczg.testes

import com.aczg.model.Empresa
import com.aczg.repository.EmpresaRepository
import spock.lang.Specification
import com.aczg.controller.validadorEntrada

class AdicionarNovaEmpresaListaTeste extends Specification implements validadorEntrada{

    def "Testa se uma nova empresa é adicionada corretamente lista" (){
        given: "uma instância de EmpresaRepository que contém um lista de empresas pré cadastrados"
        def empresaRepository = new EmpresaRepository()

        and: "uma nova empresa"
        def empresa1 = new Empresa("ZG Soluções", "info@aczg.com.br", "SP", "11155-012", "Especialistas redução de glossas hospitalares", "45.678.901/0001-00", "Brasil", ["Java", "Spring", "MySQL"] )

        when: "um novo contato deve ser adicionado a lista"
        empresaRepository.cadastrarNovaEmpresa(empresa1)

        then: "a empresa deve ter sido adicionada corretamente a lista"
        empresaRepository.obterEmpresasCadastradas().contains(empresa1)

        and: "A lista de empressas deve ter uma nova empresa"
        empresaRepository.obterEmpresasCadastradas().size() == old(empresaRepository.obterEmpresasCadastradas().size()) + 1


    }

}
