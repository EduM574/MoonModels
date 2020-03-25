<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
		<title>Login administrador</title>
	</head>
	<body>
		<header class="container">
			<p class="ladm-logo-header ">MO<span>O</span>N</p>
		</header>
		<section class="ladm-content">
			<form action="LoginAdmin.do" method="post" class="ladm-form">
				<input type="email" name="email" placeholder="E-mail">
				<input type="password" name="password" placeholder="Senha">
				<button type="submit">Fazer login</button>
			</form>
			<p>
				Ainda não possuo conta, <a href="">fazer cadastro</a> 
			</p>
		</section>
	</body>
</html>