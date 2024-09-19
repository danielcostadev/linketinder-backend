import { Pessoa } from './pessoa';
import { Vaga } from './vaga';

export class Candidato extends Pessoa {

    protected _cpf: string;
    protected _idade: string;
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
        this._cpf = cpf
        this._idade = idade
        this._formacao = formacao
    }

    public get cpf() : string {
        return this._cpf;
    }
    
    public set cpf(value : string) {
        this._cpf = value;
    }

    public get idade() : string {
        return this._idade;
    }
    
    public set idade(value : string) {
        this._idade = value;
    }

    public get formacao() : string {
        return this._formacao
    }   

    public set formacao(value : string) {
        this._formacao = value;
    }

    
    
    

}