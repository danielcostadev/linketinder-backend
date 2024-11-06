package com.aczg.exceptions

class DatabaseException extends Exception{

    public DatabaseException(Throwable causa) {
        super("Erro ao acessar o banco de dados.", causa);
    }

}
