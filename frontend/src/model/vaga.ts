import { Candidato } from './candidato';
import { Empresa } from './empresa';


export class Vaga {
    protected codigo: number;
    protected candidato?: Candidato;
    protected empresa: Empresa

    constructor(
        codigo: number,
        candidato: Candidato, 
        empresa: Empresa 
    ){
        this.codigo = codigo;
        this.candidato = candidato;
        this.empresa = empresa;
    }

}

// function gerarCodigo(): number {

//     let listaVagas: number = 1
//     let idGlobal: number;

//     if (listaVagas > 0) {
//         // let ids = listaTarefas.map(tarefa => tarefa.id); // Extrair os IDs usando map
//         // let maiorId = Math.max(...ids); // Pega o maior valor dentro doo array ... < é chamado de operador spread, separa todos os itens de um array 1 por 1
//         idGlobal = listaVagas + 1;
//     } else {
//         idGlobal = 1;
//     }

//     return idGlobal;
// }

