import { DadosDAO } from "../model/dadosDAO";
import { Empresa } from "../model/empresa";
import { EmpresaService } from "../service/empresa.service";

export class EmpresaController {

    private empresaService = new EmpresaService();
    private dadosDAO = new DadosDAO();


    public adicionarEmpresa(dadosFormulario: FormData): void {

        const competencias = (dadosFormulario.get("competencias") as string)
        .split(',')
        .map(competencia => competencia.trim());

        const empresa = new Empresa(
            dadosFormulario.get("nome") as string,
            dadosFormulario.get("email") as string,
            dadosFormulario.get("cnpj") as string,
            dadosFormulario.get("pais") as string,
            dadosFormulario.get("estado") as string,
            dadosFormulario.get("cep") as string,
            dadosFormulario.get("descricao") as string,
            competencias,
        );
        this.empresaService.adicionarEmpresa(empresa, this.dadosDAO);
        console.log("Empresa adicionada com sucesso!");
        
    }
}