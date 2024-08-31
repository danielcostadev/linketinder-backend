package com.aczg.controller

class MenuController implements validadorEntrada{

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
                String opcaoSelecionada = validarTexto("DICA: Digite um número de [1 a 5]: ")

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
                        break
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

    private void cadastrarEmpresa(){
        empresaController.adicionarEmpresa()
    }

    private void cadastrarCandidato(){
        candidatoController.adicionarCandidato()
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
        fecharScanner()
        System.exit(0);
    }
}
