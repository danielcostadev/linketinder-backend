import { CandidatoController } from './controller/candidato.controller';
import { EmpresaController } from './controller/empresa.controller';
import { VagaController } from './controller/vaga.controller';
import { Formulario } from './service/formulario.service';
import { NavegacaoService } from './service/navegacao.service';

// Centralização de interação com o DOM
var botaoAdicionarEmpresa = document.getElementById("adicionarEmpresa") as HTMLButtonElement;
var botaoAdicionarCandidato = document.getElementById("adicionarCandidato") as HTMLButtonElement;
var botaoAdicionarVaga = document.getElementById("adicionarVaga") as HTMLButtonElement;
var linkAdicionarVaga = document.getElementById("cadastrarVaga") as HTMLAnchorElement;
var caixaFormularioCadastro = document.getElementById("caixa-branca-cadastro") as HTMLElement;
var frmEmpresa = document.getElementById("frmEmpresa") as HTMLFormElement;
var frmCandidato = document.getElementById("frmCandidato") as HTMLFormElement;
var frmVaga = document.getElementById("frmVaga") as HTMLFormElement;
var camposCadastro: { nome: string, mensagem: string }[];


// Controladores de serviço
const formulario = new Formulario();
const empresaController = new EmpresaController();
const candidatoController = new CandidatoController();
const navegacaoService = new NavegacaoService();
const vagaController = new VagaController();


window.onload = () => {

    const idEmpresaAtual = navegacaoService.obterIdDaUrl()

    if (idEmpresaAtual > 0) {
        empresaController.exibirEmpresa(idEmpresaAtual);
        candidatoController.listarCandidatos();
    }
    vagaController.listarVagas();

    formulario.gerarPaises();
    formulario.gerarEstados();

};


if (botaoAdicionarEmpresa) {
    botaoAdicionarEmpresa.onclick = function () {
        if (frmEmpresa) {
            const camposCadastroEmpresa = [
                { nome: "nome", mensagem: "O campo Nome está vazio." },
                { nome: "email", mensagem: "O campo E-mail está vazio." },
                { nome: "cnpj", mensagem: "O campo CNPJ está vazio." },
                { nome: "pais", mensagem: "O campo País está vazio." },
                { nome: "estado", mensagem: "O campo Estado está vazio." },
                { nome: "cep", mensagem: "O campo CEP está vazio." },
                { nome: "descricao", mensagem: "O campo Descrição está vazio." }
            ];

            camposCadastro = camposCadastroEmpresa

            if (formulario.validarEntradaDados(camposCadastro, frmEmpresa)) {
                console.log(frmEmpresa)
                const formData: FormData = new FormData(frmEmpresa);
                empresaController.adicionarEmpresa(formData);
            };
        }
    }
}

if (botaoAdicionarCandidato) {
    botaoAdicionarCandidato.onclick = function () {
        if (frmCandidato) {
            const camposCadastroCandidato = [
                { nome: "nome", mensagem: "O campo Nome está vazio." },
                { nome: "email", mensagem: "O campo E-mail está vazio." },
                { nome: "cpf", mensagem: "O campo CPF está vazio." },
                { nome: "idade", mensagem: "O campo Idade está vazio." },
                { nome: "estado", mensagem: "O campo Estado está vazio." },
                { nome: "cep", mensagem: "O campo CEP está vazio." },
                { nome: "descricao", mensagem: "O campo Descrição está vazio." },
                { nome: "competencias", mensagem: "O campo Competencias está vazio." }
            ];

            camposCadastro = camposCadastroCandidato

            if (formulario.validarEntradaDados(camposCadastro, frmCandidato)) {
                console.log(frmCandidato)
                const formData: FormData = new FormData(frmCandidato);
                candidatoController.adicionarCandidato(formData);
            };

        }
    }

}

if (botaoAdicionarVaga) {
    botaoAdicionarVaga.onclick = function () {
        if (frmVaga) {
            const camposCadastroVaga = [
                { nome: "nome", mensagem: "O campo Nome está vazio." },
                { nome: "descricao", mensagem: "O campo Descrição está vazio." },
                { nome: "competencias", mensagem: "O campo Competencias está vazio." }
            ];

            camposCadastro = camposCadastroVaga

            if (formulario.validarEntradaDados(camposCadastro, frmVaga)) {
                console.log(frmVaga)
                const formData: FormData = new FormData(frmVaga);
                vagaController.adicionarVaga(formData);
            };

        }
    }

}

if (linkAdicionarVaga) {
    linkAdicionarVaga.onclick = function () {
        caixaFormularioCadastro.style.display = "block";
    }
}