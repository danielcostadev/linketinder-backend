import { Candidato } from "../model/candidato";
import { BotoesService } from "../service/botoes.service";
import { CandidatoService } from "../service/candidato.service";

// Centralização de interação com o DOM
var tabelaItensCandidato = document.getElementById("tabela-itens-candidato") as HTMLTableSectionElement;

// Centralização de interação com o DOM
var nomeCandidato = document.getElementById("nomeCandidato") as HTMLElement;
var cpfCandidato = document.getElementById("cpfCandidato") as HTMLElement;
var descricaoCandidato = document.getElementById("descricaoCandidato") as HTMLElement;

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

    public listarCandidatos(): void {
        const candidatos: Candidato[] = this.candidatoService.listarCandidatos();

        if (candidatos.length > 0) {
            tabelaItensCandidato.innerHTML = "";

            candidatos.forEach((candidato, index) => {
                var botaoContainer = this.botoesService.criarBotaoContainer() as HTMLDivElement; 
                var linha = tabelaItensCandidato.insertRow() as HTMLTableRowElement;

                var cellCompetencias = linha.insertCell(0) as HTMLTableCellElement;
                var cellFormacao = linha.insertCell(1) as HTMLTableCellElement;
                var cellOpcoes = linha.insertCell(2) as HTMLTableCellElement;

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



    public exibirCandidato(idCandidato: number): void {
        const candidato = this.candidatoService.obterCandidato(idCandidato);

        if (candidato) {
            nomeCandidato.textContent = candidato.nome || 'Nome não disponível';
            cpfCandidato.textContent = candidato.cpf || 'CPF não disponível';
            descricaoCandidato.textContent = candidato.descricao || 'Descrição não disponível';
        }
    }




}