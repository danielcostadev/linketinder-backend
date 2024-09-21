import { DadosDAO } from "../model/dados.DAO";
export class CandidatoService {
    constructor() {
        this.dadosDAO = new DadosDAO();
    }
    gerarId() {
        let candidatos = this.dadosDAO.obterCandidatoDoLocalStorage("dataCandidato");
        console.log(candidatos);
        if (candidatos.length > 0) {
            const ids = candidatos.map((candidato) => candidato.id);
            const maiorId = Math.max(...ids);
            return maiorId + 1;
        }
        else {
            return 1;
        }
    }
    adicionarCandidato(candidato) {
        let candidatos = this.dadosDAO.obterCandidatoDoLocalStorage("dataCandidato");
        candidatos.push(candidato);
        this.dadosDAO.salvarCandidatoNoLocalStorage("dataCandidato", candidatos);
        console.log("Candidato adicionado com sucesso!");
    }
    exlcuirCandidato(idCandidato) {
        let candidatos = this.dadosDAO.obterCandidatoDoLocalStorage("dataCandidato");
        const index = candidatos.findIndex(candidato => candidato.id === idCandidato);
        if (index !== -1) {
            candidatos.splice(index, 1);
            this.dadosDAO.salvarCandidatoNoLocalStorage("dataCandidato", candidatos);
            console.log("Candidato removido com sucesso!");
        }
        else {
            console.log("Candidato não encontrado na lista!");
        }
    }
    obterCandidato(idCandidato) {
        let candidatos = this.dadosDAO.obterCandidatoDoLocalStorage("dataCandidato");
        const candidato = candidatos.find(cand => cand.id === idCandidato);
        if (candidato) {
            return candidato;
        }
        else {
            console.log("Candidato não encontrado na lista!");
            return undefined;
        }
    }
    listarCandidatos() {
        const candidatos = this.dadosDAO.obterCandidatoDoLocalStorage("dataCandidato");
        return candidatos || [];
    }
    contarCompetencias(candidatos) {
        const contagem = {};
        candidatos.forEach(candidato => {
            candidato.competencias.forEach(competencia => {
                if (contagem[competencia]) {
                    contagem[competencia]++;
                }
                else {
                    contagem[competencia] = 1;
                }
            });
        });
        return contagem;
    }
}
