<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="css/loginCadastro.css" media="screen" />
        <title>Redefinir senha</title>
    </head>

    <%     
        if (session.getAttribute("aluno") != null || session.getAttribute("adm") != null) {
            //caso ja esteja logado
            response.sendRedirect("UserHomeAluno.do");
        }

        String ra = request.getParameter("ra");
         
    %>

        <body>
            <header>
                <div class="container main-header">
                    <span class="logo">MO<span class="logo-pink">O</span>N</span>
                </div>
            </header>
            <section class="content">
                <form action="RedefinirSenhaAluno.do" method="POST" class="form">
                    <input type="hidden" name="ra" value="<%=ra%>">
                    <input type="password" name="password" maxlength="30" placeholder="Nova senha" required>
                    <input type="password" name="passwordConfirm" maxlength="30" placeholder="Confirme a nova senha" required>
                    <button type="submit">Redefinir</button>
                </form>
            </section>
        </body>

    </html>