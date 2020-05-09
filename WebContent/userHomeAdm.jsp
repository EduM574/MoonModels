<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@page import="model.Administrador" %>
        <%@page import="model.Solicitacao" %>
            <%@page import="java.util.ArrayList" %>
                <%
            if (session.getAttribute("aluno") == null && session.getAttribute("adm") == null) {
                //caso a pessoa não esteja logada
                response.sendRedirect("loginAdm.jsp");

            } else if(session.getAttribute("aluno") != null) {
                //caso a pessoa que esteja logada seja um aluno
                response.sendRedirect("userHomeAluno.jsp");

            } else {
                Administrador adm = (Administrador) session.getAttribute("adm");
                ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) request.getAttribute("solicitacoesAdm");
        		
                System.out.println(solicitacoes);
        %>
                    <!DOCTYPE html>
                    <html lang="en">

                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <link rel="stylesheet" href="css/userHome.css">
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

                            <div class="content">
                                <section class="info">
                                    <div class="info-card">
                                        <p class="info-title">Total de Solicitações</p>
                                        <p class="info-content">15</p>
                                    </div>
                                    <div class="info-card">
                                        <p class="info-title">Solicitações concluídas</p>
                                        <p class="info-content">5</p>
                                    </div>
                                    <div class="info-card">
                                        <p class="info-title">Solicitações deferidas</p>
                                        <p class="info-content">2</p>
                                    </div>
                                </section>

                                <section>
                                    <h2 class="titulo-solicitacao">Em andamento</h2>
                                    <div class="s-card-color">
                                        <div class="s-card-content">
                                            <div class="s-row">
                                                <div>
                                                    <span class="s-card-subtitle">Nome:</span>
                                                    <span>Cartão do estudante</span>
                                                </div>
                                                <div class="s-status-ativo"></div>
                                            </div>
                                            <div class="s-row">
                                                <div>
                                                    <span class="s-card-subtitle">Status:</span>
                                                    <span>1/4</span>
                                                </div>
                                                <div>
                                                    <span class="s-card-subtitle">Código:</span>
                                                    <span>1470</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>

                                <section>
                                    <h2 class="titulo-solicitacao">Deferidas</h2>
                                    <div class="s-card-color">
                                        <div class="s-card-content">
                                            <div class="s-row">
                                                <div>
                                                    <span class="s-card-subtitle">Nome:</span>
                                                    <span>Cartão do estudante</span>
                                                </div>
                                                <div class="s-status-ativo"></div>
                                            </div>
                                            <div class="s-row">
                                                <div>
                                                    <span class="s-card-subtitle">Status:</span>
                                                    <span>1/4</span>
                                                </div>
                                                <div>
                                                    <span class="s-card-subtitle">Código:</span>
                                                    <span>1470</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>

                                <section>
                                    <h2 class="titulo-solicitacao">Indeferido</h2>
                                    <div class="s-card-color">
                                        <div class="s-card-content">
                                            <div class="s-row">
                                                <div>
                                                    <span class="s-card-subtitle">Nome:</span>
                                                    <span>Cartão do estudante</span>
                                                </div>
                                                <div class="s-status-ativo"></div>
                                            </div>
                                            <div class="s-row">
                                                <div>
                                                    <span class="s-card-subtitle">Status:</span>
                                                    <span>1/4</span>
                                                </div>
                                                <div>
                                                    <span class="s-card-subtitle">Código:</span>
                                                    <span>1470</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>

                            <section class="hist-solicit" id="detalhe-solicitacao">
                                <div class="title-wrapper top-content">
                                    <div>
                                        <span class="s-hist-title">Nome:</span>
                                        <span class="s-hist-subtitle">{{Nome da solicitação}}</span>
                                    </div>
                                    <button class="s-hist-send-comment-btn">
                                <img src="./img/download.png" alt="enviar">
                            </button>
                                </div>
                                <div class="title-wrapper">
                                    <span class="s-hist-title">Código:</span>
                                    <span class="s-hist-subtitle">{{CÃ³digo}}</span>
                                </div>
                                <div class="s-hist-sub-info title-wrapper">
                                    <div>
                                        <span class="s-hist-title">Abertura:</span>
                                        <span class="s-hist-subtitle">{{data}}</span>
                                    </div>

                                    <div>
                                        <span class="s-hist-title">Prazo:</span>
                                        <span class="s-hist-subtitle">{{prazo}}</span>
                                    </div>
                                </div>
                                <div class="s-hist-step-wrapper">
                                    <div class="s-hist-step">
                                        <div class="step-dot"></div>
                                        <p class="step-text">{{Parte 1}}</p>
                                    </div>
                                    <div class="s-hist-step">
                                        <div class="step-dot"></div>
                                        <p class="step-text">{{Parte 2}}</p>
                                    </div>
                                    <div class="s-hist-step">
                                        <div class="step-dot"></div>
                                        <p class="step-text">{{Parte 3}}</p>
                                    </div>
                                    <div class="s-hist-step">
                                        <div class="step-dot"></div>
                                        <p class="step-text">{{Parte 4}}</p>
                                    </div>
                                    <div class="s-hist-step">
                                        <div class="step-dot"></div>
                                        <p class="step-text">{{Parte 5}}</p>
                                    </div>
                                </div>
                                <span class="s-hist-title">ComentÃ¡rios</span>
                                <div class="caixa-mensagem">
                                    <div>
                                        <span class="s-comment-user">{{nome do usuário}}:</span>
                                        <span class="s-comment-content">{{comentário}}</span>
                                    </div>
                                </div>
                                <div>
                                    <form action="Comentario.do" method="POST" class="form">
                                        <input type="text" class="s-hist-input" name="texto">
                                        <button class="s-hist-send-comment-btn" type="submit">
                                    <img src="./img/forward.png" alt="enviar">
                                </button>
                                    </form>
                                </div>
                            </section>
                    </body>

                    </html>

                    <%
                }
            %> } %>