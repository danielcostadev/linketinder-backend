import { Pessoa } from './pessoa';

export class Empresa extends Pessoa {

    protected cnpj: string;
    protected pais: string;

    constructor(
        id: number,
        nome: string,
        email: string,
        cnpj: string,
        pais: string,
        estado: string,
        cep: string,
        descricao: string,
        competencias: string[]
    ) {
        super(id, nome, email, estado, cep, descricao, competencias);
        this.id = id;
        this.cnpj = cnpj;
        this.pais = pais;
    }
    mostrarInformacoes(): string | string[] {
        return this.competencias;
    }

    mostrarInformacoesMatch(): string | string[] {
        return [
            this.nome,
            this.email,
            this.cnpj,
            this.pais,
            this.estado,
            this.cep,
            this.descricao,
            ...this.competencias
        ];
    }

}