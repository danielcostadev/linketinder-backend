import { Vaga } from "../model/vaga";
import { VagaService } from "../service/vaga.service";
import { EmpresaService } from "../service/empresa.service";
import { NavegacaoService } from "../service/navegacao.service";
import { BotoesService } from "../service/botoes.service";

// Centralização de interação com o DOM
var tabelaItensVaga = document.getElementById("tabela-itens-vaga") as HTMLTableSectionElement;

export class VagaController {
    private vagaService = new VagaService();
    private empresaService = new EmpresaService();
    private navegacaoService = new NavegacaoService();
    private botoesService = new BotoesService();


    public adicionarVaga(dadosFormulario: FormData): void {

        const codigo = this.vagaService.gerarCodigo();

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
    public excluirVaga(id: number): void {
        this.vagaService.excluirVaga(id);
    }

    public listarVagas(): void {
        const vagas: Vaga[] = this.vagaService.listarVagas();

        if(vagas.length > 0) {
            tabelaItensVaga.innerHTML = "";

            vagas.forEach((vaga, index) => {
                var botaoContainer = this.botoesService.criarBotaoContainerLike() as HTMLDivElement; 

                var linha = tabelaItensVaga.insertRow() as HTMLTableRowElement;
                var cellNome = linha.insertCell(0) as HTMLTableCellElement;
                var cellCompetencias = linha.insertCell(1) as HTMLTableCellElement;
                var cellOpcoes = linha.insertCell(2) as HTMLTableCellElement;

                cellNome.innerHTML = vaga.nome
                cellCompetencias.innerHTML = Array.isArray(vaga.competencias) ? vaga.competencias.join(', ') : vaga.competencias;
                cellOpcoes.appendChild(botaoContainer);

                // Para que os botões funcionem, devemos atribuir eventos logo após serem adicionados
                var botaoCurtir = botaoContainer.querySelector('.botao-curtir');
                var botaoRejeitar = botaoContainer.querySelector('.botao-rejeitar');

            });
        }
    }
}