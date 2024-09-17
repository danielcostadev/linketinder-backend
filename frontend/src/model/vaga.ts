import { Candidato } from './candidato';
import { Empresa } from './empresa';


export class Vaga {
    protected codigo: number;
    protected candidato?: Candidato;
    protected empresa: Empresa;
    protected competencias: string[]

    constructor(
        codigo: number,
        candidato: Candidato, 
        empresa: Empresa, 
        competencias: string[]
    ){
        this.codigo = codigo;
        this.candidato = candidato;
        this.empresa = empresa;
        this.competencias = competencias;
    }

}