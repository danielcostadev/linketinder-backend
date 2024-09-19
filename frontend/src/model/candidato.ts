import { Pessoa } from './pessoa';
import { Vaga } from './vaga';

export class Candidato extends Pessoa {

    protected cpf: string;
    protected idade: string;
    protected _formacao: string;

    constructor(
        id: number,
        nome: string,
        email: string,
        cpf: string,
        idade: string,
        estado: string,
        cep: string,
        descricao: string,
        formacao: string,
        competencias: string[],
        vagas?: Vaga[]

    ) {
        super(id, nome, email, estado, cep, descricao, competencias,vagas);
        this.id = id
        this.cpf = cpf
        this.idade = idade
        this._formacao = formacao
    }

    
    public get formacao() : string {
        return this._formacao
    }

    
    public set formacao(value : string) {
        this._formacao = value;
    }
    
    

}