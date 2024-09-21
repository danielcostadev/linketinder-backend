
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

        if (candidatos.length >= 0) {
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
                    // Listagem disponível apenas para acesso via página super-user
                    var botaoContainer = this.botoesService.criarBotaoContainerReadUpdateDelete() as HTMLDivElement;

                    var cellId = linha.insertCell(0) as HTMLTableCellElement;
                    var cellNome = linha.insertCell(1) as HTMLTableCellElement;
                    var cellEmail = linha.insertCell(2) as HTMLTableCellElement;
                    var cellOpcoes = linha.insertCell(3) as HTMLTableCellElement;

                    cellId.innerHTML = candidato.id.toString();
                    cellNome.innerHTML = candidato.nome;
                    cellEmail.innerHTML = candidato.email
                    cellOpcoes.appendChild(botaoContainer);

                    // Para que os botões funcionem, devemos atribuir eventos logo após serem adicionados
                    var botaoVer = botaoContainer.querySelector('.botao-ver') as HTMLButtonElement;
                    var botaoEditar = botaoContainer.querySelector('.botao-editar') as HTMLButtonElement
                    var botaoExcluir = botaoContainer.querySelector('.botao-excluir') as HTMLButtonElement;

                    botaoVer.onclick = (() => {
                        window.location.href = "perfil-candidato.html?id=" + candidato.id;

                    });

                    botaoExcluir.onclick = (() => {
                        let confirmacao: boolean = confirm(`Tem certeza que deseja excluir o candidato com id: ${candidato.id} ?`);
                        if (confirmacao) {
                            this.excluirCandidato(candidato.id);
                            this.listarCandidatosPublicos();
                        }

                    });
                }
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
                    barPercentage: 0.9,
                    data: valores, // Quantidades no eixo Y
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(255, 159, 64, 0.2)',
                        'rgba(255, 205, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(201, 203, 207, 0.2)'
                    ],
                    borderColor: [
                        'rgb(255, 99, 132)',
                        'rgb(255, 159, 64)',
                        'rgb(255, 205, 86)',
                        'rgb(75, 192, 192)',
                        'rgb(54, 162, 235)',
                        'rgb(153, 102, 255)',
                        'rgb(201, 203, 207)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                indexAxis: 'y',

                scales: {
                    y: {

                        beginAtZero: true,

                    },
                    x: {
                        ticks: {
                            stepSize: 1,
                        }
                    },
                }
            }
        });
    }


}