import { Empresa } from "../model/empresa";
import { BotoesService } from "../service/botoes.service";
import { EmpresaService } from "../service/empresa.service";
// Centralização de interação com o DOM
const tabelaItensEmpresa = document.getElementById("tabela-itens-empresa");
const nomeEmpresa = document.getElementById("nomeEmpresa");
const cnpjEmpresa = document.getElementById("cnpjEmpresa");
const descricaoEmpresa = document.getElementById("descricaoEmpresa");
export class EmpresaController {
    constructor() {
        this.empresaService = new EmpresaService();
        this.botoesService = new BotoesService();
    }
    adicionarEmpresa(dadosFormulario) {
        const id = this.empresaService.gerarId();
        const empresa = new Empresa(id, dadosFormulario.get("nome"), dadosFormulario.get("email"), dadosFormulario.get("cnpj"), dadosFormulario.get("pais"), dadosFormulario.get("estado"), dadosFormulario.get("cep"), dadosFormulario.get("descricao"), dadosFormulario.get("competencias").split(","), dadosFormulario.get("tags").split(","));
        this.empresaService.adicionarEmpresa(empresa);
    }
    excluirEmpresa(id) {
        this.empresaService.excluirEmpresa(id);
    }
    exibirEmpresa(idEmpresa) {
        const empresa = this.empresaService.obterEmpresa(idEmpresa);
        if (empresa) {
            nomeEmpresa.textContent = empresa.nome || 'Nome não disponível';
            cnpjEmpresa.textContent = empresa.cnpj || 'CNPJ não disponível';
            descricaoEmpresa.textContent = empresa.descricao || 'Descrição não disponível';
        }
    }
    // Listagem disponível apenas para o acesso via págind do super-user
    listarEmpresas() {
        const empresas = this.empresaService.listarEmpresa();
        if (empresas.length >= 0) {
            tabelaItensEmpresa.innerHTML = "";
            empresas.forEach((empresa, index) => {
                var botaoContainer = this.botoesService.criarBotaoContainerReadUpdateDelete();
                var linha = tabelaItensEmpresa.insertRow();
                var cellId = linha.insertCell(0);
                var cellNome = linha.insertCell(1);
                var cellEmail = linha.insertCell(2);
                var cellOpcoes = linha.insertCell(3);
                cellId.innerHTML = empresa.id.toString();
                cellNome.innerHTML = empresa.nome;
                cellEmail.innerHTML = empresa.email;
                cellOpcoes.appendChild(botaoContainer);
                var botaoVer = botaoContainer.querySelector('.botao-ver');
                var botaoEditar = botaoContainer.querySelector('.botao-editar');
                var botaoExcluir = botaoContainer.querySelector('.botao-excluir');
                botaoVer.onclick = (() => {
                    window.location.href = "perfil-empresa.html?id=" + empresa.id;
                });
                botaoExcluir.onclick = (() => {
                    let confirmacao = confirm(`Tem certeza que deseja excluir o candidato com id: ${empresa.id} ?`);
                    if (confirmacao) {
                        this.excluirEmpresa(empresa.id);
                        this.listarEmpresas();
                    }
                });
            });
        }
    }
}
