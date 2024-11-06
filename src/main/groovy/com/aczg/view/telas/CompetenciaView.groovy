    package com.aczg.view.telas

    import com.aczg.controller.interfaces.ICandidatoController
    import com.aczg.controller.interfaces.IVagaController
    import com.aczg.interfaces.ICompetencia
    import com.aczg.model.Competencia
    import com.aczg.service.interfaces.ManipulaEntidadeTrait
    import com.aczg.view.interfaces.ValidadorEntradaTrait
    import com.aczg.view.telas.interfaces.ICompetenciaView

    class CompetenciaView implements ICompetenciaView<Long>, ManipulaEntidadeTrait, ValidadorEntradaTrait{

        ICompetencia competenciaController
        ICandidatoController candidatoController
        IVagaController vagaController

        CompetenciaView(ICompetencia competenciaController, ICandidatoController candidatoController, IVagaController vagaController) {
            this.competenciaController = competenciaController
            this.candidatoController = candidatoController
            this.vagaController = vagaController
        }

        @Override
        void exibirFormularioDeCadastroCandidato() {
            try {
                Long candidatoId = validarInteiro("Digite o ID do candidato: ")
                String competencias = validarTexto("Digite as competências separadas por vírgula: ")
                List<String> listaCompetencias = competencias.split(",\\s*")
                competenciaController.cadastrar(listaCompetencias, candidatoId, null) // Passar ID do candidato
            } catch (Exception e) {
                println "Erro ao cadastrar competências: ${e.message}"
            }
        }

        @Override
        void exibirFormularioDeCadastroVaga() {
            try {
                Long vagaId = validarInteiro("Digite o ID da vaga: ")
                String competencias = validarTexto("Digite as competências separadas por vírgula: ")
                List<String> listaCompetencias = competencias.split(",\\s*")
                competenciaController.cadastrar(listaCompetencias, null, vagaId) // Passar ID da vaga
            } catch (Exception e) {
                println "Erro ao cadastrar competências: ${e.message}"
            }
        }

        @Override
        void exibirLista() {
            try {
                List<Competencia> competencias = competenciaController.listar()
                competencias.each { competencia ->
                    println "ID: ${competencia.id}, Nome: ${competencia.nome}"
                }
            } catch (Exception e) {
                println "Erro ao recuperar lista de competências: ${e.message}"
            }
        }
    }
