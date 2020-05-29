<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@page import="model.Aluno" %>
        <%@page import="model.Solicitacao" %>
            <%@page import="java.util.ArrayList" %>
                <%
        	if (session.getAttribute("aluno") == null && session.getAttribute("adm") == null) {
                //caso a pessoa não esteja logada
                response.sendRedirect("loginAluno.jsp");
            
            } else if(session.getAttribute("adm") != null) {
                //caso a pessoa que esteja logada seja um aluno
                response.sendRedirect("UserHomeAdm.do");

            } else {
            	  Aluno aluno = (Aluno) session.getAttribute("aluno");
            	  ArrayList<Solicitacao> solicitacaoDeferida = (ArrayList<Solicitacao>) session.getAttribute("solicitacaoDeferida");
            	  ArrayList<Solicitacao> solicitacaoIndeferida = (ArrayList<Solicitacao>) session.getAttribute("solicitacaoIndeferida");
            	  ArrayList<Solicitacao> solicitacaoAndamento = (ArrayList<Solicitacao>) session.getAttribute("solicitacaoAndamento");
        %>
                    <!DOCTYPE html>
                    <html lang="en">

                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <link rel="stylesheet" href="css/userHome.css">
                        <script src="chatbot/frontend/script.js" defer></script>
                        <script src="js/script.js" defer></script>
                        <title>Solicitações</title>
                    </head>

                    <body>
                        <header>
                            <div class="container main-header">
                                <span class="logo">MO<span class="logo-pink">O</span>N</span>
                                <div class="username-wrapper">
                                    <span><%= aluno.getNome() %></span>
                                    <form action="Logout.do" method="post" class="logout-wrapper">
                                        <button type="submit"><img src="img/logout.png" alt="logout"></button>
                                    </form>
                                </div>
                            </div>
                        </header>
                        <nav>
                            <form action="UserHomeAluno.do" method="post" class="form-nav-wrapper">
                                <button type="submit"><img src="./img/home.png" alt="Home"></button>
                            </form>
                            <%
                        if(aluno.getStatus().equals("ATIVO")) {
                            //caso o aluno que esteja logado esteja ativo, ele pode criar solicitacoes novas
	                        out.print("<a href='cadastroSolicitacoes.jsp'><div class='menu-icon-wrapper'><img src='./img/more.png' alt='Cadastrar solicitação'></div></a>");     
                        } 
                     %>
                                <a href="verPerfilAluno.jsp">
                                    <div class="menu-icon-wrapper">
                                        <img src="./img/user.png" alt="Visualizar perfil">
                                    </div>
                                </a>
                                <a href="editarSenhaAluno.jsp">
                                    <div class="menu-icon-wrapper">
                                        <img src="./img/configuration.png" alt="Editar senha">
                                    </div>
                                </a>
                        </nav>

                        <div class="chatboat-icon">
                            <button onClick="openBot()"><img src="./img/comment.png" alt="Icone do chatboat"></button>
                        </div>

                        <div class="content">
                            <section class="info">
                                <div class="info-card">
                                    <p class="info-title">Total de solicitações</p>
                                    <p class="info-content">
                                        <%= solicitacaoDeferida.size() + solicitacaoIndeferida.size() + solicitacaoAndamento.size()%>
                                    </p>
                                </div>
                                <div class="info-card">
                                    <p class="info-title">Solicitações Deferidas</p>
                                    <p class="info-content">
                                        <%= solicitacaoDeferida.size() %>
                                    </p>
                                </div>
                                <div class="info-card">
                                    <p class="info-title">Solicitações Indeferidas</p>
                                    <p class="info-content">
                                        <%= solicitacaoIndeferida.size() %>
                                    </p>
                                </div>
                            </section>

                            <section>
                                <h2 class="titulo-solicitacao">Em andamento</h2>
                                <div class="cards-adm">
                                    <%
                               int totalSolicitacoes = 0; for(Solicitacao s : solicitacaoAndamento) {
                           %>
                                        <form action="UserHomeSolicitacao.do" method="POST" class="s-card-color-adm">
                                            <input type="hidden" name="id-solicitacao" value="<%=s.getIdSolicitacao()%>">
                                            <button type="submit" class="s-card-content-adm">
                                       <div class="s-row-adm">
                                           <div>
                                               <span class="s-card-subtitle">Nome:</span>
                                               <span><%=s.getNome()%></span>
                                           </div>
                                           <div class="s-status-ativo"></div>
                                       </div>
                                       <div class="s-row-adm">
                                           <div>
                                               <span class="s-card-subtitle">Status:</span>
                                               <span><%=s.getStatus()%></span>
                                           </div>
                                           <div class="status-card-adm">
                                               <span class="s-card-subtitle">Código:</span>
                                               <span><%=s.getIdSolicitacao()%></span>
                                           </div>
                                       </div>
                                   </button>
                                        </form>
                                        <%
                           	}
                           %>
                                </div>
                            </section>

                            <section>
                                <h2 class="titulo-solicitacao">Deferidas</h2>
                                <div class="cards-adm">

                                    <%
                                            for(Solicitacao s : solicitacaoDeferida) {
                                        %>
                                        <form action="UserHomeSolicitacao.do" method="POST" class="s-card-color-adm">
                                            <input type="hidden" name="id-solicitacao" value="<%=s.getIdSolicitacao()%>">
                                            <button type="submit" class="s-card-content-adm">
                                                    <div class="s-row-adm">
                                                        <div>
                                                            <span class="s-card-subtitle">Nome:</span>
                                                            <span><%=s.getNome()%></span>
                                                        </div>
                                                        <div class="s-status-ativo"></div>
                                                    </div>
                                                    <div class="s-row-adm">
                                                        <div>
                                                            <span class="s-card-subtitle">Status:</span>
                                                            <span><%=s.getStatus()%></span>
                                                        </div>
                                                        <div class="status-card-adm">
                                                            <span class="s-card-subtitle">Código:</span>
                                                            <span><%=s.getIdSolicitacao()%></span>
                                                        </div>
                                                    </div>
                                                </button>
                                        </form>
                                        <%
                                        	}
                                        %>
                                </div>
                            </section>

                            <section>
                                <h2 class="titulo-solicitacao">Indeferidas</h2>
                                <div class="cards-adm">

                                    <%
                                            for(Solicitacao s : solicitacaoIndeferida) {
                                        %>
                                        <form action="UserHomeSolicitacao.do" method="POST" class="s-card-color-adm">
                                            <input type="hidden" name="id-solicitacao" value="<%=s.getIdSolicitacao()%>">
                                            <button type="submit" class="s-card-content-adm">
                                                    <div class="s-row-adm">
                                                        <div>
                                                            <span class="s-card-subtitle">Nome:</span>
                                                            <span><%=s.getNome()%></span>
                                                        </div>
                                                        <div class="s-status-ativo"></div>
                                                    </div>
                                                    <div class="s-row-adm">
                                                        <div>
                                                            <span class="s-card-subtitle">Status:</span>
                                                            <span><%=s.getStatus()%></span>
                                                        </div>
                                                        <div class="status-card-adm">
                                                            <span class="s-card-subtitle">Código:</span>
                                                            <span><%=s.getIdSolicitacao()%></span>
                                                        </div>
                                                    </div>
                                                </button>
                                        </form>
                                        <%
                                        	}
                                        %>
                                </div>
                            </section>
                        </div>

                        <section class="chatboat" id="tela-chatboat">
                            <div class="chat-header">
                                <img src="./img/lunaIcon.png" alt="Icone de lua">
                                <span class="title-chat">Luna</span>
                            </div>
                            <div class="chat-content" id="chat-content">

                            </div>
                            <div class="chat-footer">
                                <input type="text" id="textInput">
                            </div>
                        </section>
                    </body>

                    </html>
                    <% }%>