<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.GregorianCalendar" %>
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
                  
                  String data = aluno.getData_nascimento().get(GregorianCalendar.DAY_OF_MONTH) 
                		  + "/" + aluno.getData_nascimento().get(GregorianCalendar.MONTH)
                		  + "/" + aluno.getData_nascimento().get(GregorianCalendar.YEAR);
                  
                  String newCpf = aluno.getCpf().substring(0, 3) 
                    		+ "." + aluno.getCpf().substring(3, 6)
                    		+ "." + aluno.getCpf().substring(6, 9)
                    		+ "-" + aluno.getCpf().substring(9, 11);
                  
        %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="utf-8" />
                    <link rel="stylesheet" type="text/css" href="css/visualizarDados.css" media="screen" />
                    <title>Ver Perfil</title>
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
                        <a href="userHomeAluno.jsp">
                            <div class="menu-icon-wrapper">
                                <img src="./img/home.png" alt="Home">
                            </div>
                        </a>
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
                        <div class="box">
                            <div class="group">
                                <span class="title">Nome:</span>
                                <span class="data"><%= aluno.getNome()%></span>
                            </div>
                            <div class="group">
                                <span class="title">Sobrenome:</span>
                                <span class="data"><%= aluno.getSobrenome()%></span>
                            </div>
                            <div class="group">
                                <span class="title">CPF:</span>
                                <span class="data"><%=newCpf%></span>
                            </div>
                            <div class="group">
                                <span class="title">Data de Nascimento:</span>
                                <span class="data"><%=data%></span>
                            </div>
                            <div class="group">
                                <span class="title">E-mail:</span>
                                <span class="data"><%= aluno.getEmail()%></span>
                            </div>
                        </div>
                        <div class="box">
                            <div class="group">
                                <span class="title">RA:</span>
                                <span class="data"><%= aluno.getRa()%></span>
                            </div>
                            <div class="group">
                                <span class="title">Curso:</span>
                                <span class="data"><%= aluno.getCurso()%></span>
                            </div>
                            <div class="group">
                                <span class="title">Turno:</span>
                                <span class="data"><%= aluno.getTurno()%></span>
                            </div>
                            <div class="group">
                                <span class="title">Unidade:</span>
                                <span class="data"><%= aluno.getUnidade()%></span>
                            </div>
                            <div class="group">
                                <span class="title">Semestre:</span>
                                <span class="data"><%= aluno.getSemestre()%>º semestre</span>
                            </div>
                            <div class="group">
                                <span class="title">Status:</span>
                                <span class="data"><%= aluno.getStatus()%></span>
                            </div>
                        </div>
                    </section>
                </body>

                </html>
                <% }%>