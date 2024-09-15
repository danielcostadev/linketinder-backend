export abstract class Pessoa {

    protected nome: string;
    protected email: string;
    protected estado: string;
    protected cep: string;
    protected descricao: string;
    protected competencias: string[]

    constructor(
        nome: string,
        email: string,
        estado: string,
        cep: string,
        descricao: string,
        competencias: string[]
    ) {
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