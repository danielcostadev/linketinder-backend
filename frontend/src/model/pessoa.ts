import { Vaga } from "./vaga";

export abstract class Pessoa {

    protected _id: number;
    protected nome: string;
    protected email: string;
    protected estado: string;
    protected cep: string;
    protected descricao: string;
    protected competencias: string[];
    protected _vagas?: Vaga[]

    constructor(
        id: number,
        nome: string,
        email: string,
        estado: string,
        cep: string,
        descricao: string,
        competencias: string[],
        vagas?: Vaga[]
    ) {
        this._id = id;
        this.nome = nome;
        this.email = email;
        this.estado = estado;
        this.cep = cep;
        this.descricao = descricao;
        this.competencias = competencias;
        this._vagas = vagas;
    }

    abstract mostrarInformacoes(): string | string[]
    abstract mostrarInformacoesMatch(): string | string[]


    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    public get vagas(): Vaga[] | undefined {
        return this._vagas;
    }
    public set vagas(value: Vaga[]) {
        this._vagas = value;
    }
}