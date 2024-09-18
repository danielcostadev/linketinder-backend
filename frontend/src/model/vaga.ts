import { Candidato } from './candidato';
import { Empresa } from './empresa';


export class Vaga {
    public codigo: number;
    protected nome: string;
    protected descricao: string;
    protected candidato?: Candidato;
    protected competencias: string[]

    constructor(
        codigo: number,
        nome: string,
        descricao: string,
        competencias: string[]
    ){
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.competencias = competencias;
    }

}