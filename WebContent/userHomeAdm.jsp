<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@page import="model.Administrador" %>
        <%@page import="model.Solicitacao" %>
            <%@page import="java.util.ArrayList" %>
                <%
            if (session.getAttribute("aluno") == null && session.getAttribute("adm") == null) {
                //caso a pessoa não esteja logada
                response.sendRedirect("loginAdm.jsp");

            } else if(session.getAttribute("aluno") != null) {
                //caso a pessoa que esteja logada seja um aluno
                response.sendRedirect("userHomeAluno.jsp");

            } else {
                Administrador adm = (Administrador) session.getAttribute("adm");
                ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) session.getAttribute("solicitacoesAdm");
                
        %>
                    <!DOCTYPE html>
                    <html lang="en">

                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <link rel="stylesheet" href="css/userHome.css">
                        <title>Solicitações</title>
                    </head>

                    <body>
                        <header>
                            <div class="container main-header">
                                <span class="logo">MO<span class="logo-pink">O</span>N</span>
                                <div class="username-wrapper">
                                    <span>
                                <%= adm.getNome() %>
                            </span>
                                    <form action="Logout.do" method="post" class="logout-wrapper">
                                        <button type="submit">
			                        <img src="img/logout.png" alt="logout">
			                    </button>
                                    </form>
                                </div>
                            </div>
                        </header>
                        <% 
                            String editar, adicionar;
                        
                            if(adm.getSetor().getIdSetor() == 4) {
                                editar = "listaUser.jsp";
                                adicionar = "cadastrarUser.jsp";
                            } else {
                                editar = "ListaAlunos.do";
                                adicionar = "cadastroAluno.jsp";
                            }
                        %>
                            <nav>
                                <form action="UserHomeAdm.do" method="post" class="form-nav-wrapper">
                                    <button type="submit"><img src="./img/home.png" alt="Home"></button>
                                </form>
                                <a href="<%=editar%>">
                                    <div class="menu-icon-wrapper">
                                        <img src="./img/newsfeed.png" alt="Alunos/ADMs">
                                    </div>
                                </a>
                                <a href="<%=adicionar%>">
                                    <div class="menu-icon-wrapper">
                                        <img src="./img/add-friend.png" alt="Adicionar usuário">
                                    </div>
                                </a>
                                <a href="verPerfilAdm.jsp">
                                    <div class="menu-icon-wrapper">
                                        <img src="./img/user.png" alt="Visualizar perfil">
                                    </div>
                                </a>
                                <a href="editarSenhaAdm.jsp">
                                    <div class="menu-icon-wrapper">
                                        <img src="./img/configuration.png" alt="Editar senha">
                                    </div>
                                </a>
                            </nav>

                            <div class="content">
                                <section class="info">
                                    <div class="info-card">
                                        <p class="info-title">Nº de Solicitações a serem resolvidas</p>
                                        <p class="info-content">
                                            <%=solicitacoes.size()%>
                                        </p>
                                    </div>
                                </section>

                                <section>
                                    <h2 class="titulo-solicitacao">Solicitações</h2>
                                    <div class="cards-adm">

                                        <%
                                            int totalSolicitacoes = 0; for(Solicitacao s : solicitacoes) {
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
                    </body>

                    </html>

                    <%
                		}
            		%>