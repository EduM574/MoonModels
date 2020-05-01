<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@page import="model.Aluno" %>
        <%
            if (session.isNew()) {
                //caso a pessoa não esteja logada
                response.sendRedirect("loginAluno.jsp");
            } else {
            	  Aluno aluno = (Aluno) session.getAttribute("aluno");        
        %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" href="css/userHome.css">
                <script src="chatbot/frontend/script.js" defer></script>
                <title>Solicitações</title>
            </head>

            <body>
                <header>
                    <div class="container main-header">
                        <span class="logo">MO<span class="logo-pink">O</span>N</span>
                        <div class="username-wrapper">
                            <span>
                                <%= aluno.getNome() %>
                            </span>
                            <form action="logout.do" mothod="post" class="logout-wrapper">
                                <button type="submit">
			                        <img src="img/logout.png" alt="logout">
			                    </button>
                            </form>
                        </div>
                    </div>
                </header>
                <nav>
                    <a href="#">
                        <div class="menu-icon-wrapper">
                            <img src="./img/home.png" alt="Home">
                        </div>
                    </a>
                    <a href="#">
                        <div class="menu-icon-wrapper">
                            <img src="./img/newsfeed.png" alt="SolicitaÃ§Ãµes">
                        </div>
                    </a>
                    <a href="#">
                        <div class="menu-icon-wrapper">
                            <img src="./img/add-friend.png" alt="Adicionar usuário">
                        </div>
                    </a>
                    <a href="#">
                        <div class="menu-icon-wrapper">
                            <img src="./img/user.png" alt="Editar perfil">
                        </div>
                    </a>
                </nav>

                <div class="chatboat-icon">
                    <button id="button-chatboat-icon">
            <img src="./img/comment.png" alt="Icone do chatboat">
        </button>
                </div>

                <div class="content">
                    <section class="info">
                        <div class="info-card">
                            <p class="info-title">Total de solicitações</p>
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
                        <span class="s-hist-subtitle">{{Código}}</span>
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

                <section class="chatboat" id="tela-chatboat">
                    <div class="chat-header">
                        <img src="./img/lunaIcon.png" alt="Icone de lua">
                        <span class="title-chat">Luna</span>
                    </div>
                    <div class="chat-content" id="chat-content">

                    </div>
                    <div class="chat-footer">
                        <input type="text" id="textInput">
                    </div>
                </section>
            </body>

            </html>
            <% }%>