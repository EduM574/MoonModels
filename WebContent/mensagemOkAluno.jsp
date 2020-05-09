<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.Aluno" %>
        <%
        if (session.getAttribute("aluno") == null && session.getAttribute("adm") == null) {
            //caso a pessoa não esteja logada
            response.sendRedirect("loginAluno.jsp");

        } else if(session.getAttribute("adm") != null) {
            //caso a pessoa que esteja logada seja um aluno
            response.sendRedirect("userHomeAdm.jsp");

        } else {
              Aluno aluno = (Aluno) session.getAttribute("aluno");        
              String title = (String) request.getAttribute("title");
        %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="utf-8" />
                <link rel="stylesheet" type="text/css" href="css/senhas.css" media="screen" />
                <title>Moon</title>
            </head>

            <body>
                <header>
                    <div class="container main-header">
                        <span class="logo">MO<span class="logo-pink">O</span>N</span>
                        <div class="username-wrapper">
                            <span>
                                <%= aluno.getNome() %>
                            </span>
                            <form action="Logout.do" method="post" class="logout-wrapper">
                                <button type="submit">
                        <img src="img/logout.png" alt="logout">
                    </button>
                            </form>
                        </div>
                    </div>
                </header>
                <nav>
                    <form action="UserHomeAluno.do" method="post" class="form-nav-wrapper">
                        <button type="submit">
                            <img src="./img/home.png" alt="Home">
                        </button>
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
                <section class="content">
                    <div class="editar-user">
                        <p class="title">
                            <%=title%>
                        </p>
                    </div>
                </section>
            </body>

            </html>
            <%
                }
            %>