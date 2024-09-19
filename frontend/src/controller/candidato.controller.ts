import { Candidato } from "../model/candidato";
import { CandidatoService } from "../service/candidato.service";

// Centralização de interação com o DOM
var tabelaItens = document.getElementById("tabela-itens") as HTMLTableSectionElement;

export class CandidatoController {

    private candidatoService = new CandidatoService();

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

        if(candidatos.length > 0) {
            tabelaItens.innerHTML = "";

            candidatos.forEach((candidato, index) => {
                var linha = tabelaItens.insertRow() as HTMLTableRowElement;
                var cellCompetencias = linha.insertCell(0) as HTMLTableCellElement;
                var cellFormacao = linha.insertCell(1) as HTMLTableCellElement;

                cellCompetencias.innerHTML = Array.isArray(candidato.competencias) ? candidato.competencias.join(', ') : candidato.competencias;
                cellFormacao.innerHTML = candidato.formacao;
            });
        }
    }




}