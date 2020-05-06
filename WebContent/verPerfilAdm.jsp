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

            String setor = "";
            
            if(adm.getSetor().getIdSetor() == 1) {
                setor = "Transporte escolar";
                
            } else if(adm.getSetor().getIdSetor() == 2) {
            	setor = "Gestão de estágio";
            	
            } else if(adm.getSetor().getIdSetor() == 3) {
            	setor = "Atividades curriculares";
            	
            } else if(adm.getSetor().getIdSetor() == 4) {
            	setor = "MASTER";
            }
            
            String newCpf = adm.getCpf().substring(0, 3) 
              		+ "." + adm.getCpf().substring(3, 6)
              		+ "." + adm.getCpf().substring(6, 9)
              		+ "-" + adm.getCpf().substring(9, 11);
    		
    %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" href="css/visualizarDados.css">
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
                        <div class="box">
                            <div class="group">
                                <span class="title">Nome:</span>
                                <span class="data"><%= adm.getNome()%></span>
                            </div>
                            <div class="group">
                                <span class="title">Sobrenome:</span>
                                <span class="data"><%= adm.getSobrenome()%></span>
                            </div>
                            <div class="group">
                                <span class="title">CPF:</span>
                                <span class="data"><%=newCpf%></span>
                            </div>
                        </div>
                        <div class="box">
                            <div class="group">
                                <span class="title">E-mail:</span>
                                <span class="data"><%= adm.getEmail()%></span>
                            </div>
                            <div class="group">
                                <span class="title">Setor:</span>
                                <span class="data"><%=setor%></span>
                            </div>
                        </div>
                    </section>
            </body>

            </html>

            <%
                    }
                %>