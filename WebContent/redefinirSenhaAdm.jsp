<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="css/loginCadastro.css" media="screen" />
        <script src="js/script.js" defer></script>
        <title>Redefinir senha</title>
    </head>

    <%     
        if (session.getAttribute("aluno") != null || session.getAttribute("adm") != null) {
            //caso ja esteja logado
            response.sendRedirect("UserHomeAluno.do");
        }

        String email = request.getParameter("email");
         
    %>

        <body>
            <header>
                <div class="container main-header">
                    <span class="logo">MO<span class="logo-pink">O</span>N</span>
                </div>
            </header>
            <section class="content">
                <form id="redefinirSenhaAdm" action="RedefinirSenhaAdm.do" method="POST" class="form">
                    <input type="hidden" name="email" value="<%=email%>">
                    <input id="passRedefinirAdm" type="password" name="password" maxlength="30" placeholder="Nova senha" required>
                    <input id="confRedefinirAdm" type="password" name="passwordConfirm" maxlength="30" placeholder="Confirme a nova senha" required>
                    <button type="submit" onClick="return redefinirSenhaAdm()">Redefinir</button>
                    <p id="errorPassword">oi</p>
                </form>
            </section>
        </body>

    </html>