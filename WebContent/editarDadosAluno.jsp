<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.Administrador" %>
        <%@page import="model.Aluno" %>
            <%@page import="java.util.GregorianCalendar" %>
                <%
            if (session.getAttribute("aluno") == null && session.getAttribute("adm") == null) {
                //caso a pessoa não esteja logada
                response.sendRedirect("loginAdm.jsp");

            } else if(session.getAttribute("aluno") != null) {
                //caso a pessoa que esteja logada seja um aluno
                response.sendRedirect("userHomeAluno.jsp");

            } else {
                Administrador adm = (Administrador) session.getAttribute("adm");
                Aluno alunoEdicao = (Aluno) request.getAttribute("exibeAluno");
                
                String dia, mes, date;
                
                if(alunoEdicao.getData_nascimento().get(GregorianCalendar.MONTH) < 10) {
                	mes = "-0" + alunoEdicao.getData_nascimento().get(GregorianCalendar.MONTH);
                } else {
                	mes = "-" + alunoEdicao.getData_nascimento().get(GregorianCalendar.MONTH);
                }
                
                if(alunoEdicao.getData_nascimento().get(GregorianCalendar.DAY_OF_MONTH) < 10) {
                	dia = "-0" + alunoEdicao.getData_nascimento().get(GregorianCalendar.DAY_OF_MONTH);
                } else {
                	dia = "-" + alunoEdicao.getData_nascimento().get(GregorianCalendar.DAY_OF_MONTH);
                }
                
                date = alunoEdicao.getData_nascimento().get(GregorianCalendar.YEAR) 
              		  + mes + dia;        
        %>
                    <!DOCTYPE html>
                    <html>

                    <head>
                        <meta charset="utf-8" />
                        <link rel="stylesheet" type="text/css" href="css/cadastroAluno.css" media="screen" />
                        <title>Editar Aluno</title>
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
                                <p class="title">Editar um aluno</p>
                                <span class="data-ra"><%=alunoEdicao.getRa() %></span>
                                <form action="EditarAlunoDados.do" method="POST" class="form">
                                    <div class="input-form">
                                        <div class="box">
                                            <input type="text" name="nome" placeholder="Nome" value="<%=alunoEdicao.getNome()%>">
                                            <input type="text" name="sobrenome" placeholder="Sobrenome" value="<%=alunoEdicao.getSobrenome()%>">
                                            <div class="mini-box">
                                                <input type="text" name="cpf" placeholder="CPF" value="<%=alunoEdicao.getCpf()%>" class="normal">
                                                <input type="date" name="nascimento" placeholder="Data de nascimento" class="normal" value="<%=date%>">
                                            </div>
                                            <input type="password" name="password" maxlength="15" placeholder="Nova senha">
                                        </div>
                                        <div class="box">
                                            <input type="email" name="email" placeholder="E-mail" value="<%=alunoEdicao.getEmail()%>">
                                            <div class="mini-box">
                                                <select name="curso" class="maior">
                                            <option value="CCP" selected>Ciência da Computação</option>
                                            <option value="ECP">Engenharia da Computação</option>
                                            <option value="ADS">Análise e Desenvolvimento de Sistemas</option>
                                        </select>
                                                <select name="semestre" class="menor">
                                            <option value="1" selected>1º</option>
                                            <option value="2">2º</option>
                                            <option value="3">3º</option>
                                            <option value="4">4º</option>
                                            <option value="5">5º</option>
                                            <option value="6">6º</option>
                                            <option value="7">7º</option>
                                            <option value="8">8º</option>
                                        </select>
                                            </div>
                                            <div class="mini-box">
                                                <select name="unidade" class="normal">
                                            <option value="paulista" selected>Paulista</option>
                                            <option value="mooca">Mooca</option>
                                            <option value="santana">Santana</option>
                                        </select>
                                                <select name="turno" class="normal">
                                            <option value="matutino" selected>Matutino</option>
                                            <option value="vespetino">Vespetino</option>
                                            <option value="noturno">Noturno</option>
                                        </select>
                                            </div>
                                            <div class="mini-box">
                                                <select name="status" class="normal">
                                            <option value="ATIVO" selected>ATIVO</option>
                                            <option value="INATIVO">INATIVO</option>
                                        </select>
                                            </div>
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