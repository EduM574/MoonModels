<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="css/loginCadastro.css" media="screen" />
        <title>Login administrador</title>
    </head>

    <% 
        if (!session.isNew() && (session.getAttribute("aluno") != null || session.getAttribute("adm") != null)) {
            //caso ja esteja logado
            response.sendRedirect("UserHome.do");
        }
    %>

        <body>
            <header>
                <div class="container main-header">
                    <span class="logo">MO<span class="logo-pink">O</span>N</span>
                </div>
            </header>
            <section class="content">
                <form action="LoginAdmin.do" method="post" class="form">
                    <input type="email" name="email" placeholder="E-mail">
                    <input type="password" name="password" placeholder="Senha">
                    <button type="submit">Fazer login</button>
                </form>
                <div class="redirect">
                    <a href="esqueciSenhaAdm.html" class="new-password">Esqueci a senha</a>
                    <a href="loginAluno.jsp" class="login-adm">Fazer login como aluno</a>
                </div>
            </section>
        </body>

    </html>