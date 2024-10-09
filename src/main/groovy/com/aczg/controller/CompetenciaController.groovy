package com.aczg.controller

import com.aczg.service.CompetenciaService

class CompetenciaController implements validadorEntrada{

    CompetenciaService competenciaService

    void adicionarCompetencia() {
        String competencias = validarTexto("Digite as competências separadas por vírgula: ");
        List<String> listaCompetencias = competencias.split(",\\s*");

        for (String nome : listaCompetencias) {
            try {
                competenciaService.cadastrarCompetencia(nome);
                println("Competência '${nome}' cadastrada com sucesso!");
            } catch (Exception e) {
                println("Erro ao cadastrar competência '${nome}': ${e.message}");
            }
        }
    }

}
