package com.aczg.repository

import com.aczg.model.Empresa

class EmpresaRepository {

    private List<Empresa> empresasPreCadastradas = [
            new Empresa("Tech Solutions", "contato@techsolutions.com.br", "SP", "12345-678", "Empresa de desenvolvimento de software", "12.345.678/0001-00", "Brasil", ["Python", "Java", "Angular"]),
            new Empresa("Data Experts", "contact@dataexperts.com", "RJ", "23456-789", "Consultoria em análise de dados", "98.765.432/0001-00", "Brasil", ["SQL", "Python", "PowerBI"]),
            new Empresa("Cloud Innovators", "info@cloudinnovators.com", "MG", "34567-890", "Serviços de infraestrutura em nuvem", "23.456.789/0001-00", "Brasil", ["Docker", "Kubernetes", "AWS"]),
            new Empresa("Web Creators", "contact@webcreators.com", "SP", "45678-901", "Desenvolvimento de websites e aplicações web", "34.567.890/0001-00", "Brasil", ["React", "CSS", "JavaScript"]),
            new Empresa("Backend Masters", "info@backendmasters.com", "RS", "56789-012", "Especialistas em soluções backend", "45.678.901/0001-00", "Brasil", ["Java", "Spring", "MySQL"]),
    ]

    private List<Empresa> empresasCadastradas = empresasPreCadastradas.collect {it}

    List<Empresa> obterEmpresasCadastradas(){
        empresasCadastradas
    }

    void cadastrarNovaEmpresa(Empresa novaEmpresa){
        empresasCadastradas.add(novaEmpresa)
    }

}
