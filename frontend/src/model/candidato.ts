import { Pessoa } from './pessoa';
import { Vaga } from './vaga';

export class Candidato extends Pessoa {

    protected _sobrenome: string;
    protected _cpf: string;
    protected _dataNascimento: string;
    protected _formacao: string;
    protected _telefone: string;
    protected _linkedin: string;

    constructor(
        id: number,
        nome: string,
        sobrenome: string,
        email: string,
        telefone: string,
        linkedin: string,
        cpf: string,
        dataNascimento: string,
        estado: string,
        cep: string,
        descricao: string,
        formacao: string,
        competencias: string[],
        tags: string[],
        senha: string,
        vagas?: Vaga[]

    ) {
        super(id, nome, email, estado, cep, descricao, competencias, tags, senha, vagas);
        this.id = id
        this._sobrenome = sobrenome
        this._cpf = cpf
        this._dataNascimento = dataNascimento
        this._formacao = formacao
        this._telefone = telefone
        this._linkedin = linkedin
    }

    public get sobrenome() : string {
        return this._sobrenome;
    }
    
    public set sobrenome(value : string) {
        this._sobrenome = value;
    }

    public get cpf() : string {
        return this._cpf;
    }
    
    public set cpf(value : string) {
        this._cpf = value;
    }

    public get idade() : string {
        return this._dataNascimento;
    }
    
    public set idade(value : string) {
        this._dataNascimento = value;
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