package com.aczg.view.interfaces

import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter

trait ValidadorEntradaTrait {

    public Scanner scanner = new Scanner(System.in)

    String validarTexto(String mensagemInterativa, Scanner entradaScanner = scanner) {
        while (true) {
            print "${mensagemInterativa}"
            if (!entradaScanner.hasNextLine()) {
                println "Erro: Nenhuma linha de entrada encontrada"
                return null
            }
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

    Date validarData(String mensagemInterativa, Scanner entradaScanner = scanner) {

        DateTimeFormatter formatterEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        while (true) {
            print "${mensagemInterativa}"
            try {
                String resposta = entradaScanner.nextLine()
                if(resposta && !resposta.trim().isEmpty()){
                    LocalDate data = LocalDate.parse(resposta, formatterEntrada)
                    return Date.valueOf(data)
                }

            } catch (IllegalArgumentException e) {
                println "Erro: Data inválida. Use o formato DD/MM/YYYY."
            }
        }
    }

    String declararEstruturaDeValidacoesComRegex(String tipo){

        String regex = null;

        switch (tipo) {
            case 'nome': regex = /^(?:[A-Za-zÀ-ÿ]+(?:\s+[A-Za-zÀ-ÿ]+)+)$/; break
            case 'sobrenome': regex = /^[\w\W\s]{4,150}$/; break
            case 'email': regex = /^[a-zA-Z0-9._%+-]+@\w+\.\w{2,}(?:\.[a-z]{2})?$/; break
            case 'telefone': regex = /^\(\d{2}\)\s?\d{4,5}-\d{4}$/; break
            case 'linkedin': regex = /^https:\/\/[a-z]{2,3}\.linkedin\.com\/in\/[A-Za-z0-9._-]{1,30}$/; break
            case 'cpf': regex = /^(?:\d{11}|\d{3}\.\d{3}\.\d{3}-\d{2})$/; break
            case 'cnpj': regex = /^(?:\d{14}|\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2})$/; break;
            case 'idade': regex = /^(?:1[6-9]|[2-5][0-9]|6[0-5])$/; break;
            case 'cep': regex = /^(?:\d{8}|\d{5}-\d{3})$/; break;
            case 'estado': regex = /(?i)^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$/; break
            case 'descricao': regex = /^[\w\W\s]{10,500}$/; break;
            case 'competencias': regex = /^(?:[a-zA-ZÀ-ÿ\s]+,){2,}[a-zA-ZÀ-ÿ\s]+$/; break;

        }
        return regex
    }

    String exibirMensagemDeErro(String tipo) {
        String mensagem = null;

        switch (tipo) {
            case 'nome': mensagem = "Erro: O nome deve conter pelo menos dois nomes e não pode conter números."; break
            case 'sobrenome': mensagem = "Erro: O nome deve conter pelo menos quatro caracteres."; break
            case 'email': mensagem = "Erro: O formato do e-mail está inválido. Exemplo válido: exemplo@dominio.com."; break
            case 'telefone': mensagem = "Erro: O telefone deve seguir o formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX."; break
            case 'linkedin': mensagem = "Erro: O link do LinkedIn deve seguir o formato https://xx.linkedin.com/in/username."; break
            case 'cpf': mensagem = "Erro: O CPF deve estar no formato XXX.XXX.XXX-XX ou apenas números."; break
            case 'cnpj': mensagem = "Erro: O CNPJ deve estar no formato XX.XXX.XXX/XXXX-XX ou apenas números."; break
            case 'idade': mensagem = "Erro: A idade deve estar entre 16 e 65 anos."; break
            case 'cep': mensagem = "Erro: O CEP deve estar no formato XXXXX-XXX ou apenas números."; break
            case 'estado': mensagem = "Erro: Digite a sigla do estado com 2 caracteres ex: Para 'Bahia' digite apenas: BA"; break
            case 'descricao': mensagem = "Erro: A descrição deve ter entre 10 e 500 caracteres."; break
            case 'competencias': mensagem = "Erro: As competências devem ser separadas por vírgulas, ex: Competência 1, Competência 2."; break
        }
        return mensagem
    }

    String validarTextoComRegex(String tipo, String mensagemInterativa, Scanner entradaScanner = scanner) {

        String regex = declararEstruturaDeValidacoesComRegex(tipo)

        while (true) {
            print "${mensagemInterativa}"
            String resposta = entradaScanner.nextLine()
            if (resposta && resposta.matches(regex)) {
                return resposta
            }
            println exibirMensagemDeErro(tipo)
        }
    }

    void fecharScanner(){
        if (scanner) {
            scanner.close()
        }
    }

}
