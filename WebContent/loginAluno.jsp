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
                <form action="LoginAluno.do" method="post" class="form">
                    <input type="number" name="ra" placeholder="RA" maxlength="9" required>
                    <input type="password" name="password" maxlength="15" placeholder="Senha" maxlength="30" required>
                    <p class="erro">
                        <% if(erro != null) out.print(erro); %>
                    </p>
                    <button type="submit">Fazer login</button>
                </form>
                <div class="redirect">
                    <a href="esqueciSenhaAluno.jsp" class="new-password">Esqueci a senha</a>
                    <a href="loginAdm.jsp" class="login-adm">Sou administrador</a>
                </div>
            </section>
        </body>

    </html>