import { Empresa } from "../model/empresa";
import { EmpresaService } from "../service/empresa.service";

// Centralização de interação com o DOM
var nomeEmpresa = document.getElementById("nomeEmpresa") as HTMLElement;
var cnpjEmpresa = document.getElementById("cnpjEmpresa") as HTMLElement;
var descricaoEmpresa = document.getElementById("descricaoEmpresa") as HTMLElement;

export class EmpresaController {

    private empresaService = new EmpresaService();

    public adicionarEmpresa(dadosFormulario: FormData): void {

        const id = this.empresaService.gerarId();

        const empresa = new Empresa(
            id,
            dadosFormulario.get("nome") as string,
            dadosFormulario.get("email") as string,
            dadosFormulario.get("cnpj") as string,
            dadosFormulario.get("pais") as string,
            dadosFormulario.get("estado") as string,
            dadosFormulario.get("cep") as string,
            dadosFormulario.get("descricao") as string,
            (dadosFormulario.get("competencias") as string).split(","),
            
        );
        this.empresaService.adicionarEmpresa(empresa);

    }

    public excluirEmpresa(id: number): void {
        this.empresaService.exlcuirEmpresa(id);
    }

    public exibirEmpresa(idEmpresa: number): void {
        const empresa = this.empresaService.obterEmpresa(idEmpresa);

        if(empresa){
        nomeEmpresa.textContent = empresa.nome || 'Nome não disponível';
        cnpjEmpresa.textContent = empresa.cnpj || 'CNPJ não disponível';
        descricaoEmpresa.textContent = empresa.descricao || 'Descrição não disponível';
        }
    }

}

