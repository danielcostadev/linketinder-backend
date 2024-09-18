import { EmpresaService } from "../service/empresa.service";

export class NavegacaoService {

public obterIdDaUrl(): number {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get("id");
    
    if (id){
       return parseInt(id, 10)
    }

    return 1

}

public carregarEmpresa() {
    const idEmpresaAtual = this.obterIdDaUrl();

    if (idEmpresaAtual !== null) {
        const empresaService = new EmpresaService();

        const empresa = empresaService.obterEmpresa(idEmpresaAtual);

        if (empresa) {
            console.log("Empresa encontrada:");
           
        } else {
            console.log("Empresa não encontrada!");
        }
    } else {
        console.log("ID da empresa não encontrado na URL.");
    }
}


}



