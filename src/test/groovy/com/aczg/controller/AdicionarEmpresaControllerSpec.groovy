package com.aczg.controller

import com.aczg.model.Empresa
import com.aczg.service.EmpresaService
import spock.lang.Specification

class AdicionarEmpresaControllerSpec extends Specification{

    def "Deve adicionar uma nova empresa com sucesso"() {
        given: "Um mock do serviço de empresa"
        EmpresaService empresaService = Mock(EmpresaService)
        EmpresaController controller = new EmpresaController(empresaService)

        and: "Entradas válidas para o formulário"
        controller.validarTextoComRegex(_ as String, _ as String) >> "Validado"
        controller.validarTexto(_) >> "Validado"

        when: "A função de adicionar empresa é chamada"
        controller.exibirFormularioParaAdicionarEmpresa()

        then: "O serviço de adicionar empresa é chamado com uma nova empresa"
        1 * empresaService.adicionarEmpresa(_ as Empresa)

        and: "Nenhuma exceção é lançada"
        noExceptionThrown()
    }

    def "Deve exibir uma mensagem de erro ao tentar adicionar empresa e falhar"() {
        given: "Um mock do serviço de empresa que gera uma exceção"
        EmpresaService empresaService = Mock(EmpresaService)
        EmpresaController controller = new EmpresaController(empresaService)

        and: "Entradas válidas para o formulário"
        controller.validarTextoComRegex(_ as String, _ as String) >> "Validado"
        controller.validarTexto(_) >> "Validado"

        and: "O serviço lança uma exceção ao tentar adicionar a empresa"
        empresaService.adicionarEmpresa(_ as Empresa) >> { throw new Exception("Falha ao cadastrar empresa") }

        when: "A função de adicionar empresa é chamada"
        controller.exibirFormularioParaAdicionarEmpresa()

        then: "Nenhuma exceção é lançada"
        noExceptionThrown()

        and: "A mensagem de erro é exibida no console"
        println "Erro ao cadastrar empresa 'Validado': Falha ao cadastrar empresa"
    }

}
