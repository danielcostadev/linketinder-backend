package com.aczg.exceptions

class EntidadeJaExisteException extends Exception{

    public EntidadeJaExisteException() {
        super("Entidade já existe com o e-mail ou CPF/CNPJ fornecidos.");
    }

}
