import { DadosDAO } from "../model/dadosDAO";
import { Empresa } from "../model/empresa";

export class EmpresaService {

    private empresas: Empresa[] = [];

    public adicionarEmpresa(empresa:Empresa, dados: DadosDAO):void{
        this.empresas.push(empresa);
        dados.salvarNoLocalStorage("linketinderData",JSON.stringify(this.empresas))

    }

}