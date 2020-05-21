<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@page import="model.Aluno" %>
        <%@page import="model.Solicitacao" %>
            <%@page import="model.Comentario" %>
                <%@page import="java.util.ArrayList" %>
                    <%@page import="java.util.GregorianCalendar" %>
                        <%
                if (session.getAttribute("aluno") == null && session.getAttribute("adm") == null) {
                    //caso a pessoa não esteja logada
                    response.sendRedirect("loginAluno.jsp");
                
                } else if(session.getAttribute("adm") != null) {
                    //caso a pessoa que esteja logada seja um aluno
                    response.sendRedirect("UserHomeAdm.do");

                } else {
              	  Aluno aluno = (Aluno) session.getAttribute("aluno");
              	  ArrayList<Solicitacao> solicitacaoDeferida = (ArrayList<Solicitacao>) session.getAttribute("solicitacaoDeferida");
              	  ArrayList<Solicitacao> solicitacaoIndeferida = (ArrayList<Solicitacao>) session.getAttribute("solicitacaoIndeferida");
              	  ArrayList<Solicitacao> solicitacaoAndamento = (ArrayList<Solicitacao>) session.getAttribute("solicitacaoAndamento");
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
        			listaStatus.add("Contrato recebido e enviado para analise");

        		} else if (solicitacao.getNome().equals("Bilhete da SPTrans")) {
        			listaStatus.add("Dados enviados para SPTrans");
        			listaStatus.add("Cartao em producao");
        			listaStatus.add("Cartao pronto para retirada na universidade");

        		} else if (solicitacao.getNome().equals("Entrega de atividades complementares")) {
        			listaStatus.add("Comprovate em analise");

        		} else if (solicitacao.getNome().equals("Mudanca de horario")) {
        			listaStatus.add("Solicitacao em analise");
        		}
                
                listaStatus.add("Deferida");
                listaStatus.add("Indeferida");
                      
        %>
                            <!DOCTYPE html>
                            <html lang="en">

                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <link rel="stylesheet" href="css/userHome.css">
                                <script src="chatbot/frontend/script.js" defer></script>
                                <script src="js/script.js" defer></script>
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

                                <div class="content">
                                    <section class="info">
                                        <div class="info-card">
                                            <p class="info-title">Total de solicitações</p>
                                            <p class="info-content">
                                                <%= solicitacaoDeferida.size() + solicitacaoIndeferida.size() + solicitacaoAndamento.size()%>
                                            </p>
                                        </div>
                                        <div class="info-card">
                                            <p class="info-title">Solicitações Deferidas</p>
                                            <p class="info-content">
                                                <%= solicitacaoDeferida.size() %>
                                            </p>
                                        </div>
                                        <div class="info-card">
                                            <p class="info-title">Solicitações Indeferidas</p>
                                            <p class="info-content">
                                                <%= solicitacaoIndeferida.size() %>
                                            </p>
                                        </div>
                                    </section>

                                    <section>
                                        <h2 class="titulo-solicitacao">Em andamento</h2>
                                        <div class="cards-adm">
                                            <%
                               int totalSolicitacoes = 0; for(Solicitacao s : solicitacaoAndamento) {
                           %>
                                                <form action="UserHomeSolicitacao.do" method="POST" class="s-card-color-adm">
                                                    <input type="hidden" name="id-solicitacao" value="<%=s.getIdSolicitacao()%>">
                                                    <button type="submit" class="s-card-content-adm">
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
                                   </button>
                                                </form>
                                                <%
                           	}
                           %>
                                        </div>
                                    </section>

                                    <section>
                                        <h2 class="titulo-solicitacao">Deferidas</h2>
                                        <div class="cards-adm">

                                            <%
                                for(Solicitacao s : solicitacaoDeferida) {
                            %>
                                                <form action="UserHomeSolicitacao.do" method="POST" class="s-card-color-adm">
                                                    <input type="hidden" name="id-solicitacao" value="<%=s.getIdSolicitacao()%>">
                                                    <button type="submit" class="s-card-content-adm">
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
                                    </button>
                                                </form>
                                                <%
                                }
                            %>
                                        </div>
                                    </section>

                                    <section>
                                        <h2 class="titulo-solicitacao">Indeferido</h2>
                                        <div class="cards-adm">

                                            <%
                            for(Solicitacao s : solicitacaoIndeferida) {
                        %>
                                                <form action="UserHomeSolicitacao.do" method="POST" class="s-card-color-adm">
                                                    <input type="hidden" name="id-solicitacao" value="<%=s.getIdSolicitacao()%>">
                                                    <button type="submit" class="s-card-content-adm">
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
                                </button>
                                                </form>
                                                <%
                            }
                        %>
                                        </div>
                                    </section>
                                </div>

                                <section class="hist-solicit" id="detalhe-solicitacao">
                                    <div class="title-wrapper top-content">
                                        <div>
                                            <span class="s-hist-title">Nome:</span>
                                            <span class="s-hist-subtitle"><%=solicitacao.getNome() %></span>
                                        </div>
                                        <div class="s-hist-buttons-top-adm">
                                            <% if(solicitacao.getAnexo() != null){ %>
                                                <a href="./anexoSolicitacoes/solicitacao<%=solicitacao.getIdSolicitacao()%>.pdf" class="s-hist-send-comment-btn" download="solicitacao<%=solicitacao.getIdSolicitacao()%>.pdf">
                                                    <img src="./img/download.png" alt="enviar">
                                                </a>
                                                <%}%>
                                                    <form action="UserHomeAluno.do" method="GET">
                                                        <button class="s-hist-close-comment-btn">X</button>
                                                    </form>
                                        </div>
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
                                                <p class="step-text">
                                                    <%=listaStatus.get(i)%>
                                                </p>
                                            </div>
                                            <% i++;} %>
                                                <div class="s-hist-step">
                                                    <div class="step-dot"></div>
                                                    <p class="step-text">
                                                        <%=solicitacao.getStatus()%>
                                                    </p>
                                                </div>
                                    </div>

                                    <span class="s-hist-title">Comentários</span>
                                    <div class="caixa-mensagem">
                                        <%
                        for(Comentario c : solicitacao.getComentarios()){
                            String dia, mes, hora, minuto, segundo, dateComentario;
                            
                            if(c.getDataHora().get(GregorianCalendar.MONTH) < 10) {
                                mes = "0" + c.getDataHora().get(GregorianCalendar.MONTH) + "/";
                            } else {
                                mes = c.getDataHora().get(GregorianCalendar.MONTH)  + "/";
                            }
                            
                            if(c.getDataHora().get(GregorianCalendar.DAY_OF_MONTH) < 10) {
                                dia = "0" + c.getDataHora().get(GregorianCalendar.DAY_OF_MONTH) + "/";
                            } else {
                                dia = c.getDataHora().get(GregorianCalendar.DAY_OF_MONTH) + "/";
                            }
                            
                            if(c.getDataHora().get(GregorianCalendar.HOUR_OF_DAY) < 10) {
                                hora = "0" + c.getDataHora().get(GregorianCalendar.HOUR_OF_DAY) + ":";
                            } else {
                                hora = c.getDataHora().get(GregorianCalendar.HOUR_OF_DAY) + ":";
                            }
                            
                            if(c.getDataHora().get(GregorianCalendar.MINUTE) < 10) {
                                minuto = "0" + c.getDataHora().get(GregorianCalendar.MINUTE) + ":";
                            } else {
                                minuto = c.getDataHora().get(GregorianCalendar.MINUTE) + ":";
                            }
                            
                            if(c.getDataHora().get(GregorianCalendar.SECOND) < 10) {
                                segundo = "0" + c.getDataHora().get(GregorianCalendar.SECOND);
                            } else {
                                segundo = c.getDataHora().get(GregorianCalendar.SECOND) + "";
                            }
                            
                            dateComentario = dia + mes + c.getDataHora().get(GregorianCalendar.YEAR) 
                                            + " " + hora + minuto + segundo;
                            
                            String nome = "";
                            if(c.getAdministrador() != null) {
                                nome = c.getAdministrador().getNome() + " " + c.getAdministrador().getSobrenome();
                            } else if(c.getAluno() != null) {
                                nome = c.getAluno().getNome() + " " + c.getAluno().getSobrenome();
                            }
                        
                    %>
                                            <div class="data-comment-adm">
                                                <div>
                                                    <span class="s-comment-user"><%=nome%>:</span>
                                                    <span class="s-comment-content"><%=c.getTexto() %></span>
                                                </div>
                                                <div class="s-comment-file">
                                                    <% if(c.getAnexo() != null){ %>
                                                        <a href="./anexoSolicitacoes/comentario<%=c.getIdComentario()%>.pdf" class="s-comment-file-btn" download="comentario<%=c.getIdComentario()%>.pdf">
                                                            <img src="./img/download.png" alt="enviar">
                                                        </a>
                                                        <%}%>
                                                            <span class="s-comment-date"><%=dateComentario%></span>
                                                </div>
                                            </div>
                                            <%}%>
                                    </div>
                                    <div>
                                        <form action="Comentario.do" method="POST" class="form form-comentario" enctype="multipart/form-data">
                                            <input type="hidden" name="id-solicitacao" value="<%=solicitacao.getIdSolicitacao()%>">
                                            <input type="text" class="s-hist-input" name="texto" maxlength="200" required>
                                            <div class="inputFile">
                                                <span class="s-hist-send-comment-btn"><img src="./img/file.png" alt="anexo"></span>
                                                <input type="file" size="50" name="arquivo" id="arquivo" accept=".pdf" />
                                            </div>
                                            <button class="s-hist-send-comment-btn" type="submit"><img src="./img/forward.png" alt="enviar"></button>
                                        </form>
                                    </div>
                                </section>
                            </body>

                            </html>

                            <%
    }
%>