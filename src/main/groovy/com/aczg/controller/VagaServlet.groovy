package com.aczg.controller

import com.aczg.DAO.EmpresaDAO
import com.aczg.DAO.VagaDAO
import com.aczg.DAO.interfaces.IEmpresaDAO
import com.aczg.DAO.interfaces.IVagaDAO
import com.aczg.controller.interfaces.IEmpresaController
import com.aczg.controller.interfaces.IVagaController
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.InvalidDataException
import com.aczg.model.Candidato
import com.aczg.model.Empresa
import com.aczg.model.Vaga
import com.aczg.service.EmpresaService
import com.aczg.service.VagaService
import com.aczg.service.interfaces.IEmpresaService
import com.aczg.service.interfaces.IVagaService
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.sql.SQLException

class VagaServlet extends HttpServlet{

    IVagaController vagaController;
    IEmpresaController empresaController;

    VagaServlet() {}

    @Override
    void init(ServletConfig config) throws ServletException {
        super.init(config)

        IVagaDAO vagaDAO = new VagaDAO()
        IVagaService vagaService = new VagaService(vagaDAO)
        this.vagaController = new VagaController(vagaService)

        IEmpresaDAO empresaDAO = new EmpresaDAO()
        IEmpresaService empresaService = new EmpresaService(empresaDAO)
        this.empresaController = new EmpresaController(empresaService)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8")
            response.setContentType("application/json; charset=UTF-8")
            response.setCharacterEncoding("UTF-8")

            String json = request.reader.text
            Map jsonMap = new JsonSlurper().parseText(json)

            String idEmpresa = jsonMap.empresaId

            if (idEmpresa == null || idEmpresa.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
                ErrorResponse error = new ErrorResponse("ID da empresa não inserido.", "ID_NAO_FORNECIDO")
                response.getWriter().write(new JsonBuilder(error).toString())
                return
            }

            Long empresaId = Long.parseLong(idEmpresa)
            Empresa empresaExistente = getEmpresaController().buscarPorId(empresaId)

            if (empresaExistente == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND)
                ErrorResponse error = new ErrorResponse("Empresa com ID ${empresaId} não encontrada.", "EMPRESA_NAO_ENCONTRADA")
                response.writer.write(new JsonBuilder(error).toString())
                return
            }

            Vaga vaga = criarVaga(jsonMap)

            Long vagaId = getVagaController().cadastrar(vaga)

            response.setStatus(HttpServletResponse.SC_CREATED)
            response.setHeader("Location", "/api/v1/vagas/${vagaId}")

            Map responseMap = [
                    message: "Vaga criada com sucesso.",
                    vaga: [
                            id: vagaId,
                            titulo: vaga.nome,
                            descricao: vaga.descricao,
                            requisitos: vaga.local,
                            empresaId: empresaId
                    ],
                    links: [
                            self: "/api/v1/vagas/${vagaId}",
                            list: "/api/v1/vagas"
                    ]
            ]

