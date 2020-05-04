<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<link rel="stylesheet" type="text/css" href="css/cadastroAluno.css" media="screen" />
		<title>Editar Administrador</title>
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
        	<p class="title">Editar um administrador</p>
            <form action="CadastroAluno.do" method="POST" class="form">
                <div class="input-form">
                    <div class="box">
                    	<input type="text" name="nome" maxlength= "15" placeholder="Nome">
						<input type="number" name="cpf" placeholder="CPF">
						<select name="setor" class="altura-diferente">
	                 		<option value="SUS" selected>Selecione um setor</option>
	                  	 	<option value="GDE" >Gestão de estágio</option>
	                    	<option value="TPE">Transporte escolar</option>
							<option value="ATC">Atividades curriculares</option>
						</select>
                    </div>
                    <div class="box">
                        <input type="text" name="sobrenome" maxlength= "15" placeholder="Sobrenome">
                    	<input type="password" name="password" maxlength="15" placeholder="Senha">
                    <select name="status" class="altura-diferente">
	                 		<option value="ATIVO" selected>ATIVO</option>
	                  	 	<option value="INATIVO" >INATIVO</option>
	                </select>
                    </div>
                </div>    
				<button type="submit">Editar</button>
            </form>
        </section>
	</body>
</html>