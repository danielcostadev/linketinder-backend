import { DadosDAO } from "../model/dados.DAO";
import { Empresa } from "../model/empresa";
import { Vaga } from "../model/vaga";
import { VagaService } from "./vaga.service";

export class EmpresaService {

    private dadosDAO: DadosDAO;
    private vagasService = new VagaService();

    constructor() {
        this.dadosDAO = new DadosDAO();
    }

    public gerarId(): number {
        let empresas = this.dadosDAO.obterEmpresaDoLocalStorage("dataEmpresa");
        console.log(empresas);
        if (empresas.length > 0) {
            const ids: number[] = empresas.map((empresa: Empresa) => empresa.id);
            const maiorId: number = Math.max(...ids);
            return maiorId + 1;
        } else {
            return 1;
        }
    }

    adicionarEmpresa(empresa: Empresa): void {
        let empresas: Empresa[] = this.dadosDAO.obterEmpresaDoLocalStorage("dataEmpresa");
        empresas.push(empresa);
        this.dadosDAO.salvarEmpresaNoLocalStorage("dataEmpresa", empresas);
        alert("Empresa adicionada com sucesso!");
    }

    excluirEmpresa(idEmpresa: number): void {
        let empresas: Empresa[] = this.dadosDAO.obterEmpresaDoLocalStorage("dataEmpresa");
        const index: number = empresas.findIndex(empresa => empresa.id === idEmpresa);

        if (index !== -1) { 

            this.vagasService.excluirVagasPorEmpresa(idEmpresa);

            empresas.splice(index, 1);
            this.dadosDAO.salvarEmpresaNoLocalStorage("dataEmpresa", empresas);
            alert("Empresa removida com sucesso!");

        } else {
            alert("Empresa não encontrado na lista!");
        }
    }

    obterEmpresa(idEmpresa: number): Empresa | undefined {
        let empresas: Empresa[] = this.dadosDAO.obterEmpresaDoLocalStorage("dataEmpresa");
        const empresa = empresas.find(emp => emp.id === idEmpresa);

        if (empresa) {
            return empresa;
        } else {
            console.log("Empresa não encontrada na lista!");
            return undefined;
        }
    }

    public listarEmpresa(): Empresa[] {
        const empresas: Empresa[] = this.dadosDAO.obterEmpresaDoLocalStorage("dataEmpresa");
        return empresas || [];
    }

    adicionarVagaEmpresa(idEmpresa: number, vaga: Vaga): void {
        let empresas: Empresa[] = this.dadosDAO.obterEmpresaDoLocalStorage("dataEmpresa");
        const empresa = empresas.find(emp => emp.id === idEmpresa);

        if (empresa) {

            if (!empresa.vagas) {
                empresa.vagas = [];
            }

            const vagaTratada = this.dadosDAO.removerUnderscores(vaga);

            empresa.vagas.push(vagaTratada);
            
            this.dadosDAO.salvarEmpresaNoLocalStorage("dataEmpresa", empresas);
            console.log("Vaga adicionada à empresa ");
        } else {
            console.log("Empresa não encontrada!");
        }
    }

    excluirVagaEmpresa(idEmpresa: number,codigoVaga: number): void {
        let empresas: Empresa[] = this.dadosDAO.obterEmpresaDoLocalStorage("dataEmpresa");
        const empresa = empresas.find(emp => emp.id === idEmpresa);

        if (empresa) {

            if (empresa.vagas) {
                const index = empresa.vagas.findIndex(vaga => vaga.codigo === codigoVaga)
            
                if(index !== -1){
                    empresa.vagas.splice(index,1);
                    this.dadosDAO.salvarEmpresaNoLocalStorage("dataEmpresa", empresas)
                    console.log("Vaga removida com sucesso!");
                } else {
                    console.log("Vaga não encontrada na lista de vagas da empresa!");
                }
            
            } else {
                console.log("A empresa não tem vagas associadas.");

            }
            
            this.dadosDAO.salvarEmpresaNoLocalStorage("dataEmpresa", empresas);
            console.log("Vaga adicionada à empresa ");
        } else {
            console.log("Empresa não encontrada!");
        }
    }
}