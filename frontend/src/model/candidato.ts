import { Pessoa } from './pessoa';

export class Candidato extends Pessoa {

    protected cpf: string;
    protected idade: string;
    protected formacao: string;

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
        competencias: string[]

    ) {
        super(id, nome, email, estado, cep, descricao, competencias);
        this.id = id
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
               this.formacao,
               this.competencias
        
    }

}