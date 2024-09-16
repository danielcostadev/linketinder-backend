export abstract class Pessoa {

    public id: number;
    protected nome: string;
    protected email: string;
    protected estado: string;
    protected cep: string;
    protected descricao: string;
    protected competencias: string[]

    constructor(
        id: number,
        nome: string,
        email: string,
        estado: string,
        cep: string,
        descricao: string,
        competencias: string[]
    ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.estado = estado;
        this.cep = cep;
        this.descricao = descricao;
        this.competencias = competencias;
    }

    abstract mostrarInformacoes(): string | string[]
    abstract mostrarInformacoesMatch(): string | string[]
}