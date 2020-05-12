package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Aluno;
import model.Solicitacao;
import service.SolicitacaoService;

/**
 * Servlet implementation class CadastroAdmController
 */
@WebServlet("/CadastroSolicitacoes.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CadastroSolicitacoesController extends HttpServlet {
	private static final String SAVE_DIR = "anexoSolicitacoes";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Fazendo maior role pra pegar o arquivo
		// gets absolute path of the web application
		String appPath = request.getServletContext().getRealPath("");
		// constructs path of the directory to save uploaded file
		String savePath = appPath + File.separator + SAVE_DIR;
		savePath = "C:/Users/kesse/OneDrive/Documents/USJT/3º semestre/PI/MoonModels/WebContent/" + SAVE_DIR;

		// creates the save directory if it does not exists
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String fileName = null;
		for (Part part : request.getParts()) {
			fileName = extractFileName(part);
			// refines the fileName in case it is an absolute path
			fileName = new File(fileName).getName();

			try {
				part.write(savePath + File.separator + fileName);
			} catch (Exception e) {
				
			}
		}

		String nomeAnexo = "";
		for (Part part : request.getParts()) {
			fileName = extractFileName(part);
			fileName = new File(fileName).getName();
			
			if(!fileName.equals("")) {
				nomeAnexo = fileName;
			}
		}
		
		String sNome = request.getParameter("solicitacao");
		String sDescricao = request.getParameter("descricao");

		HttpSession session = request.getSession();
		Aluno al = (Aluno) session.getAttribute("aluno");

		Solicitacao sol = new Solicitacao();
		sol.setNome(sNome);
		sol.setDescricao(sDescricao);
		sol.setAluno(al);

		if(!nomeAnexo.equals("")) {
			File anexo = new File(savePath + File.separator + nomeAnexo);
			sol.setAnexo(anexo);
		}

		SolicitacaoService ss = new SolicitacaoService();
		ss.create(sol);

		String title = "Solicitação realizada com sucesso.";
		request.setAttribute("title", title);

		RequestDispatcher view = request.getRequestDispatcher("mensagemOkAluno.jsp");
		view.forward(request, response);

	}

	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}