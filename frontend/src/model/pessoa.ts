import { Vaga } from "./vaga";

export abstract class Pessoa {

    public id: number;

    protected _nome: string;
    
    protected _email: string;
    
    protected _estado: string;
    
    protected _cep: string;
    
    protected _descricao: string;
    
    protected _competencias: string[];

    protected _tags: string[];
    
    protected _vagas?: Vaga[] | undefined;


    constructor(
        id: number,
        nome: string,
        email: string,
        estado: string,
        cep: string,
        descricao: string,
        competencias: string[],
        tags: string[],
        vagas?: Vaga[]
    ) {
        this.id = id;
        this._nome = nome;
        this._email = email;
        this._estado = estado;
        this._cep = cep;
        this._descricao = descricao;
        this._competencias = competencias;
        this._tags = tags;
        this._vagas = vagas;
    }

    public get nome(): string {
        return this._nome;
    }
    public set nome(value: string) {
        this._nome = value;
    }

    public get email(): string {
        return this._email;
    }
    public set email(value: string) {
        this._email = value;
    }
    public get estado(): string {
        return this._estado;
    }
    public set estado(value: string) {
        this._estado = value;
    }
    public get cep(): string {
        return this._cep;
    }
    public set cep(value: string) {
        this._cep = value;
    }
    public get descricao(): string {
        return this._descricao;
    }
    public set descricao(value: string) {
        this._descricao = value;
    }
    public get competencias(): string[] {
        return this._competencias;
    }
    public set competencias(value: string[]) {
        this._competencias = value;
    }
    public get tags() : string[] {
        return this._tags;
    }
    public set tags(value : string[]) {
        this._tags = value;
    }
    public get vagas(): Vaga[] | undefined {
        return this._vagas;
    }
    public set vagas(value: Vaga[] | undefined) {
        this._vagas = value;
    }
}