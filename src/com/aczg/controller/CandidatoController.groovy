package com.aczg.controller

import com.aczg.model.Candidato

class CandidatoController {

    List<Candidato> candidatosPreCadastrados = [
            new Candidato("Ana Beatriz", "ana.beatriz@gmail.com", "SP", "04567-890", "Desenvolvedora Frontend apaixonada por design de interfaces e usabilidade.", "123.456.789-01", 28, ["HTML", "CSS", "JavaScript", "React"]),
            new Candidato("Carlos Eduardo", "carlos.eduardo@yahoo.com", "RJ", "12345-678", "Engenheiro de Dados com foco em Big Data e Machine Learning.", "987.654.321-09", 32, ["Python", "Spark", "Hadoop", "SQL"]),
            new Candidato("Mariana Santos", "mariana.santos@outlook.com", "MG", "23456-789", "Especialista em DevOps com experiência em automação de infraestrutura.", "456.789.123-45", 29, ["Docker", "Kubernetes", "Jenkins", "AWS"]),
            new Candidato("Rodrigo Almeida", "rodrigo.almeida@hotmail.com", "RS", "56789-012", "Desenvolvedor Backend com ampla experiência em Java e Spring Framework.", "321.654.987-65", 35, ["Java", "Spring", "MySQL", "REST"]),
            new Candidato("Luiza Martins", "luiza.martins@gmail.com", "BA", "67890-123", "Analista de Sistemas com foco em segurança da informação.", "789.123.456-78", 27, ["Segurança", "Pentest", "Cryptografia", "Linux"]),
    ]

    void mostrarTodosCandidatos(){
        for(candidato in candidatosPreCadastrados){
            candidato.mostrarInformacoes()
            50.times {print '-'}
        }
    }
}
