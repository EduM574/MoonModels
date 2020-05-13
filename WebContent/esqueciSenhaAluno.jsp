<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="css/senhas.css" media="screen" />
        <title>Editar senha</title>
    </head>

    <body>
        <!-- <header>
            <div class="container main-header">
                <span class="logo">MO<span class="logo-pink">O</span>N</span>
                <div class="username-wrapper">
                    <span>User Name</span>
                    <div class="logout-wrapper">
                        <img src="img/logout.png" alt="logout">
                    </div>
                </div>
            </div>
        </header> -->
        <section class="content">
            <p class="title">Editar senha</p>
            <form action="EsqueciSenhaAluno.do" method="POST" class="form">
                <div class="input-form">
                    <div class="box">
                        <input type="number" name="ra" placeholder="RA">
                        <input type="password" name="password" maxlength="15" placeholder="Nova senha">
                        <input type="password" name="password" maxlength="15" placeholder="Confirme a nova senha">
                    </div>
                </div>
                <div class="buttons">
                    <button type="submit" class="buttons-b">Editar</button>
                </div>
            </form>
        </section>
    </body>

    </html>