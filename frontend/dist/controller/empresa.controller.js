import { Empresa } from "../model/empresa";
import { EmpresaService } from "../service/empresa.service";
// Centralização de interação com o DOM
var nomeEmpresa = document.getElementById("nomeEmpresa");
var cnpjEmpresa = document.getElementById("cnpjEmpresa");
var descricaoEmpresa = document.getElementById("descricaoEmpresa");
export class EmpresaController {
    constructor() {
        this.empresaService = new EmpresaService();
    }
    adicionarEmpresa(dadosFormulario) {
        const id = this.empresaService.gerarId();
        const empresa = new Empresa(id, dadosFormulario.get("nome"), dadosFormulario.get("email"), dadosFormulario.get("cnpj"), dadosFormulario.get("pais"), dadosFormulario.get("estado"), dadosFormulario.get("cep"), dadosFormulario.get("descricao"), dadosFormulario.get("competencias").split(","));
        this.empresaService.adicionarEmpresa(empresa);
    }
    excluirEmpresa(id) {
        this.empresaService.exlcuirEmpresa(id);
    }
    exibirEmpresa(idEmpresa) {
        const empresa = this.empresaService.obterEmpresa(idEmpresa);
        if (empresa) {
            nomeEmpresa.textContent = empresa.nome || 'Nome não disponível';
            cnpjEmpresa.textContent = empresa.cnpj || 'CNPJ não disponível';
            descricaoEmpresa.textContent = empresa.descricao || 'Descrição não disponível';
        }
    }
}
