import { Pessoa } from './pessoa';
import { Vaga } from './vaga';

export class Candidato extends Pessoa {

    protected _cpf: string;
    protected _idade: string;
    protected _formacao: string;
    protected _telefone: string;
    protected _linkedin: string;

    constructor(
        id: number,
        nome: string,
        email: string,
        telefone: string,
        linkedin: string,
        cpf: string,
        idade: string,
        estado: string,
        cep: string,
        descricao: string,
        formacao: string,
        competencias: string[],
        tags: string[],
        vagas?: Vaga[]

    ) {
        super(id, nome, email, estado, cep, descricao, competencias,tags, vagas);
        this.id = id
        this._cpf = cpf
        this._idade = idade
        this._formacao = formacao
        this._telefone = telefone
        this._linkedin = linkedin
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

    public get telefone() : string {
        return this._telefone
    }   

    public set telefone(value : string) {
        this._telefone = value;
    }

    public get linkedin() : string {
        return this._linkedin
    }   

    public set linkedin(value : string) {
        this._linkedin = value;
    }

}