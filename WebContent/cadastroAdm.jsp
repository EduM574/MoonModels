<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.Administrador" %>
        <%
            if (session.getAttribute("aluno") == null && session.getAttribute("adm") == null) {
                //caso a pessoa não esteja logada
                response.sendRedirect("loginAdm.jsp");
            
            } else if(session.getAttribute("aluno") != null) {
                //caso a pessoa que esteja logada seja um aluno
                response.sendRedirect("UserHomeAluno.do");
            
            } else {
                Administrador adm = (Administrador) session.getAttribute("adm");
                String erro = (String) request.getAttribute("erro");
        
        %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="utf-8" />
                <link rel="stylesheet" type="text/css" href="css/cadastroAluno.css" media="screen" />
                <title>Cadastro Administrador</title>
                <script src="js/cpf.js" defer></script>
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
                            <button type="submit">
                                <img src="./img/home.png" alt="Home">
                            </button>
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

                    <section class="content">
                        <p class="title">Cadastrar um Administrador</p>
                        <form action="CadastraAdm.do" method="POST" class="form">
                            <div class="input-form">
                                <div class="box">
                                    <input type="text" name="nome" placeholder="Nome" maxlength="35" required>
                                    <input type="text" name="sobrenome" placeholder="Sobrenome" maxlength="35" required>
                                </div>
                                <div class="box">
                                    <input type="email" name="email" placeholder="E-mail" maxlength="80" required>
                                    <div class="mini-box">
                                        <input type="text" name="cpf" placeholder="CPF" class="normal" onkeydown="javascript: fMasc( this, mCPF );" maxlength = "14" required>
                                        <select name="setor" class="normal">
                                            <option value="1">Transporte escolar</option>
                                            <option value="2">Gestão de estagio</option>
                                            <option value="3">Atividades curriculares</option>
                                            <option value="4">MASTER</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="align-erro">
                                <p>
                                    <% if(erro != null) out.print(erro); %>
                                </p>
                                <button type="submit">Cadastrar</button>
                            </div>
                        </form>
                    </section>
            </body>

            </html>
            <%
                }
            %>