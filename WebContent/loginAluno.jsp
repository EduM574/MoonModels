<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="css/loginCadastro.css" media="screen" />
        <title>Login aluno</title>
    </head>

    <body>
        <header>
            <div class="container main-header">
                <span class="logo">MO<span class="logo-pink">O</span>N</span>
            </div>
        </header>
        <section class="content">
            <form action="LoginAluno.do" method="post" class="form">
                <input type="number" name="ra" placeholder="RA">
                <input type="password" name="password" maxlength="15" placeholder="Senha">
                <button type="submit">Fazer login</button>
            </form>
            <div class="redirect">
                <a href="esqueciSenhaAluno.html" class="new-password">Esqueci a senha</a>
                <a href="loginAdm.jsp" class="login-adm">Fazer login como administrador</a>
            </div>
        </section>
    </body>

    </html>