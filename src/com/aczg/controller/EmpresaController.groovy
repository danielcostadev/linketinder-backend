package com.aczg.controller

import com.aczg.model.Empresa
import com.aczg.repository.EmpresaRepository

class EmpresaController implements Renderizavel, validadorEntrada{
    EmpresaRepository empresaRepository = new EmpresaRepository()

    //TODO: Realizar devidas validações de acodo com os campos, quando me aprofundar em REGEX
    void adicionarEmpresa(){
        String nome = validarTexto("Digite o nome da empresa: ")
        String emailCorporativo = validarTexto("Digite o email corporativo da empresa: ")
        String estado = validarTexto("Digite o estado da empresa: ")
        String cep = validarTexto("Digite o cep da empresa: ")
        String descricao = validarTexto("Digite a descricao da empresa: ")
        String cnpj = validarTexto("Digite o cpnj da empresa: ")
        String pais = validarTexto("Digite o país da empresa: ")
        String competenciasEsperadas = validarTexto("Digite as competéncias separadas por virgula: ")
        List<String> listaCompetenciasEsperadas = competenciasEsperadas.split(",\\s*")

        cadastrarEmpresa(nome,emailCorporativo,estado,cep,descricao,cnpj,pais,listaCompetenciasEsperadas)

    }

    void cadastrarEmpresa(String nome, String email, String estado, String cep, String descricao, String cnpj, String pais, List<String> competencias) {
        Empresa novaEmpresa = new Empresa(nome, email, estado, cep, descricao, cnpj, pais, competencias)
        empresaRepository.cadastrarNovaEmpresa(novaEmpresa)
        println " "
        println "Empresa '${nome}' cadastrada com sucesso!"
    }


    @Override
    void mostrarDados(){
        List<Empresa> empresasPreCadastradas = empresaRepository.obterEmpresasCadastradas()
        println " "
        println "================== EMPRESAS CADASTRADAS =================="
        for(empresa in empresasPreCadastradas){
            empresa.mostrarInformacoes()
            50.times {print '-'}
        }
    }
}
