import { CandidatoController } from './controller/candidato.controller';
import { EmpresaController } from './controller/empresa.controller';
import { Formulario } from './service/formulario.service';

// Centralização de interação com o DOM
var botaoAdicionarEmpresa = document.getElementById("adicionarEmpresa") as HTMLButtonElement;
var botaoAdicionarCandidato = document.getElementById("adicionarCandidato") as HTMLButtonElement;
var botaoCancelar = document.getElementById("cancelar") as HTMLButtonElement;
var frmEmpresa = document.getElementById("frmEmpresa") as HTMLFormElement;
var frmCandidato = document.getElementById("frmCandidato") as HTMLFormElement;
var camposCadastro: { nome: string, mensagem: string }[];


// Controladores de serviço
const formulario = new Formulario();
const empresaController = new EmpresaController();
const candidatoController = new CandidatoController();


window.onload = () => {
    
    formulario.gerarPaises();
    formulario.gerarEstados();   
    
};

if(botaoAdicionarEmpresa){
    botaoAdicionarEmpresa.onclick = function () {
        if(frmEmpresa){
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

if(botaoAdicionarCandidato) {
    botaoAdicionarCandidato.onclick = function () {
        if(frmCandidato){
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




// botaoAdicionar.onclick = function () {

//     if (frmEmpresa) {

//         // Validação de dados
//         const camposCadastroEmpresa = [
//             { nome: "nome", mensagem: "O campo Nome está vazio." },
//             { nome: "email", mensagem: "O campo E-mail está vazio." },
//             { nome: "cnpj", mensagem: "O campo CNPJ está vazio." },
//             { nome: "pais", mensagem: "O campo País está vazio." },
//             { nome: "estado", mensagem: "O campo Estado está vazio." },
//             { nome: "cep", mensagem: "O campo CEP está vazio." },
//             { nome: "descricao", mensagem: "O campo Descrição está vazio." }
//         ];

//         frmCadastro = frmEmpresa;
//         camposCadastro = camposCadastroEmpresa
        

//     } else {

//         const camposCadastroCandidato = [
//             { nome: "nome", mensagem: "O campo Nome está vazio." },
//             { nome: "email", mensagem: "O campo E-mail está vazio." },
//             { nome: "cpf", mensagem: "O campo CPF está vazio." },
//             { nome: "idade", mensagem: "O campo Idade está vazio." },
//             { nome: "estado", mensagem: "O campo Estado está vazio." },
//             { nome: "cep", mensagem: "O campo CEP está vazio." },
//             { nome: "descricao", mensagem: "O campo Descrição está vazio." },
//             { nome: "competencias", mensagem: "O campo Competencias está vazio." }

//         ];

//         frmCadastro = frmCandidato;
//         camposCadastro = camposCadastroCandidato

//     }

//     if (formulario.validarEntradaDados(camposCadastro, frmCadastro)) {
//         console.log(frmCadastro)
//         const formData: FormData = new FormData(frmCadastro);
//         empresaController.adicionarEmpresa(formData);
//         candidatoController.adicionarCandidato(formData);
//     };

//};