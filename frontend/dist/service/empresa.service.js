import { DadosDAO } from "../model/dados.DAO";
export class EmpresaService {
    constructor() {
        this.dadosDAO = new DadosDAO();
    }
    gerarId() {
        let empresas = this.dadosDAO.obterEmpresaDoLocalStorage("dataEmpresa");
        console.log(empresas);
        if (empresas.length > 0) {
            const ids = empresas.map((empresa) => empresa.id);
            const maiorId = Math.max(...ids);
            return maiorId + 1;
        }
        else {
            return 1;
        }
    }
    adicionarEmpresa(empresa) {
        let empresas = this.dadosDAO.obterEmpresaDoLocalStorage("dataEmpresa");
        empresas.push(empresa);
        this.dadosDAO.salvarEmpresaNoLocalStorage("dataEmpresa", empresas);
        console.log("Empresa adicionada com sucesso!");
    }
    exlcuirEmpresa(idEmpresa) {
        let empresas = this.dadosDAO.obterEmpresaDoLocalStorage("dataEmpresa");
        const index = empresas.findIndex(empresa => empresa.id === idEmpresa);
        if (index !== -1) {
            empresas.splice(index, 1);
            this.dadosDAO.salvarEmpresaNoLocalStorage("dataEmpresa", empresas);
            console.log("Empresa removida com sucesso!");
        }
        else {
            console.log("Empresa não encontrado na lista!");
        }
    }
    obterEmpresa(idEmpresa) {
        let empresas = this.dadosDAO.obterEmpresaDoLocalStorage("dataEmpresa");
        const empresa = empresas.find(emp => emp.id === idEmpresa);
        if (empresa) {
            return empresa;
        }
        else {
            console.log("Empresa não encontrada na lista!");
            return undefined;
        }
    }
    adicionarVagaEmpresa(idEmpresa, vaga) {
        let empresas = this.dadosDAO.obterEmpresaDoLocalStorage("dataEmpresa");
        const empresa = empresas.find(emp => emp.id === idEmpresa);
        if (empresa) {
            if (!empresa.vagas) {
                empresa.vagas = [];
            }
            const vagaTratada = this.dadosDAO.removerUnderscores(vaga);
            empresa.vagas.push(vagaTratada);
            this.dadosDAO.salvarEmpresaNoLocalStorage("dataEmpresa", empresas);
            console.log("Vaga adicionada à empresa ");
        }
        else {
            console.log("Empresa não encontrada!");
        }
    }
    excluirVagaEmpresa(idEmpresa, codigoVaga) {
        let empresas = this.dadosDAO.obterEmpresaDoLocalStorage("dataEmpresa");
        const empresa = empresas.find(emp => emp.id === idEmpresa);
        if (empresa) {
            if (empresa.vagas) {
                const index = empresa.vagas.findIndex(vaga => vaga.codigo === codigoVaga);
                if (index !== -1) {
                    empresa.vagas.splice(index, 1);
                    this.dadosDAO.salvarEmpresaNoLocalStorage("dataEmpresa", empresas);
                    console.log("Vaga removida com sucesso!");
                }
                else {
                    console.log("Vaga não encontrada na lista de vagas da empresa!");
                }
            }
            else {
                console.log("A empresa não tem vagas associadas.");
            }
            this.dadosDAO.salvarEmpresaNoLocalStorage("dataEmpresa", empresas);
            console.log("Vaga adicionada à empresa ");
        }
        else {
            console.log("Empresa não encontrada!");
        }
    }
}
