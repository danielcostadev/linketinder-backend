import { Formulario } from './service/formulario.service';

// Centralização de interação com o DOM
var botaoAdicionar = document.getElementById("adicionar") as HTMLButtonElement;
var frmEmpresa = document.getElementById("frmEmpresa") as HTMLFormElement;
var frmCandidato = document.getElementById("frmCandidato") as HTMLFormElement;
var frmCadastro: HTMLFormElement;
var camposCadastro: { nome: string, mensagem: string }[];



// Interagindo com formulário
const formulario = new Formulario();

window.onload = () => {
    formulario.gerarListaPaisesEstados();
};

botaoAdicionar.onclick = function () {

    if (frmEmpresa) {

        // Validação de dados
        const camposCadastroEmpresa = [
            { nome: "nome", mensagem: "O campo Nome está vazio." },
            { nome: "email", mensagem: "O campo E-mail está vazio." },
            { nome: "cnpj", mensagem: "O campo CNPJ está vazio." },
            { nome: "pais", mensagem: "O campo País está vazio." },
            { nome: "estado", mensagem: "O campo Estado está vazio." },
            { nome: "cep", mensagem: "O campo CEP está vazio." },
            { nome: "descricao", mensagem: "O campo Descrição está vazio." }
        ];

        frmCadastro = frmEmpresa;
        camposCadastro = camposCadastroEmpresa
        

    } else {

        const camposCadastroCandidato = [
            { nome: "nome", mensagem: "O campo Nome está vazio." },
            { nome: "email", mensagem: "O campo E-mail está vazio." },
            { nome: "cpf", mensagem: "O campo CPF está vazio." },
            { nome: "idade", mensagem: "O campo Idade está vazio." },
            { nome: "estado", mensagem: "O campo Estado está vazio." },
            { nome: "cep", mensagem: "O campo CEP está vazio." },
            { nome: "descricao", mensagem: "O campo Descrição está vazio." }
        ];

        frmCadastro = frmCandidato;
        camposCadastro = camposCadastroCandidato

    }

    if (formulario.validarEntradaDados(camposCadastro, frmCadastro)) {
        alert("Estou Ok!")
    };

};