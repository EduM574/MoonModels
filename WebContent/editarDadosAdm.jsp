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
                response.sendRedirect("UserHomeAluno.do");

            } else {
            	Administrador adm = (Administrador) session.getAttribute("adm");
                Administrador admEdicao = (Administrador) request.getAttribute("exibeAdmin");
                String erro = (String) request.getAttribute("erro");

                System.out.println("Adm edição JSP: " + admEdicao);
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
                                <%= admEdicao.getNome() %>
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
                            <p class="title">Editar um administrador</p>
                            <span class="data-ra"><%= admEdicao.getEmail() %></span>
                            <form action="EditarAdmDados.do" method="POST" class="form">
                                <input type="hidden" name="email-admin" value="<%=admEdicao.getEmail() %>">
                                <div class="input-form">
                                    <div class="box">
                                        <input type="text" name="nome" maxlength="15" placeholder="Nome" value="<%= admEdicao.getNome()%>">
                                        <input type="text" name="sobrenome" maxlength="15" placeholder="Sobrenome" value="<%= admEdicao.getSobrenome()%>">
                                        <input type="password" name="password" maxlength="15" placeholder="Senha">
                                    </div>
                                    <div class="box">
                                        <input type="text" name="cpf" placeholder="CPF" value="<%= admEdicao.getCpf()%>">
                                        <select name="setor" class="altura-diferente">
                                    	<%
                                    		ArrayList<String> setor = new ArrayList<String>();
                                    		setor.add("Transporte escolar");
                                    		setor.add("Gestão de estagio");
                                    		setor.add("Atividades curriculares");
                                    		setor.add("MASTER");
                                    		
                                    		for(int i = 0; i < 4; i++) {
                                    			String s = setor.get(i);
                                    			
                                    			if (i+1 == admEdicao.getSetor().getIdSetor()){
                                    				out.print("<option value='"+ i+1 +"' selected>"+s+"</option>");
                                    			} else {
                                    				out.print("<option value='"+ i+1 +"'>"+s+"</option>");
                                    			}
                                    		}
                                    	%>
                                        
                                    </select>
                                        <select name="status" class="altura-diferente">
                                        <%
                                        	ArrayList<String> status = new ArrayList<String>();
                                            status.add("ATIVO");
                                            status.add("INATIVO");
                                            
                                            for(String u : status) {
                                            	if(u.equals(admEdicao.getStatus())) {
                                            		out.print("<option value='"+u+"' selected>"+u+"</option>");		
                                            	} else {
                                            		out.print("<option value='"+u+"'>"+u+"</option>");
                                            	}
                                            }
                                            
                                        %>
	                                 </select>
                                    </div>
                                </div>
                                <div class="align-erro">
                                    <p>
                                        <% if(erro != null) out.print(erro); %>
                                    </p>
                                    <button type="submit">Editar</button>
                                </div>
                            </form>
                        </section>
                </body>

                </html>
                <%
                }
            %>