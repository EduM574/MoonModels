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
        %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="utf-8" />
                <link rel="stylesheet" type="text/css" href="css/senhas.css" media="screen" />
                <title>Editar senha</title>
            </head>

            <body>
                <header>
                    <div class="container main-header">
                        <span class="logo">MO<span class="logo-pink">O</span>N</span>
                        <div class="username-wrapper">
                            <span>
						<%= aluno.getNome() %>
					</span>
                            <form action="Logout.do" mothod="post" class="logout-wrapper">
                                <button type="submit">
							<img src="img/logout.png" alt="logout">
						</button>
                            </form>
                        </div>
                    </div>
                </header>
                <nav>
                    <a href="userHomeAluno.jsp">
                        <div class="menu-icon-wrapper">
                            <img src="./img/home.png" alt="Home">
                        </div>
                    </a>
                    <a href="#">
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
                    <p class="title">Editar senha</p>
                    <form action="CadastroAluno.do" method="POST" class="form">
                        <div class="input-form">
                            <div class="box">
                                <input type="password" name="password" maxlength="15" placeholder="Nova senha">
                                <input type="password" name="password" maxlength="15" placeholder="Confirme a nova senha">
                            </div>
                        </div>
                        <button type="submit">Editar</button>
                    </form>
                </section>
            </body>

            </html>
            <% }%>