package com.aczg.repository

import com.aczg.model.Candidato

class CandidatoRepository {

    List<Candidato> obterCandidatosCadastrados(){

        return [
                new Candidato("Ana Beatriz", "ana.beatriz@gmail.com", "123.456.789-01", 28, "SP", "04567-890", "Desenvolvedora Frontend apaixonada por design de interfaces e usabilidade.", ["HTML", "CSS", "JavaScript", "React"]),
                new Candidato("Carlos Eduardo", "carlos.eduardo@yahoo.com", "987.654.321-09", 32, "RJ", "12345-678", "Engenheiro de Dados com foco em Big Data e Machine Learning.", ["Python", "Spark", "Hadoop", "SQL"]),
                new Candidato("Mariana Santos", "mariana.santos@outlook.com", "456.789.123-45", 29, "MG", "23456-789", "Especialista em DevOps com experiência em automação de infraestrutura.", ["Docker", "Kubernetes", "Jenkins", "AWS"]),
                new Candidato("Rodrigo Almeida", "rodrigo.almeida@hotmail.com", "321.654.987-65", 35, "RS", "56789-012", "Desenvolvedor Backend com ampla experiência em Java e Spring Framework.", ["Java", "Spring", "MySQL", "REST"]),
                new Candidato("Luiza Martins", "luiza.martins@gmail.com", "789.123.456-78", 27, "BA", "67890-123", "Analista de Sistemas com foco em segurança da informação.", ["Segurança", "Pentest", "Cryptografia", "Linux"])
        ]
    }
}
