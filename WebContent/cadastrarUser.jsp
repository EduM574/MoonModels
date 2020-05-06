<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.Administrador" %>
        <%
            if (session.getAttribute("aluno") == null && session.getAttribute("adm") == null) {
                //caso a pessoa não esteja logada
                response.sendRedirect("loginAdm.jsp");

            } else if(session.getAttribute("aluno") != null) {
                //caso a pessoa que esteja logada seja um aluno
                response.sendRedirect("userHomeAluno.jsp");

            } else {
            	Administrador adm = (Administrador) session.getAttribute("adm");
        
        %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="utf-8" />
                <link rel="stylesheet" type="text/css" href="css/senhas.css" media="screen" />
                <title>Cadastrar</title>
            </head>

            <body>
                <header>
                    <div class="container main-header">
                        <span class="logo">MO<span class="logo-pink">O</span>N</span>
                        <div class="username-wrapper">
                            <span>
                                <%= adm.getNome() %>
                            </span>
                            <form action="Logout.do" mothod="post" class="logout-wrapper">
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
                        editar = "listaAlunos.do";
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
                        <p class="title">Cadastrar</p>
                        <div class="editar-user">
                            <a href="cadastroAdm.jsp">Cadastrar ADMs</a>
                            <a href="cadastroAluno.jsp">Cadastrar Alunos</a>
                        </div>
                    </section>
            </body>

            </html>
            <%
                }
            %>