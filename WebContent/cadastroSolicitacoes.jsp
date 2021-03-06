<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.Aluno" %>
        <%
        	if (session.getAttribute("aluno") == null && session.getAttribute("adm") == null) {
                //caso a pessoa não esteja logada
                response.sendRedirect("loginAluno.jsp");

            } else if(session.getAttribute("adm") != null) {
                //caso a pessoa que esteja logada seja um aluno
                response.sendRedirect("UserHomeAdm.do");

            } else {
                  Aluno aluno = (Aluno) session.getAttribute("aluno"); 
                  
                  if(aluno.getStatus().equals("INATIVO")) {
                    //caso o aluno que esteja logado esteja ativo, ele pode criar solicitacoes novas
                    response.sendRedirect("UserHomeAluno.do");
                  } else {
        %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="utf-8" />
                <link rel="stylesheet" type="text/css" href="css/cadastroAluno.css" media="screen" />
                <script src="js/script.js" defer></script>
                <title>Criar Solicitação</title>
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
                    <p class="title">Criar Solicitação</p>
                    <form action="CadastroSolicitacoes.do" method="POST" class="form" enctype="multipart/form-data">
                        <div class="input-form">
                            <div class="box ">
                                <select name="solicitacao">
                                    <option value="Contrato de estagio">Contrato de Estágio</option> 
                                    <option value="Bilhete da SPTrans">Bilhete da SPTrans</option>
                                    <option value="Entrega de atividades complementares">Entrega de Atividades Complementares</option>
                                    <option value="Mudanca de horario">Mudança de Horário</option>
                                </select>
                            </div>
                            <div class="box inputFile">
                                <span id="textFile">Escolha um arquivo</span>
                                <span id="textMore">+</span>
                                <input type="file" size="50" name="arquivo" id="arquivo" accept=".pdf" />
                            </div>
                        </div>
                        <div class="input-form2 ">
                            <label>
                                Escreva uma descrição
                            </label>
                            <textarea rows="3" class="grande" type="text" name="descricao" maxlength="100"></textarea>
                        </div>
                        <button type="submit ">Enviar</button>
                    </form>
                </section>
            </body>

            </html>
            <% }}%>