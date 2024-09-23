import { CandidatoController } from './controller/candidato.controller';
import { EmpresaController } from './controller/empresa.controller';
import { VagaController } from './controller/vaga.controller';
import { CandidatoService } from './service/candidato.service';
import { EmpresaService } from './service/empresa.service';
import { NavegacaoService } from './service/navegacao.service';
import { Formulario } from './service/formulario.service';
// Centralização de interação com o DOM
const botaoAdicionarEmpresa = document.getElementById("adicionarEmpresa");
const botaoAdicionarCandidato = document.getElementById("adicionarCandidato");
const botaoAdicionarVaga = document.getElementById("adicionarVaga");
const botaoLimparEmpresa = document.getElementById("limparEmpresa");
const botaoLimparCandidato = document.getElementById("limparCandidato");
const botaoLimparVaga = document.getElementById("limparVaga");
const botaoCancelarEmpresa = document.getElementById("cancelarEmpresa");
const botaoCancelarCandidato = document.getElementById("cancelarCandidato");
const botaoCancelarVaga = document.getElementById("cancelarVaga");
const linkAdicionarVaga = document.getElementById("cadastrarVaga");
const linkVerVagas = document.getElementById("link-vagas-index");
const linkVerCandidatos = document.getElementById("link-candidatos-index");
const caixaFormularioCadastro = document.getElementById("caixa-branca-cadastro");
const frmEmpresa = document.getElementById("frmEmpresa");
const frmCandidato = document.getElementById("frmCandidato");
const frmVaga = document.getElementById("frmVaga");
const empresaElemento = document.querySelector("#empresa");
const candidatoElemento = document.querySelector("#candidato");
const listaEmpresasElemento = document.querySelector("#listaEmpresas");
const listaCandidatosElemento = document.querySelector("#listaCandidatos");
const vagasElemento = document.querySelector("#vagas");
const formularioElemento = document.querySelector("#formulario");
// Controlador para validação dos dados nos formulários
var camposCadastro;
// Controladores e serviços
const candidatoController = new CandidatoController();
const empresaController = new EmpresaController();
const vagaController = new VagaController();
const candidatoService = new CandidatoService();
const empresaService = new EmpresaService();
const navegacaoService = new NavegacaoService();
const formulario = new Formulario();
// Carrega as funcionalidades de acordo com o tipo de página
window.onload = () => {
    const idEmpresaAtual = navegacaoService.obterIdDaUrl();
    const idCandidatoAtual = navegacaoService.obterIdDaUrl();
    if (empresaElemento && idEmpresaAtual > 0) {
        empresaController.exibirEmpresa(idEmpresaAtual);
        candidatoController.listarCandidatosAnonimos();
        candidatoController.contarCompetencias();
        console.log("Teste sou empresa");
    }
    if (candidatoElemento) {
        candidatoController.exibirCandidato(idCandidatoAtual);
        vagaController.listarVagas();
        console.log("Teste sou candidato");
    }
    if (vagasElemento) {
        vagaController.listarVagas();
        console.log("Teste sou vaga");
    }
    if (formularioElemento) {
        formulario.gerarPaises();
        formulario.gerarEstados();
        console.log("Teste sou form");
    }
    if (listaEmpresasElemento) {
        empresaController.listarEmpresas();
        console.log("Teste sou uma lista de empresas");
    }
    if (listaCandidatosElemento) {
        candidatoController.listarCandidatosPublicos();
        console.log("Teste sou uma lista de candidatos");
    }
};
if (botaoAdicionarEmpresa) {
    botaoAdicionarEmpresa.onclick = function () {
        if (frmEmpresa) {
            const camposCadastroEmpresa = [
                { nome: "nome", mensagem: "O campo Nome está vazio.", tipo: "nome" },
                { nome: "email", mensagem: "O campo E-mail está vazio.", tipo: "email" },
                { nome: "cnpj", mensagem: "O campo CNPJ está vazio.", tipo: "cnpj" },
                { nome: "pais", mensagem: "O campo País está vazio." },
                { nome: "estado", mensagem: "O campo Estado está vazio." },
                { nome: "cep", mensagem: "O campo CEP está vazio.", tipo: "cep" },
                { nome: "descricao", mensagem: "O campo Descrição está vazio.", tipo: "descricao" },
                { nome: "tags", mensagem: "O campo tags está vazio.", tipo: "tags" }
            ];
            camposCadastro = camposCadastroEmpresa;
            if (formulario.validarEntradaDados(camposCadastro, frmEmpresa)) {
                console.log(frmEmpresa);
                const formData = new FormData(frmEmpresa);
                empresaController.adicionarEmpresa(formData);
                frmEmpresa.reset;
                window.location.href = "index.html?";
            }
            ;
        }
    };
}
if (botaoAdicionarCandidato) {
    botaoAdicionarCandidato.onclick = function () {
        if (frmCandidato) {
            const camposCadastroCandidato = [
                { nome: "nome", mensagem: "O campo Nome está vazio.", tipo: "nome" },
                { nome: "email", mensagem: "O campo E-mail está vazio.", tipo: "email" },
                { nome: "telefone", mensagem: "O campo Telefone está vazio.", tipo: "telefone" },
                { nome: "linkedin", mensagem: "O campo Linkedin está vazio.", tipo: "linkedin" },
                { nome: "cpf", mensagem: "O campo CPF está vazio.", tipo: "cpf" },
                { nome: "idade", mensagem: "O campo Idade está vazio.", tipo: "idade" },
                { nome: "estado", mensagem: "O campo Estado está vazio.", },
                { nome: "cep", mensagem: "O campo CEP está vazio.", tipo: "cep" },
                { nome: "descricao", mensagem: "O campo Descrição está vazio.", tipo: "descricao" },
                { nome: "competencias", mensagem: "O campo Competencias está vazio.", tipo: "competencias" },
                { nome: "tags", mensagem: "O campo tags está vazio.", tipo: "tags" }
            ];
            camposCadastro = camposCadastroCandidato;
            if (formulario.validarEntradaDados(camposCadastro, frmCandidato)) {
                console.log(frmCandidato);
                const formData = new FormData(frmCandidato);
                candidatoController.adicionarCandidato(formData);
                frmCandidato.reset;
                window.location.href = "index.html?";
            }
            ;
        }
    };
}
if (botaoAdicionarVaga) {
    botaoAdicionarVaga.onclick = function () {
        if (frmVaga) {
            const camposCadastroVaga = [
                { nome: "nome", mensagem: "O campo Nome está vazio.", tipo: "nome" },
                { nome: "descricao", mensagem: "O campo Descrição está vazio.", tipo: "descricao" },
                { nome: "competencias", mensagem: "O campo Competencias está vazio.", tipo: "competencias" }
            ];
            camposCadastro = camposCadastroVaga;
            const id = navegacaoService.obterIdDaUrl();
            if (formulario.validarEntradaDados(camposCadastro, frmVaga)) {
                console.log(frmVaga);
                const formData = new FormData(frmVaga);
                vagaController.adicionarVaga(formData);
                frmVaga.reset;
                window.location.href = "perfil-empresa.html?id=" + id;
            }
            ;
        }
    };
}
function adicionarEventoLimpar(botao, formulario) {
    if (botao && formulario) {
        botao.onclick = function () {
            let confirmacao = confirm("Tem certeza que deseja limpar o formulário?");
            if (confirmacao) {
                formulario.reset();
            }
        };
    }
}
adicionarEventoLimpar(botaoLimparEmpresa, frmEmpresa);
adicionarEventoLimpar(botaoLimparCandidato, frmCandidato);
adicionarEventoLimpar(botaoLimparVaga, frmVaga);
function adicionarEventoCancelar(botao) {
    if (botao) {
        botao.onclick = function () {
            let confirmacao = confirm("Tem certeza que deseja cancelar?");
            if (confirmacao) {
                window.location.href = "index.html?";
            }
        };
    }
}
adicionarEventoCancelar(botaoCancelarEmpresa);
adicionarEventoCancelar(botaoCancelarCandidato);
adicionarEventoCancelar(botaoCancelarVaga);
function checarIdIndex(value) {
    let id = null;
    while (id === null || isNaN(id) || id.toString().length > 4) {
        const input = prompt(`Digite o seu ID de ${value} (até 4 dígitos)`);
        if (input !== null) {
            id = parseInt(input, 10);
            if (isNaN(id)) {
                alert("Por favor, insira um número válido.");
            }
            else if (id.toString().length > 4) {
                alert("O ID deve conter até 4 dígitos.");
            }
        }
        else {
            alert("Você cancelou a operação.");
            return null;
        }
    }
    return id;
}
if (linkAdicionarVaga) {
    linkAdicionarVaga.onclick = function () {
        caixaFormularioCadastro.style.display = "block";
    };
}
// Index
if (linkVerVagas) {
    linkVerVagas.onclick = function () {
        let id = checarIdIndex("Candidato");
        if (id !== null && candidatoService.obterCandidato(id)) {
            window.location.href = "perfil-candidato.html?id=" + id;
        }
        else if (id !== null) {
            alert("Candidato não encontrado!");
        }
    };
}
if (linkVerCandidatos) {
    linkVerCandidatos.onclick = function () {
        let id = checarIdIndex("Empresa");
        if (id !== null && empresaService.obterEmpresa(id)) {
            window.location.href = "perfil-empresa.html?id=" + id;
        }
        else if (id !== null) {
            alert("Empresa não encontrada!");
        }
    };
}
