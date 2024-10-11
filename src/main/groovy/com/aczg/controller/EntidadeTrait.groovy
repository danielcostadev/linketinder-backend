package com.aczg.controller

trait EntidadeTrait {

    void manipularEntidade(Long id, String nomeEntidade,Closure<Boolean> entidadeExiste,Closure<Void> acaoEntidade,String tipoAcao) {
        try {
            if (entidadeExiste.call(id)) {
                acaoEntidade.call(id)
                println "${nomeEntidade} com ID '${id}' ${tipoAcao} com sucesso!"
            } else {
                println "Erro: ${nomeEntidade} com ID '${id}' não existe."
            }
        } catch (Exception e) {
            println "Erro ao ${tipoAcao.toLowerCase()} ${nomeEntidade}: ${e.message}"
        }
    }

}