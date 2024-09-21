import { DadosDAO } from "../model/dados.DAO";
export class VagaService {
    constructor() {
        this.dadosDAO = new DadosDAO();
    }
    gerarCodigo() {
        let vagas = this.dadosDAO.obterVagaDoLocalStorage("dataVaga");
        console.log(vagas);
        if (vagas.length > 0) {
            const ids = vagas.map((vaga) => vaga.codigo);
            const maiorId = Math.max(...ids);
            return maiorId + 1;
        }
        else {
            return 1;
        }
    }
    adicionarVaga(vaga) {
        let vagas = this.dadosDAO.obterVagaDoLocalStorage("dataVaga");
        vagas.push(vaga);
        this.dadosDAO.salvarVagaNoLocalStorage("dataVaga", vagas);
        console.log("Vaga adicionada com sucesso!");
    }
    excluirVaga(idVaga) {
        let vagas = this.dadosDAO.obterVagaDoLocalStorage("dataVaga");
        const index = vagas.findIndex(vaga => vaga.codigo === idVaga);
        if (index !== -1) {
            vagas.splice(index, 1);
            this.dadosDAO.salvarVagaNoLocalStorage("dataVaga", vagas);
            console.log("Vaga removida com sucesso!");
        }
        else {
            console.log("Vaga não encontrado na lista!");
        }
    }
    listarVagas() {
        const vagas = this.dadosDAO.obterVagaDoLocalStorage("dataVaga");
        return vagas || [];
    }
}
