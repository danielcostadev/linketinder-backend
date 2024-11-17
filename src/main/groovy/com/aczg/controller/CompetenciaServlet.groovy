package com.aczg.controller

import com.aczg.DAO.CandidatoDAO
import com.aczg.DAO.CompetenciaDAO
import com.aczg.DAO.VagaDAO
import com.aczg.DAO.interfaces.ICandidatoDAO
import com.aczg.DAO.interfaces.ICompetenciaDAO
import com.aczg.DAO.interfaces.IVagaDAO
import com.aczg.controller.interfaces.ICandidatoController
import com.aczg.controller.interfaces.IVagaController
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.exceptions.InvalidDataException
import com.aczg.interfaces.ICompetencia
import com.aczg.model.Candidato
import com.aczg.model.Competencia
import com.aczg.model.Vaga
import com.aczg.service.CandidatoService
import com.aczg.service.CompetenciaService
import com.aczg.service.VagaService
import com.aczg.service.interfaces.ICandidatoService
import com.aczg.service.interfaces.IVagaService
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.sql.SQLException

class CompetenciaServlet extends HttpServlet {
    ICompetencia competenciaController
    IVagaController vagaController
    ICandidatoController candidatoController

    @Override
    void init(ServletConfig config) throws ServletException {
        super.init(config)

        ICompetenciaDAO competenciaDAO = new CompetenciaDAO()
        ICompetencia competenciaService = new CompetenciaService(competenciaDAO)
        this.competenciaController = new CompetenciaController(competenciaService)

        IVagaDAO vagaDAO = new VagaDAO()
        IVagaService vagaService = new VagaService(vagaDAO)
        this.vagaController = new VagaController(vagaService)

        ICandidatoDAO candidatoDAO = new CandidatoDAO()
        ICandidatoService candidatoService = new CandidatoService(candidatoDAO)
        this.candidatoController = new CandidatoController(candidatoService)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.contentType = "application/json; charset=UTF-8"
            response.characterEncoding = "UTF-8"

            String json = request.reader.text
            Map jsonMap = new JsonSlurper().parseText(json)

            Long vagaId = jsonMap.vagaId as Long
            Long candidatoId = jsonMap.candidatoId as Long

            if (!vagaId && !candidatoId) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
                ErrorResponse error = new ErrorResponse("ID de vaga ou candidato é obrigatório.", "PARAMETRO_INVALIDO")
                response.getWriter().write(new JsonBuilder(error).toString())
                return
            }

            Vaga vagaExistente = vagaId ? buscarVagaPorId(vagaId, response) : null
            if (vagaId && !vagaExistente) return

            Candidato candidatoExistente = candidatoId ? buscarCandidatoPorId(candidatoId, response) : null
            if (candidatoId && !candidatoExistente) return

            String competencias = jsonMap.competencias
            competencias = competencias.replaceAll("[\\[\\]]", "")
            List<String> listaCompetencias = competencias.split(",")*.trim() as List

            if (vagaId) competenciaController.cadastrar(listaCompetencias, null, vagaId)
            if (candidatoId) competenciaController.cadastrar(listaCompetencias, candidatoId, null)

            response.status = HttpServletResponse.SC_CREATED
            response.writer.write(new JsonBuilder([message: "Competência cadastrada com sucesso!"]).toString())

        } catch (EntidadeJaExisteException e) {
            response.status = HttpServletResponse.SC_CONFLICT  // 409 Conflict
            ErrorResponse error = new ErrorResponse("O candidato já existe. " + e.message, "CANDIDATO_DUPLICADO")
            response.writer.write(new JsonBuilder(error).toString())

        } catch (InvalidDataException e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST  // 400 Bad Request
            ErrorResponse error = new ErrorResponse("Dados inválidos. " + e.message, "DADOS_INVALIDOS")
            response.writer.write(new JsonBuilder(error).toString())

        } catch (DatabaseException e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR  // 500 Internal Server Error
            ErrorResponse error = new ErrorResponse("Erro ao processar a requisição no banco de dados. " + e.message, "ERRO_BANCO")
            response.writer.write(new JsonBuilder(error).toString())

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR  // 500 Internal Server Error
            ErrorResponse error = new ErrorResponse("Erro desconhecido. Por favor, tente novamente mais tarde.", "ERRO_DESCONHECIDO")
            response.writer.write(new JsonBuilder(error).toString())
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.contentType = "application/json; charset=UTF-8"
            response.characterEncoding = "UTF-8"

            String id = request.getParameter("id")
            if (!id) {
                List<Competencia> compentencias = competenciaController.listar()
                response.status = HttpServletResponse.SC_OK
                response.writer.write(new JsonBuilder(compentencias).toString())
                return
            }

            Long competenciaId = id as Long
            Competencia competencia = buscarCompetenciaPorId(competenciaId, response)
            if (!competencia) return

            response.status = HttpServletResponse.SC_OK
            response.writer.write(new JsonBuilder(competencia).toString())
        } catch (NumberFormatException e) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            ErrorResponse error = new ErrorResponse("ID inválido: " + e.getMessage(), "ID_INVALIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        } catch (SQLException e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao acessar o banco de dados: " + e.getMessage(), "ERRO_BANCO")
            response.getWriter().write(new JsonBuilder(error).toString())
        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao buscar compêtencias: " + e.getMessage(), "ERRO_DESCONHECIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        }
    }

    private Vaga buscarVagaPorId(Long id, HttpServletResponse response) {
        Vaga vaga = vagaController.buscarPorId(id)
        if (!vaga) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND)
            ErrorResponse error = new ErrorResponse("Vaga com ID $id não encontrada.", "VAGA_NAO_ENCONTRADA")
            response.getWriter().write(new JsonBuilder(error).toString())
        }
        return vaga
    }

    private Candidato buscarCandidatoPorId(Long id, HttpServletResponse response) {
        Candidato candidato = candidatoController.buscarPorId(id)
        if (!candidato) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND)
            ErrorResponse error = new ErrorResponse("Candidato com ID $id não encontrado.", "CANDIDATO_NAO_ENCONTRADO")
            response.getWriter().write(new JsonBuilder(error).toString())
        }
        return candidato
    }

    private Competencia buscarCompetenciaPorId(Long id, HttpServletResponse response) {
        Competencia competencia = competenciaController.buscarPorId(id)
        if (!competencia) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND)
            ErrorResponse error = new ErrorResponse("Competência com ID $id não encontrada.", "CANDIDATO_NAO_ENCONTRADO")
            response.getWriter().write(new JsonBuilder(error).toString())
        }
        return competencia
    }
}
