package com.aczg.model

class Empresa extends Pessoa {

    String cnpj
    String pais
    String emailCorporativo
    List<String> competenciasEsperadas

    Empresa(String nome, String emailCorporativo, String estado, String cep, String descricao, String cnpj, String pais, List<String> competenciasEsperadas) {
        super(nome, estado, cep, descricao)
        this.cnpj = cnpj
        this.pais = pais
        this.emailCorporativo = emailCorporativo
        this.competenciasEsperadas = competenciasEsperadas
    }

    @Override
    void mostrarInformacoes() {
        print """ 
        Nome: ${nome}
        Email Corporativo: ${emailCorporativo}
        Estado: ${estado}
        CEP: ${cep}
        Descrição: ${descricao}
        CNPJ: ${cnpj}
        País: ${pais}
        Competencias Esperadas: ${competenciasEsperadas.join(', ')}
        """.stripIndent()
    }
}
