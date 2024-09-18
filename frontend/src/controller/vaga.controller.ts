import { Vaga } from "../model/vaga";
import { VagaService } from "../service/vaga.service";
import { EmpresaService } from "../service/empresa.service";
import { NavegacaoService } from "../service/navegacao.service";


export class VagaController {
    private vagaService = new VagaService();
    private empresaService = new EmpresaService();
    private navegacaoService = new NavegacaoService();

    public adicionarVaga(dadosFormulario: FormData): void {

        const codigo = this.vagaService.gerarCodigo();
        const idEmpresa = this.empresaService.obterEmpresa

        const vaga = new Vaga(
            codigo,
            dadosFormulario.get("nome") as string,
            dadosFormulario.get("descricao") as string,
            (dadosFormulario.get("competencias") as string).split(",")
        );

       const idEmpresaAtual = this.navegacaoService.obterIdDaUrl();

        this.empresaService.adicionarVagaEmpresa(idEmpresaAtual, vaga)
        this.vagaService.adicionarVaga(vaga);

    }
}