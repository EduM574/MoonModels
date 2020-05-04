<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="css/senhas.css" media="screen" />
        <title>Visualizar</title>
    </head>

    <body>
        <header>
            <div class="container main-header">
                <span class="logo">MO<span class="logo-pink">O</span>N</span>
                <div class="username-wrapper">
                    <span>User Name</span>
                    <div class="logout-wrapper">
                        <img src="img/logout.png" alt="logout">
                    </div>
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
                    <img src="./img/newsfeed.png" alt="Solicitações">
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
        <section class="content">
            <p class="title">Visualizar</p>
            <div class="editar-user">
                <a href="listaADM.jsp">Visualizar ADMs</a>
                <a href="listaAlunos.jsp">Visualizar Alunos</a>
            </div>
        </section>
    </body>

    </html>