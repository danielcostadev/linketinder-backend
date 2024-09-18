import { DadosDAO } from "../model/dados.DAO";
import { Empresa } from "../model/empresa";
import { Vaga } from "../model/vaga";

export class VagaService {
    private dadosDAO: DadosDAO;

    constructor() {
        this.dadosDAO = new DadosDAO();
    }

    public gerarCodigo(): number{
        let vagas = this.dadosDAO.obterVagaDoLocalStorage("dataVaga");
        console.log(vagas);
        if(vagas.length > 0) {
            const ids: number[] = vagas.map((vaga: Vaga) => vaga.codigo);
            const maiorId: number = Math.max(...ids);
            return maiorId + 1;
        } else {
            return 1;
        }
    }

    public adicionarVaga(vaga: Vaga): void {
        let vagas: Vaga[] = this.dadosDAO.obterVagaDoLocalStorage("dataVaga");
        vagas.push(vaga);
        this.dadosDAO.salvarVagaNoLocalStorage("dataVaga", vagas);
        console.log("Vaga adicionada com sucesso!");
    }

    public excluirVaga(idVaga: number): void {
        let vagas: Vaga[] = this.dadosDAO.obterVagaDoLocalStorage("dataVaga");
        const index: number = vagas.findIndex(vaga => vaga.codigo === idVaga);

        if (index !== -1) {
            vagas.splice(index, 1);
            this.dadosDAO.salvarVagaNoLocalStorage("dataVaga", vagas);
            console.log("Vaga removida com sucesso!")

        } else {
            console.log("Vaga não encontrado na lista!");
        }
    }


}