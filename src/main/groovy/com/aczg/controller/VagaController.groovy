package com.aczg.controller

import com.aczg.service.EmpresaService

class VagaController implements validadorEntrada{

    EmpresaService empresaService

    void adicionarVaga(){

        String nome = validarTexto("Digite o TITULO da vaga: ")
        String descricao = validarTexto("Digite a DESCRIÇÃO da vaga: ")
        String local = validarTexto("Digite o LOCAL da vaga: ")

        try {
            empresaService.cadastrarVaga(nome,descricao,local)
            println("Vaga '${nome}' cadastrada com sucesso!");
        } catch (Exception e) {
            println("Erro ao cadastrar vaga '${nome}': ${e.message}");
        }
    }

}
