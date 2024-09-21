import { Candidato } from "../model/candidato";
import { BotoesService } from "../service/botoes.service";
import { CandidatoService } from "../service/candidato.service";
import Chart from 'chart.js/auto';

// Centralização de interação com o DOM
const tabelaItensCandidato = document.getElementById("tabela-itens-candidato") as HTMLTableSectionElement;
const nomeCandidato = document.getElementById("nomeCandidato") as HTMLElement;
const cpfCandidato = document.getElementById("cpfCandidato") as HTMLElement;
const descricaoCandidato = document.getElementById("descricaoCandidato") as HTMLElement;

export class CandidatoController {

    private candidatoService = new CandidatoService();
    private botoesService = new BotoesService();

    public adicionarCandidato(dadosFormulario: FormData): void {

        const id = this.candidatoService.gerarId();

        const candidato = new Candidato(
            id,
            dadosFormulario.get("nome") as string,
            dadosFormulario.get("email") as string,
            dadosFormulario.get("cpf") as string,
            dadosFormulario.get("idade") as string,
            dadosFormulario.get("estado") as string,
            dadosFormulario.get("cep") as string,
            dadosFormulario.get("descricao") as string,
            dadosFormulario.get("formacao") as string,
            (dadosFormulario.get("competencias") as string).split(",")
        );
        this.candidatoService.adicionarCandidato(candidato);

    }

    public excluirCandidato(id: number): void {
        this.candidatoService.exlcuirCandidato(id);
    }

    private listarCandidatos(isAnonimo: boolean): void {
        const candidatos: Candidato[] = this.candidatoService.listarCandidatos();

        if (candidatos.length > 0) {
            tabelaItensCandidato.innerHTML = "";

            candidatos.forEach((candidato, index) => {
                var linha = tabelaItensCandidato.insertRow() as HTMLTableRowElement;

                if (isAnonimo) {

                    var botaoContainer = this.botoesService.criarBotaoContainerLike() as HTMLDivElement;

                    var cellCompetencias = linha.insertCell(0) as HTMLTableCellElement;
                    var cellFormacao = linha.insertCell(1) as HTMLTableCellElement;
                    var cellOpcoes = linha.insertCell(2) as HTMLTableCellElement;

                    cellCompetencias.innerHTML = Array.isArray(candidato.competencias) ? candidato.competencias.join(', ') : candidato.competencias;
                    cellFormacao.innerHTML = candidato.formacao;
                    cellOpcoes.appendChild(botaoContainer);

                } else {

                    var botaoContainer = this.botoesService.criarBotaoContainerReadUpdateDelete() as HTMLDivElement;

                    var cellId = linha.insertCell(0) as HTMLTableCellElement;
                    var cellNome = linha.insertCell(1) as HTMLTableCellElement;
                    var cellEmail = linha.insertCell(2) as HTMLTableCellElement;
                    var cellOpcoes = linha.insertCell(3) as HTMLTableCellElement;

                    cellId.innerHTML = candidato.id.toString();
                    cellNome.innerHTML = candidato.nome;
                    cellEmail.innerHTML = candidato.email
                    cellOpcoes.appendChild(botaoContainer);

                }

                // Para que os botões funcionem, devemos atribuir eventos logo após serem adicionados
                var botaoVer = botaoContainer.querySelector('.botao-ver');
                var botaoEditar = botaoContainer.querySelector('.botao-editar');
                var botaoExcluir = botaoContainer.querySelector('.botao-excluir');

            });
        }
    }

    public listarCandidatosAnonimos(): void {
        this.listarCandidatos(true);
    }

    public listarCandidatosPublicos(): void {
        this.listarCandidatos(false);
    }

    public exibirCandidato(idCandidato: number): void {
        const candidato = this.candidatoService.obterCandidato(idCandidato);

        if (candidato) {
            nomeCandidato.textContent = candidato.nome || 'Nome não disponível';
            cpfCandidato.textContent = candidato.cpf || 'CPF não disponível';
            descricaoCandidato.textContent = candidato.descricao || 'Descrição não disponível';
        }
    }


    public contarCompetencias(): void {
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

    private gerarGraficoDeCompetencias(contagem: { [key: string]: number }): void {
        // Obter o contexto do elemento canvas no DOM
        const ctx = (document.getElementById('competenciasChart') as HTMLCanvasElement).getContext('2d');

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