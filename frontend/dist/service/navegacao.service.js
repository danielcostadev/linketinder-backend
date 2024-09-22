import { EmpresaService } from "../service/empresa.service";
import { CandidatoService } from "./candidato.service";
export class NavegacaoService {
    obterIdDaUrl() {
        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get("id");
        if (id) {
            return parseInt(id, 10);
        }
        return 0;
    }
    carregarEmpresa() {
        const idEmpresaAtual = this.obterIdDaUrl();
        if (idEmpresaAtual !== null) {
            const empresaService = new EmpresaService();
            const empresa = empresaService.obterEmpresa(idEmpresaAtual);
            if (empresa) {
                console.log("Empresa encontrada:");
            }
            else {
                console.log("Empresa não encontrada!");
            }
        }
        else {
            console.log("ID da empresa não encontrado na URL.");
        }
    }
    carregarCandidato() {
        const idCandidatoAtual = this.obterIdDaUrl();
        if (idCandidatoAtual !== null) {
            const candidatoService = new CandidatoService();
            const candidato = candidatoService.obterCandidato(idCandidatoAtual);
            if (candidato) {
                console.log("Candidato Encontrado!");
            }
            else {
                console.log("Candidato não encontrado!");
            }
        }
        else {
            console.log("ID do candidato não encontrado na URL");
        }
    }
}
