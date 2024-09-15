import { Pessoa } from './pessoa';

export class Candidato extends Pessoa {

    protected cpf: string;
    protected idade: number;
    protected formacao: string;

    constructor(
        nome: string,
        email: string,
        cpf: string,
        idade: number,
        estado: string,
        cep: string,
        descricao: string,
        competencias: string[],
        formacao: string

    ) {
        super(nome, email, estado, cep, descricao, competencias);
        this.cpf = cpf
        this.idade = idade
        this.formacao = formacao
    }

    mostrarInformacoes(): string | string[]{
        return this.competencias,
               this.formacao
    }

    mostrarInformacoesMatch(): string | string[]{
        return this.nome,
               this.email,
               this.cpf,
               this.idade,
               this.estado,
               this.cep,
               this.descricao,
               this.competencias
        
    }

}