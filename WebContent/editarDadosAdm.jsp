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
                <link rel="stylesheet" type="text/css" href="css/cadastroAluno.css" media="screen" />
                <title>Editar Administrador</title>
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
                        <p class="title">Editar um administrador</p>
                        <span class="data-ra">fulano@usjt.br</span>
                        <form action="CadastroAluno.do" method="POST" class="form">
                            <div class="input-form">
                                <div class="box">
                                    <input type="text" name="nome" maxlength="15" placeholder="Nome">
                                    <input type="number" name="cpf" placeholder="CPF">
                                    <select name="setor" class="altura-diferente">
	                 		<option value="SUS" selected>Selecione um setor</option>
	                  	 	<option value="GDE" >Gestão de estágio</option>
	                    	<option value="TPE">Transporte escolar</option>
							<option value="ATC">Atividades curriculares</option>
						</select>
                                </div>
                                <div class="box">
                                    <input type="text" name="sobrenome" maxlength="15" placeholder="Sobrenome">
                                    <input type="password" name="password" maxlength="15" placeholder="Senha">
                                    <select name="status" class="altura-diferente">
	                 		<option value="ATIVO" selected>ATIVO</option>
	                  	 	<option value="INATIVO" >INATIVO</option>
	                </select>
                                </div>
                            </div>
                            <button type="submit">Editar</button>
                        </form>
                    </section>
            </body>

            </html>
            <%
                }
            %>