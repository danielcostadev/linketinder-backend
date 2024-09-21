import { Candidato } from "../model/candidato";
import { BotoesService } from "../service/botoes.service";
import { CandidatoService } from "../service/candidato.service";
import Chart from 'chart.js/auto';
// Centralização de interação com o DOM
var tabelaItensCandidato = document.getElementById("tabela-itens-candidato");
var nomeCandidato = document.getElementById("nomeCandidato");
var cpfCandidato = document.getElementById("cpfCandidato");
var descricaoCandidato = document.getElementById("descricaoCandidato");
export class CandidatoController {
    constructor() {
        this.candidatoService = new CandidatoService();
        this.botoesService = new BotoesService();
    }
    adicionarCandidato(dadosFormulario) {
        const id = this.candidatoService.gerarId();
        const candidato = new Candidato(id, dadosFormulario.get("nome"), dadosFormulario.get("email"), dadosFormulario.get("cpf"), dadosFormulario.get("idade"), dadosFormulario.get("estado"), dadosFormulario.get("cep"), dadosFormulario.get("descricao"), dadosFormulario.get("formacao"), dadosFormulario.get("competencias").split(","));
        this.candidatoService.adicionarCandidato(candidato);
    }
    excluirCandidato(id) {
        this.candidatoService.exlcuirCandidato(id);
    }
    listarCandidatos() {
        const candidatos = this.candidatoService.listarCandidatos();
        if (candidatos.length > 0) {
            tabelaItensCandidato.innerHTML = "";
            candidatos.forEach((candidato, index) => {
                var botaoContainer = this.botoesService.criarBotaoContainer();
                var linha = tabelaItensCandidato.insertRow();
                var cellCompetencias = linha.insertCell(0);
                var cellFormacao = linha.insertCell(1);
                var cellOpcoes = linha.insertCell(2);
                cellCompetencias.innerHTML = Array.isArray(candidato.competencias) ? candidato.competencias.join(', ') : candidato.competencias;
                cellFormacao.innerHTML = candidato.formacao;
                cellOpcoes.appendChild(botaoContainer);
                // Para que os botões funcionem, devemos atribuir eventos logo após serem adicionados
                var botaoVer = botaoContainer.querySelector('.botao1');
                // var botaoEditar = botaoContainer.querySelector('.botao2');
                var botaoExcluir = botaoContainer.querySelector('.botao3');
            });
        }
    }
    exibirCandidato(idCandidato) {
        const candidato = this.candidatoService.obterCandidato(idCandidato);
        if (candidato) {
            nomeCandidato.textContent = candidato.nome || 'Nome não disponível';
            cpfCandidato.textContent = candidato.cpf || 'CPF não disponível';
            descricaoCandidato.textContent = candidato.descricao || 'Descrição não disponível';
        }
    }
    // public contarCompetencias(): void{
    //     var contagem = this.candidatoService.contarCompetencias(this.candidatoService.listarCandidatos());
    //     console.log(contagem);
    // }
    contarCompetencias() {
        // Chama o serviço para contar as competências dos candidatos
        const contagem = this.candidatoService.contarCompetencias(this.candidatoService.listarCandidatos());
        // Verifica se a contagem tem dados
        if (!contagem || Object.keys(contagem).length === 0) {
            console.error("Nenhuma competência encontrada");
            return;
        }
        // Gera o gráfico com os dados de contagem
        this.gerarGraficoDeCompetencias(contagem);
    }
    gerarGraficoDeCompetencias(contagem) {
        // Obter o contexto do elemento canvas no DOM
        const ctx = document.getElementById('competenciasChart').getContext('2d');
        if (!ctx) {
            console.error('Elemento canvas não encontrado');
            return;
        }
        // Transformar os dados em arrays para serem usados no gráfico
        const competencias = Object.keys(contagem);
        const valores = Object.values(contagem);
        // Criar o gráfico de barras
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: competencias, // Competências no eixo X
                datasets: [{
                        label: 'Quantidade de Competências',
                        data: valores, // Quantidades no eixo Y
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1
                    }]
            },
            options: {
                indexAxis: 'y',
                scales: {
                    y: {
                        beginAtZero: true // Começa o gráfico a partir de zero
                    }
                }
            }
        });
    }
}
