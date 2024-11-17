package com.aczg.controller.endpoints

import com.aczg.model.Empresa
import com.aczg.model.Candidato
import com.aczg.model.Vaga

class ResponseUtil {

    static Map buildCandidatoResponse(Long candidatoId, Candidato candidato, String operacao) {
        Map responseMap = [
                candidato: [
                        id: candidatoId,
                        nome: candidato.getNome(),
                        sobrenome: candidato.getSobrenome(),
                        email: candidato.getEmail(),
                        telefone: candidato.getTelefone(),
                        linkedin: candidato.getLinkedin(),
                        cpf: candidato.getCpf(),
                        estado: candidato.getEstado(),
                        cep: candidato.getCep(),
                        descricao: candidato.getDescricao(),
                        formacao: candidato.getFormacao()
                ],
                links: [
                        self: "/api/v1/candidatos/${candidatoId}",
                        list: "/api/v1/candidatos"
                ]
        ]

        if (operacao == "POST") {
            responseMap.message = "Candidato cadastrado com sucesso."
        }

        return responseMap
    }

    static Map buildEmpresaResponse(Long empresaId, Empresa empresa, String operacao) {
        Map responseMap = [
                empresa: [
                        id: empresaId,
                        nome: empresa.getNome(),
                        email: empresa.getEmail(),
                        estado: empresa.getEstado(),
                        cnpj: empresa.getCnpj(),
                        pais: empresa.getPais(),
                        cep: empresa.getCep(),
                        descricao: empresa.getDescricao()
                ],
                links: [
                        self: "/api/v1/empresas/${empresaId}",
                        list: "/api/v1/empresas"
                ]
        ]

        if (operacao == "POST") {
            responseMap.message = "Empresa cadastrada com sucesso."
        }

        return responseMap
    }


    static Map buildVagaResponse(Long vagaId, Vaga vaga, String operacao) {
        Map responseMap = [
                vaga: [
                        id: vagaId,
                        titulo: vaga.nome(),
                        descricao: vaga.descricao(),
                        requisitos: vaga.local(),
                        localizacao: vaga.empresaId()
                ],
                links: [
                        self: "/api/v1/vagas/${vagaId}",
                        list: "/api/v1/vagas"
                ]
        ]

        if (operacao == "POST") {
            responseMap.message = "Vaga cadastrada com sucesso."
        }

        return responseMap
    }
}
