<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@page import="model.Administrador" %>
        <%@page import="model.Solicitacao" %>
            <%@page import="java.util.ArrayList" %>
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
                ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) session.getAttribute("solicitacoesAdm");
                Solicitacao solicitacao = (Solicitacao) request.getAttribute("solicitacao");
                
           		String diaSoli, mesSoli, dateSoli;
           		
           	    if(solicitacao.getDataAbertura().get(GregorianCalendar.MONTH) < 10) {
           	    	mesSoli = "/0" + solicitacao.getDataAbertura().get(GregorianCalendar.MONTH) + "/";
                } else {
                	mesSoli = "/" + solicitacao.getDataAbertura().get(GregorianCalendar.MONTH) + "/";
                }
                
                if(solicitacao.getDataAbertura().get(GregorianCalendar.DAY_OF_MONTH) < 10) {
                	diaSoli = "0" + solicitacao.getDataAbertura().get(GregorianCalendar.DAY_OF_MONTH);
                } else {
                	diaSoli = "" + solicitacao.getDataAbertura().get(GregorianCalendar.DAY_OF_MONTH);
                }
                
                dateSoli = diaSoli + mesSoli + solicitacao.getDataAbertura().get(GregorianCalendar.YEAR);
                
                ArrayList<String> listaStatus = new ArrayList<String>();
                listaStatus.add("Aberta");
                
                if (solicitacao.getNome().equals("Contrato de estagio")) {
        			listaStatus.add("Modelo enviado para o aluno");
        			listaStatus.add("Contrato recebido e enviado para análise");

        		} else if (solicitacao.getNome().equals("Bilhete da SPTrans")) {
        			listaStatus.add("Dados enviados para SPTrans");
        			listaStatus.add("Cartão em produção");
        			listaStatus.add("Cartão pronto para retirada na universidade");

        		} else if (solicitacao.getNome().equals("Entrega de atividades complementares")) {
        			listaStatus.add("Comprovate em análise");

        		} else if (solicitacao.getNome().equals("Mudanca de horario")) {
        			listaStatus.add("Solicitação em análise");
        		}
                
                listaStatus.add("Deferida");
                listaStatus.add("Indeferida");
           		
                // String dia, mes, hora, minuto, segundo, date;
                
                // if(solicitacao.getDataAbertura().get(GregorianCalendar.MONTH) < 10) {
                //     mes = "-0" + solicitacao.getDataAbertura().get(GregorianCalendar.MONTH);
                // } else {
                //     mes = "-" + solicitacao.getDataAbertura().get(GregorianCalendar.MONTH);
                // }
                
                // if(solicitacao.getDataAbertura().get(GregorianCalendar.DAY_OF_MONTH) < 10) {
                //     dia = "-0" + solicitacao.getDataAbertura().get(GregorianCalendar.DAY_OF_MONTH);
                // } else {
                //     dia = "-" + solicitacao.getDataAbertura().get(GregorianCalendar.DAY_OF_MONTH);
                // }
                
                // if(solicitacao.getDataAbertura().get(GregorianCalendar.HOUR_OF_DAY) < 10) {
                //     hora = "-0" + solicitacao.getDataAbertura().get(GregorianCalendar.HOUR_OF_DAY);
                // } else {
                //     hora = "-" + solicitacao.getDataAbertura().get(GregorianCalendar.HOUR_OF_DAY);
                // }
                
                // if(solicitacao.getDataAbertura().get(GregorianCalendar.MINUTE) < 10) {
                //     minuto = "-0" + solicitacao.getDataAbertura().get(GregorianCalendar.MINUTE);
                // } else {
                // 	minuto = "-" + solicitacao.getDataAbertura().get(GregorianCalendar.MINUTE);
                // }
                
                // if(solicitacao.getDataAbertura().get(GregorianCalendar.SECOND) < 10) {
                //     segundo = "-0" + solicitacao.getDataAbertura().get(GregorianCalendar.SECOND);
                // } else {
                // 	segundo = "-" + solicitacao.getDataAbertura().get(GregorianCalendar.SECOND);
                // }
                
                // date = solicitacao.getDataAbertura().get(GregorianCalendar.YEAR) 
                //     + mes + dia;
                
        %>
                        <!DOCTYPE html>
                        <html lang="en">

                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <link rel="stylesheet" href="css/userHome.css">
                            <title>Solicitação</title>
                        </head>

                        <body>
                            <header>
                                <div class="container main-header">
                                    <span class="logo">MO<span class="logo-pink">O</span>N</span>
                                    <div class="username-wrapper">
                                        <span><%= adm.getNome() %></span>
                                        <form action="Logout.do" method="post" class="logout-wrapper">
                                            <button type="submit"><img src="img/logout.png" alt="logout"></button>
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
                                        <button type="submit"><img src="./img/home.png" alt="Home"></button>
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
                                            <p class="info-title">Nº de Solicitações a serem resolvidas</p>
                                            <p class="info-content">
                                                <%=solicitacoes.size()%>
                                            </p>
                                        </div>
                                    </section>

                                    <section>
                                        <h2 class="titulo-solicitacao">Solicitações</h2>
                                        <div class="cards-adm">

                                            <% int totalSolicitacoes = 0; for(Solicitacao s : solicitacoes) {%>
                                                <div class="s-card-color-adm">
                                                    <div class="s-card-content">
                                                        <div class="s-row-adm">
                                                            <div>
                                                                <span class="s-card-subtitle">Nome:</span>
                                                                <span><%=s.getNome()%></span>
                                                            </div>
                                                            <div class="s-status-ativo"></div>
                                                        </div>
                                                        <div class="s-row-adm">
                                                        <div>
                                                            <span class="s-card-subtitle">Status:</span>
                                                            <span><%=s.getStatus()%></span>
                                                        </div>
                                                        <div class="status-card-adm">
                                                            <span class="s-card-subtitle">Código:</span>
                                                            <span><%=s.getIdSolicitacao()%></span>
                                                        </div>
                                                    </div>
                                                    </div>
                                                </div>
                                                <%}%>
                                        </div>
                                    </section>
                                </div>

                                <section class="hist-solicit" id="detalhe-solicitacao">
                                    <div class="title-wrapper top-content">
                                        <div>
                                            <span class="s-hist-title">Nome:</span>
                                            <span class="s-hist-subtitle"><%=solicitacao.getNome() %></span>
                                        </div>
                                        <button class="s-hist-send-comment-btn"><img src="./img/download.png" alt="enviar"></button>
                                    </div>
                                    <div class="title-wrapper">
                                        <span class="s-hist-title">Código:</span>
                                        <span class="s-hist-subtitle"><%=solicitacao.getIdSolicitacao()%></span>
                                    </div>
                                    <div class="s-hist-sub-info title-wrapper">
                                        <div>
                                            <span class="s-hist-title">Abertura:</span>
                                            <span class="s-hist-subtitle"><%=dateSoli %></span>
                                        </div>

                                        <div>
                                            <span class="s-hist-title">Prazo:</span>
                                            <span class="s-hist-subtitle"><%=solicitacao.getPrazo()%> dias úteis</span>
                                        </div>
                                    </div>
                                    <div class="s-hist-step-wrapper">
                                        <%
                                        	int i = 0;
                                        while(!listaStatus.get(i).equals(solicitacao.getStatus())) {
                                        %>
                                        <div class="s-hist-step">
                                            <div class="step-dot"></div>
                                            <p class="step-text"><%=listaStatus.get(i)%></p>
                                        </div>
                                        <% i++;} %>
                                        <div class="s-hist-step">
                                            <div class="step-dot"></div>
                                            <p class="step-text"><%=solicitacao.getStatus()%></p>
                                        </div>
                                    </div>
                                    <span class="s-hist-title">Comentários</span>
                                    <div class="caixa-mensagem">
                                        <div>
                                            <span class="s-comment-user">{{nome do usuário}}:</span>
                                            <span class="s-comment-content">{{comentário}}</span>
                                        </div>
                                    </div>
                                    <div>
                                        <form action="Comentario.do" method="POST" class="form">
                                            <input type="text" class="s-hist-input" name="texto">
                                            <button class="s-hist-send-comment-btn" type="submit"><img src="./img/forward.png" alt="enviar"></button>
                                        </form>
                                    </div>
                                </section>
                        </body>

                        </html>

                        <%
                		}
            		%>