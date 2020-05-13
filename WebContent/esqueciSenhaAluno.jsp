<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="css/loginCadastro.css" media="screen" />
        <title>Login aluno</title>
    </head>

    <%     
        if (session.getAttribute("aluno") != null || session.getAttribute("adm") != null) {
            //caso ja esteja logado
            response.sendRedirect("UserHomeAluno.do");
        }
        
        String erro = (String) request.getAttribute("erro");
         
    %>

        <body>
            <header>
                <div class="container main-header">
                    <span class="logo">MO<span class="logo-pink">O</span>N</span>
                </div>
            </header>
            <section class="content">
                <form action="EsqueciSenhaAluno.do" method="POST" class="form">
                    <input type="number" name="ra" placeholder="Insira seu RA">
                    <input type="email" name="email" placeholder="Insira seu e-mail">
                    <p class="erro">
                        <% if(erro != null) out.print(erro); %>
                    </p>
                    <button type="submit">Enviar</button>
                </form>
                <div class="redirect">
                    <a href="loginAluno.jsp" class="login-adm">Voltar ao login do aluno</a>
                    <a href="loginAdm.jsp" class="login-adm">Voltar ao login do administrador</a>
                </div>
            </section>
        </body>

    </html>