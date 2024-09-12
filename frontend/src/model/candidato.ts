import { Pessoa } from './pessoa';

export class Candidato extends Pessoa {

    constructor(
        private nome: string,
        private email: string,
        private idade: number,
        private estado: string, 
        private cep: string,
        private descricao: string,
        private competencias : string[]
        
    ){
        super(nome,estado,cep,descricao)

     }

    mostrarInformacoes(): void {
        
    }

}