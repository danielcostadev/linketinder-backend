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
        this.dadosDAO.salvarCandidatoNoLocalStorage("dataCandidato", candidatos);
        alert("Candidato adicionado com sucesso!");
    }

    public exlcuirCandidato(idCandidato: number): void {
        let candidatos: Candidato[] = this.dadosDAO.obterCandidatoDoLocalStorage("dataCandidato");
        const index: number = candidatos.findIndex(candidato => candidato.id === idCandidato);

        if (index !== -1) {
            candidatos.splice(index, 1);
            this.dadosDAO.salvarCandidatoNoLocalStorage("dataCandidato", candidatos);
            alert("Candidato removido com sucesso!")

        } else {
            alert("Candidato não encontrado na lista!")
        }
    }

    public obterCandidato(idCandidato: number): Candidato | undefined {
        let candidatos: Candidato[] = this.dadosDAO.obterCandidatoDoLocalStorage("dataCandidato");
        const candidato = candidatos.find(cand => cand.id === idCandidato);

        if (candidato){
            return candidato;
        } else {
            console.log("Candidato não encontrado na lista!");
            return undefined;
        }
    }


    public listarCandidatos(): Candidato[] {
        const candidatos: Candidato[] = this.dadosDAO.obterCandidatoDoLocalStorage("dataCandidato");
        return candidatos || [];

    }

    public contarCompetencias(candidatos: Candidato[]): Record<string, number> {
        const contagem: Record<string, number> = {};
    
        candidatos.forEach(candidato => {
            candidato.competencias.forEach(competencia => {
                const competenciasLower = competencia.toLowerCase().trim();
                if (contagem[competenciasLower]) {
                    contagem[competenciasLower]++;
                } else {
                    contagem[competenciasLower] = 1;
                }
            });
        });
    
        return contagem;
    }

}