//package controller;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//
//import com.google.gson.Gson;
//import com.ibm.cloud.sdk.core.security.Authenticator;
//import com.ibm.cloud.sdk.core.security.IamAuthenticator;
//import com.ibm.cloud.sdk.core.service.security.IamOptions;
//import com.ibm.watson.assistant.v1.model.Context;
//import com.ibm.watson.assistant.v1.model.MessageInput;
//import com.ibm.watson.assistant.v1.model.MessageOptions;
//import com.ibm.watson.assistant.v1.model.MessageResponse;
//import com.ibm.watson.assistant.v1.Assistant;
//
//@WebServlet("/assitant")
//public class AssistantController extends HttpServlet{
//    private Context context = null;
//	private static final long serialVersionUID = 1L;
//	
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//    @Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String msg = request.getParameter("question");
//        System.out.println(msg);
//        
//        MessageResponse resp = this.assistantAPICall(msg);
//
//        response.setContentType("application/json");
//		response.getWriter().write(new Gson().toJson(resp.getOutput().getText()));
//    }
//
//    private MessageResponse assistantAPICall(String msg) {
//
//        //Configuracao de autenticao do servico
//        //Colocar a sua APIKEY
//        Authenticator authenticator = new IamAuthenticator("N1Wy_LvlBViEMjNuWWfqD_ljerPcpCIJniXNfShsaMKp");
//        // Criando o objeto do servico desejado
//        Assistant service = new Assistant("2020-04-07", authenticator);
//
//		//Colocar a sua WORKSPACEID
//        String workspaceId = "d762afb2-67d3-43f2-87c4-8d1d6c37050b";
//        
//        // Preparando a mensagem de envio
//        MessageInput input = new MessageInput();
//        input.setText(msg);
//        
//        // Configurando os parametros para o Watson
//        MessageOptions messageOptions = new 
//            MessageOptions.Builder()
//            .workspaceId(workspaceId)
//            .input(input)
//            .context(this.context)
//            .build();
//        
//        // Conectando com o Assistant e recebendo a resposta dele
//        // MessageResponse response = service.message(messageOptions).execute().getResult();
//		
//		// this.context = response.getContext();
//
//        // return response;
//        return null;
//    }
//}
