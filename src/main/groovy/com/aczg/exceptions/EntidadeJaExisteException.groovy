package com.aczg.exceptions

class EntidadeJaExisteException extends Exception{

    public EntidadeJaExisteException() {
        super("Candidato já existe com o e-mail ou CPF fornecido.");
    }

}
