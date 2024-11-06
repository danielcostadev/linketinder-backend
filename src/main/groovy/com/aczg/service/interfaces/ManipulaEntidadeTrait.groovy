package com.aczg.service.interfaces

import org.slf4j.Logger
import org.slf4j.LoggerFactory

trait ManipulaEntidadeTrait {

    private static final Logger log = LoggerFactory.getLogger(ManipulaEntidadeTrait)

    void manipularEntidade(Long idEntidade, String nomeEntidadePrincipal, Closure<Boolean> entidadeExiste, Closure<Void> acaoEntidade, String tipoAcao, String nomeEntidadeSecundaria = null) {
        try {
            if (entidadeExiste.call(idEntidade)) {
                acaoEntidade.call(idEntidade);
                if (nomeEntidadeSecundaria != null) {
                    log.info("{} associada com {} de ID '{}' foi {} com sucesso!", nomeEntidadeSecundaria, nomeEntidadePrincipal, idEntidade, tipoAcao);
                } else {
                    log.info("{} com ID '{}' foi {} com sucesso!", nomeEntidadePrincipal, idEntidade, tipoAcao);
                }
            } else {
                log.warn("{} com ID '{}' não existe.", nomeEntidadePrincipal, idEntidade);
            }
        } catch (Exception e) {
            log.error("Erro ao {} {}: {}", tipoAcao.toLowerCase(), nomeEntidadePrincipal, e.getMessage(), e);
        }
    }
}