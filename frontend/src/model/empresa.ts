import { Pessoa } from './pessoa';
import { Vaga } from './vaga';

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
        competencias: string[],
        vagas?: Vaga[]
    ) {
        super(id, nome, email, estado, cep, descricao, competencias,vagas);
        this._id = id;
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

   public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

}