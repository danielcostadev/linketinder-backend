import { Empresa } from "../model/empresa";
import { BotoesService } from "../service/botoes.service";
import { EmpresaService } from "../service/empresa.service";

// Centralização de interação com o DOM
const tabelaItensEmpresa = document.getElementById("tabela-itens-empresa") as HTMLTableSectionElement;
const nomeEmpresa = document.getElementById("nomeEmpresa") as HTMLElement;
const cnpjEmpresa = document.getElementById("cnpjEmpresa") as HTMLElement;
const descricaoEmpresa = document.getElementById("descricaoEmpresa") as HTMLElement;

export class EmpresaController {

    private empresaService = new EmpresaService();
    private botoesService = new BotoesService();

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
            (dadosFormulario.get("tags") as string).split(",")

        );
        this.empresaService.adicionarEmpresa(empresa);

    }

    public excluirEmpresa(id: number): void {
        this.empresaService.excluirEmpresa(id);
    }

    public exibirEmpresa(idEmpresa: number): void {
        const empresa = this.empresaService.obterEmpresa(idEmpresa);

        if (empresa) {
            nomeEmpresa.textContent = empresa.nome || 'Nome não disponível';
            cnpjEmpresa.textContent = empresa.cnpj || 'CNPJ não disponível';
            descricaoEmpresa.textContent = empresa.descricao || 'Descrição não disponível';
        }
    }

    // Listagem disponível apenas para o acesso via págind do super-user
    public listarEmpresas(): void {
        const empresas: Empresa[] = this.empresaService.listarEmpresa();

        if (empresas.length >= 0) {
            tabelaItensEmpresa.innerHTML = "";

            empresas.forEach((empresa, index) => {
                var botaoContainer = this.botoesService.criarBotaoContainerReadUpdateDelete() as HTMLDivElement;
                var linha = tabelaItensEmpresa.insertRow() as HTMLTableRowElement;

                var cellId = linha.insertCell(0) as HTMLTableCellElement;
                var cellNome = linha.insertCell(1) as HTMLTableCellElement;
                var cellEmail = linha.insertCell(2) as HTMLTableCellElement;
                var cellOpcoes = linha.insertCell(3) as HTMLTableCellElement;

                cellId.innerHTML = empresa.id.toString();
                cellNome.innerHTML = empresa.nome;
                cellEmail.innerHTML = empresa.email;
                cellOpcoes.appendChild(botaoContainer);

                var botaoVer = botaoContainer.querySelector('.botao-ver') as HTMLButtonElement;
                var botaoEditar = botaoContainer.querySelector('.botao-editar') as HTMLButtonElement
                var botaoExcluir = botaoContainer.querySelector('.botao-excluir') as HTMLButtonElement;


                botaoVer.onclick = (() => {
                    window.location.href = "perfil-empresa.html?id=" + empresa.id;

                });

                botaoExcluir.onclick = (() => {
                    let confirmacao: boolean = confirm(`Tem certeza que deseja excluir o candidato com id: ${empresa.id} ?`);
                    if (confirmacao) {
                        this.excluirEmpresa(empresa.id);
                        this.listarEmpresas();
                    }

                });

            });

        }
    }

}