            response.writer.write(new JsonBuilder(responseMap).toString())

        } catch (InvalidDataException e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            ErrorResponse error = new ErrorResponse("Dados inválidos. ${e.getMessage()}", "DADOS_INVALIDOS")
            response.writer.write(new JsonBuilder(error).toString())

        } catch (DatabaseException e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            ErrorResponse error = new ErrorResponse("Erro ao processar a requisição no banco de dados. ${e.getMessage()}", "ERRO_BANCO")
            response.writer.write(new JsonBuilder(error).toString())

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            ErrorResponse error = new ErrorResponse("Erro desconhecido. Por favor, tente novamente mais tarde.", "ERRO_DESCONHECIDO")
            response.writer.write(new JsonBuilder(error).toString())
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8")
            response.setContentType("application/json; charset=UTF-8")
            response.setCharacterEncoding("UTF-8")

            String pathInfo = request.getPathInfo()

            if (pathInfo == null || pathInfo == "/") {
                List<Vaga> vagas = getVagaController().listar()
                response.setStatus(HttpServletResponse.SC_OK)
                response.getWriter().write(new JsonBuilder(vagas).toString())
                return
            }

            String idStr = pathInfo.substring(1)
            Long vagaId = Long.parseLong(idStr)
            Vaga vaga = getVagaController().buscarPorId(vagaId)

            if (vaga == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND)
                ErrorResponse error = new ErrorResponse("Vaga não encontrada.", "VAGA_NAO_ENCONTRADA")
                response.getWriter().write(new JsonBuilder(error).toString())
            } else {
                response.setStatus(HttpServletResponse.SC_OK)
                response.getWriter().write(new JsonBuilder(vaga).toString())
            }
        } catch (NumberFormatException e) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            ErrorResponse error = new ErrorResponse("ID inválido: ${e.getMessage()}", "ID_INVALIDO")
            response.getWriter().write(new JsonBuilder(error).toString())

        } catch (SQLException e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao acessar o banco de dados: ${e.getMessage()}", "ERRO_BANCO")
            response.getWriter().write(new JsonBuilder(error).toString())

        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao buscar candidatos: ${e.getMessage()}", "ERRO_DESCONHECIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8")
            response.setContentType("application/json; charset=UTF-8")
            response.setCharacterEncoding("UTF-8")

            String json = request.reader.text
            Map jsonMap = new JsonSlurper().parseText(json)

            String pathInfo = request.getPathInfo()
            if (pathInfo == null || pathInfo == ("/")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
                ErrorResponse error = new ErrorResponse("ID da vaga não inserido.", "ID_NAO_FORNECIDO")
                response.getWriter().write(new JsonBuilder(error).toString())
                return
            }

            String idStr = pathInfo.substring(1)
            Long vagaId = Long.parseLong(idStr)
            Vaga vagaExistente = getVagaController().buscarPorId(vagaId)

            if (vagaExistente == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND)
                ErrorResponse error = new ErrorResponse("Vaga com ID ${vagaId} não encontrada.", "VAGA_NAO_ENCONTRADA")
                response.getWriter().write(new JsonBuilder(error).toString())
                return
            }

            vagaExistente = editarVaga(jsonMap, vagaExistente)
            getVagaController().editar(vagaExistente)

            response.setStatus(HttpServletResponse.SC_OK)
            response.getWriter().write("Vaga com ID ${vagaId} atualizada com sucesso.")

        } catch (NumberFormatException e) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            ErrorResponse error = new ErrorResponse("ID inválido: ${e.getMessage()}", "ID_INVALIDO")
            response.getWriter().write(new JsonBuilder(error).toString())

        } catch (DatabaseException e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao acessar o banco de dados: ${e.getMessage()}", "ERRO_BANCO")
            response.getWriter().write(new JsonBuilder(error).toString())

        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao atualizar vaga: ${e.getMessage()}", "ERRO_DESCONHECIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            request.setCharacterEncoding("UTF-8")
            response.setContentType("application/json; charset=UTF-8")
            response.setCharacterEncoding("UTF-8")

            String pathInfo = request.getPathInfo()
            if (pathInfo == null || pathInfo == ("/")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
                ErrorResponse error = new ErrorResponse("ID da vaga não fornecido.", "ID_NAO_FORNECIDO")
                response.getWriter().write(new JsonBuilder(error).toString())
                return
            }

            String idStr = pathInfo.substring(1)
            Long vagaId = Long.parseLong(idStr)

            Vaga vagaExistente = getVagaController().buscarPorId(vagaId)
            if (vagaExistente == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND)
                ErrorResponse error = new ErrorResponse("Vaga com ID ${vagaId} não encontrada.", "VAGA_NAO_ENCONTRADA")
                response.getWriter().write(new JsonBuilder(error).toString())
                return
            }

            getVagaController().remover(vagaId)
            response.setStatus(HttpServletResponse.SC_OK)
            response.getWriter().write("vaga com ID ${vagaId} removida com sucesso!")

        } catch (NumberFormatException e) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            ErrorResponse error = new ErrorResponse("ID inválido: ${e.getMessage()}", "ID_INVALIDO")
            response.getWriter().write(new JsonBuilder(error).toString())

        } catch (SQLException e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao acessar o banco de dados: ${e.getMessage()}", "ERRO_BANCO")
            response.getWriter().write(new JsonBuilder(error).toString())

        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao buscar vagas: ${e.getMessage()}", "ERRO_DESCONHECIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        }

    }

    private Vaga criarVaga(Map jsonMap) {

        String nome = jsonMap.get("nome")
        String descricao = jsonMap.get("descricao")
        String local = jsonMap.get("local")
        Long empresaId = jsonMap.get("empresaId") as Long

        return new Vaga(nome, descricao, local, empresaId)
    }

    private Vaga editarVaga(Map jsonMap, Vaga vagaExistente) {
        vagaExistente.id = (vagaExistente.id)
        vagaExistente.nome = jsonMap.nome ?: vagaExistente.nome
        vagaExistente.descricao = jsonMap.descricao ?: vagaExistente.descricao
        vagaExistente.local = jsonMap.local ?: vagaExistente.local
        vagaExistente.empresaId = vagaExistente.empresaId

        return new Vaga(vagaExistente.id, vagaExistente.nome, vagaExistente.descricao, vagaExistente.local, vagaExistente.empresaId)

    }

}
