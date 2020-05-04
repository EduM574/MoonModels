<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" type="text/css" href="css/cadastroAluno.css" media="screen" />
    <title>Criar Solicitação</title>
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
        <p class="title">Criar Solicitação</p>
        <form action="CadastroSolicitacoes.do" method="POST" enctype="multipart/form-data" class=" form ">
            <div class="input-form ">
                <div class="box ">
                    <select name="solicitacao " class=" ">
                        <option value="Contrato de estágio ">Contrato de Estágio</option> 
                        <option value="Bilhete da SPTrans ">Bilhete da SPTrans</option>
                        <option value="Entrega de atividades complementares ">Entrega de Atividades Complementares</option>
                        <option value="Mudança de horário ">Mudança de Horário</option>
                    </select>
                </div>
                <div class="box inputFile">
                    <span>Escolha um arquivo</span>
                    <span>+</span>
                    <input type="file" name="arquivo" id="arquivo" accept=".pdf" />
                </div>
            </div>
            <div class="input-form2 ">
                <label>
                    Escreva uma descrição
                </label>
                <textarea rows="3" class="grande" type="text" name="descricao"></textarea>
            </div>
            <button type="submit ">Enviar</button>
        </form>
    </section>
</body>

</html>