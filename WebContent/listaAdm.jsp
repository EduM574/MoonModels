<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.Administrador" %>
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
            	ArrayList<Administrador> administradores = (ArrayList<Administrador>) request.getAttribute("administradores"); 
        %>

                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="utf-8" />
                    <link rel="stylesheet" type="text/css" href="css/cadastroAluno.css" media="screen" />
                    <title>Lista de ADMs</title>
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
                            <a href="userHomeAdm.jsp">
                                <div class="menu-icon-wrapper">
                                    <img src="./img/home.png" alt="Home">
                                </div>
                            </a>
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
                        <section class="content">
                            <p class="title">Lista de alunos</p>
                            <div class="lista-alunos">
                                <%
                        	for(Administrador admin: administradores) {
                        %>
                                    <div class="linha-aluno">
                                        <p>
                                            <%= admin.getNome() %>
                                        </p>
                                        <form action="ExibeDadosAdminEdicao.do" method="POST">
                                             <input type="hidden" name="email-admin" value="<%=admin.getEmail()%>">
                                             <button type="submit">
                                                  <img src="./img/edit-1.png" />
                                              </button>
                                        </form>
                                    </div>
                                    <%
                        	}
                       %>
                            </div>

                        </section>
                </body>

                </html>
                <%
                }
            %>