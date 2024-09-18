import { Pessoa } from './pessoa';
import { Vaga } from './vaga';

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
        competencias: string[],
        vagas?: Vaga[]

    ) {
        super(id, nome, email, estado, cep, descricao, competencias,vagas);
        this._id = id
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

    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

}