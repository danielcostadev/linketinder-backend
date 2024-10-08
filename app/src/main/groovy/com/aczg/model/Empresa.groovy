package com.aczg.model

class Empresa extends Pessoa {

    String cnpj
    String pais

    Empresa(String nome, String email, String cnpj, String pais, String estado, String cep, String descricao, List<String> competencias, String senha, List<Vaga> vagas) {
        super(nome, email, estado, cep, descricao, competencias, senha, vagas)
        this.cnpj = cnpj
        this.pais = pais
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
