"use strict";
// import { CandidatoController } from '../controller/candidato.controller';
// import { EmpresaController } from '../controller/empresa.controller';
// import { VagaController } from '../controller/vaga.controller';
// import { Formulario } from '../service/formulario.service';
// import { NavegacaoService } from '../service/navegacao.service';
// // Centralização de interação com o DOM
// var botaoAdicionarVaga = document.getElementById("adicionarVaga") as HTMLButtonElement;
// var linkAdicionarVaga = document.getElementById("cadastrarVaga") as HTMLAnchorElement;
// var caixaFormularioCadastro = document.getElementById("caixa-branca-cadastro") as HTMLElement;
// var frmVaga = document.getElementById("frmVaga") as HTMLFormElement;
// var camposCadastro: { nome: string, mensagem: string }[];
// // Controladores de serviço
// const formulario = new Formulario();
// const empresaController = new EmpresaController();
// const candidatoController = new CandidatoController();
// const navegacaoService = new NavegacaoService();
// const vagaController = new VagaController();
// export class EmpresaView {
//     onload = () => {
//         const idEmpresaAtual = navegacaoService.obterIdDaUrl();
//         const idCandidatoAtual = navegacaoService.obterIdDaUrl();
//         if (idEmpresaAtual > 0) {
//             empresaController.exibirEmpresa(idEmpresaAtual);
//             candidatoController.listarCandidatos();
//         }
//         vagaController.listarVagas();
//         console.log("Ola")
//     };
// }
// if (botaoAdicionarVaga) {
//     botaoAdicionarVaga.onclick = function () {
//         if (frmVaga) {
//             const camposCadastroVaga = [
//                 { nome: "nome", mensagem: "O campo Nome está vazio." },
//                 { nome: "descricao", mensagem: "O campo Descrição está vazio." },
//                 { nome: "competencias", mensagem: "O campo Competencias está vazio." }
//             ];
//             camposCadastro = camposCadastroVaga
//             if (formulario.validarEntradaDados(camposCadastro, frmVaga)) {
//                 console.log(frmVaga)
//                 const formData: FormData = new FormData(frmVaga);
//                 vagaController.adicionarVaga(formData);
//             };
//         }
//     }
// }
// if (linkAdicionarVaga) {
//     linkAdicionarVaga.onclick = function () {
//         caixaFormularioCadastro.style.display = "block";
//     }
// }
