export abstract class Pessoa {
    constructor(
        private nome: string,
        private estado: string,
        private cep: string,
        private descricao: string
    ) { }
    abstract mostrarInformacoes(): void
}