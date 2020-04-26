package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.io.FileOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Aluno;
import model.Solicitacao;
import service.SolicitacaoService;

/**
 * Servlet implementation class CadastroAdmController
 */
@WebServlet("/CadastroSolicitacoes.do")
public class CadastroSolicitacoesController extends HttpServlet {
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
		// atributos capturados
		String sNome = request.getParameter("solicitacao");
		String sDescricao = request.getParameter("descricao");
		
		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
//		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		InputStream fileContent = filePart.getInputStream();
		
		String FILE_TO = "c:\\Download\\google.pdf";
		
		try {
            File file = new File(FILE_TO);
            copyInputStreamToFile(fileContent, file);
            
            // criar obj
            Aluno al = new Aluno();
            al.setRa(4);// id pronto, trocar depois
            Solicitacao sol = new Solicitacao();
            sol.setNome(sNome);
            sol.setDescricao(sDescricao);
            sol.setAluno(al);
            sol.setAnexo(file);
            
            SolicitacaoService ss = new SolicitacaoService();
            ss.create(sol);
            
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Cadastro Administrador</title></head><body>");
            out.println("Nome da Solicitação = " + sol.getNome() + "<br>");
            out.println("Descrição = " + sol.getDescricao() + "<br>");
            out.println("Ra = " + sol.getAluno().getRa() + "<br>");
            out.println("</body></html>");
        } catch(IOException e) {
        	e.printStackTrace();
        }

	}

	private static void copyInputStreamToFile(InputStream inputStream, File file)
			throws IOException {

	        try (FileOutputStream outputStream = new FileOutputStream(file)) {

	            int read;
	            byte[] bytes = new byte[1024];

	            while ((read = inputStream.read(bytes)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }

				// commons-io
	            //IOUtils.copy(inputStream, outputStream);

	        }

	    }
}
