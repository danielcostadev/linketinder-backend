package com.aczg.controller

trait validadorEntrada {

    public Scanner scanner = new Scanner(System.in)

    String validarResposta(String mensagemInterativa) {
        while (true) {
            print "${mensagemInterativa}"
            String resposta = scanner.nextLine()
            if (resposta && !resposta.trim().isEmpty()) {
                return resposta
            }
            println "Entrada Inválida!"
        }
    }

    Integer validarInt(String mensagemInterativa) {
        while (true) {
            print "${mensagemInterativa}"
            try {
                int valor = Integer.parseInt(scanner.nextLine())
                if (valor) {
                   return valor
                }
                println "\nValor inválido"
            } catch (NumberFormatException e) {
                println "\nValor inválido! Digite um número válido."
            }

        }
    }



    void fecharScanner(){
        if (scanner) {
            scanner.close()
        }
    }

}