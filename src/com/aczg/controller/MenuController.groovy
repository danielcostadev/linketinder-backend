package com.aczg.controller

class MenuController {

    private Scanner scanner = new Scanner(System.in);

    CandidatoController candidatoController = new CandidatoController()
    EmpresaController empresaController = new EmpresaController()

    Boolean menuAtivo = true

    // TODO: Acrescentar mais opções para comportar o sistema de criação de vagas, curtidas e matchs
    void gerarMenu() {
        while (menuAtivo) {

            print """
            ================== MENU PRINCIPAL ==================
            1 - Cadastrar Empresa
            2 - Cadastrar Candidato
            3 - Listar Empresas
            4 - Listar Candidatos
            5 - Encerrar Aplicação
            ====================================================            
            """.stripIndent()

            try {
                String opcaoSelecionada = validarResposta("DICA: Digite um número de [1 a 5]: ")

                switch (opcaoSelecionada) {

                    case '1':
                        cadastrarEmpresa()
                        break
                    case '2':
                        cadastrarCandidato()
                        break
                    case '3':
                        listarEmpresas()
                        break
                    case '4':
                        listarCandidatos()
                        break
                    case '5':
                       encerrarAplicacao()
                    default:
                        println "ALERTA: Entrada inválida. Tente novamente!"
                }

            } catch (NoSuchElementException e){
                println "Erro ao ler a entrada: ${e.message}"
                encerrarAplicacao()
            } catch (IllegalStateException e){
                println "Scanner fechado ou em estado inválido: ${e.message}"
                encerrarAplicacao()
            }

        }

    }

    private String validarResposta(String mensagemInterativa) {
        while (true) {
            print "${mensagemInterativa}"
            String resposta = scanner.nextLine()

            if (resposta && !resposta.trim().isEmpty()) {
                return resposta
            }
            println "Entrada Inválida!"

        }
    }

    private void cadastrarEmpresa(){
        println "Funcionalidade em desenvolvimento..."

    }

    private void cadastrarCandidato(){
        println "Funcionalidade em desenvolvimento..."
    }

    private void listarEmpresas(){
        empresaController.mostrarDados()
    }

    private void listarCandidatos(){
        candidatoController.mostrarDados()
    }

    private void encerrarAplicacao(){
        println "Encerrando aplicação..."
        menuAtivo = false
        if (!scanner.closed) {
            scanner.close()
        }
        System.exit(0);
    }
}
