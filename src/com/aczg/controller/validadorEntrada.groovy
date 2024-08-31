package com.aczg.controller

trait validadorEntrada {

    public Scanner scanner = new Scanner(System.in)

    String validarTexto(String mensagemInterativa, Scanner entradaScanner = scanner) {
        while (true) {
            print "${mensagemInterativa}"
            String resposta = entradaScanner.nextLine()
            if (resposta && !resposta.trim().isEmpty()) {
                return resposta
            }
            println "Entrada Inválida!"
        }
    }

    Integer validarInteiro(String mensagemInterativa, Scanner entradaScanner = scanner) {
        while (true) {
            print "${mensagemInterativa}"
            try {
                int valor = Integer.parseInt(entradaScanner.nextLine())
                if (valor) {
                   return valor
                }
                println "\nValor inválido"
            } catch (NumberFormatException e) {
                println "\n${e.getMessage()} Valor inválido! Digite um número válido."
            }

        }
    }



    void fecharScanner(){
        if (scanner) {
            scanner.close()
        }
    }

}