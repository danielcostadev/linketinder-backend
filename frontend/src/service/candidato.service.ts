import { DadosDAO } from "../model/dados.DAO";
import { Candidato } from "../model/candidato";

export class CandidatoService {
    private dadosDAO: DadosDAO;

    constructor() {
        this.dadosDAO = new DadosDAO();
    }

    public gerarId(): number {
        let candidatos = this.dadosDAO.obterCandidatoDoLocalStorage("dataCandidato");
        console.log(candidatos);
        if (candidatos.length > 0) {
            const ids: number[] = candidatos.map((candidato: Candidato) => candidato.id);
            const maiorId: number = Math.max(...ids);
            return maiorId + 1;
        } else {
            return 1;
        }
    }

    public adicionarCandidato(candidato: Candidato): void {
        let candidatos: Candidato[] = this.dadosDAO.obterCandidatoDoLocalStorage("dataCandidato");
        candidatos.push(candidato);
        this.dadosDAO.salvarCandidatoNoLocalStorage("dataCandidato", candidatos)
        console.log("Candidato adicionado com sucesso!");
    }

    public exlcuirCandidato(idCandidato: number): void {
        let candidatos: Candidato[] = this.dadosDAO.obterCandidatoDoLocalStorage("dataCandidato");
        const index: number = candidatos.findIndex(candidato => candidato.id === idCandidato);

        if (index !== -1) {
            candidatos.splice(index, 1);
            this.dadosDAO.salvarCandidatoNoLocalStorage("dataCandidato", candidatos)
            console.log("Candidato removido com sucesso!");

        } else {
            console.log("Candidato não encontrado na lista!");
        }
    }

    public listarCandidatos(): Candidato[] {
        const candidatos: Candidato[] = this.dadosDAO.obterCandidatoDoLocalStorage("dataCandidato");
        return candidatos || [];

    }

}