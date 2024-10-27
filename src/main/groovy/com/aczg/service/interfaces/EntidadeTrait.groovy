package com.aczg.service.interfaces

trait EntidadeTrait {

    void manipularEntidade(Long idEntidade, String nomeEntidadePrincipal, Closure<Boolean> entidadeExiste, Closure<Void> acaoEntidade, String tipoAcao, String nomeEntidadeSecundaria = null) {
        try {
            if (entidadeExiste.call(idEntidade)) {
                acaoEntidade.call(idEntidade)
                if (nomeEntidadeSecundaria) {
                    println "${nomeEntidadeSecundaria} associada com ${nomeEntidadePrincipal} de ID '${idEntidade}' foi ${tipoAcao} com sucesso!"
                } else {
                    println "${nomeEntidadePrincipal} com ID '${idEntidade}' foi ${tipoAcao} com sucesso!"
                }
            } else {
                println "Erro: ${nomeEntidadePrincipal} com ID '${idEntidade}' não existe."
            }
        } catch (Exception e) {
            println "Erro ao ${tipoAcao.toLowerCase()} ${nomeEntidadePrincipal}: ${e.message}"
        }
    }
}