package dao;

import java.sql.*;

public class ConnectionFactory {
	public static Connection conectar() {
		String user = "";
		String password = "";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/moon?useTimezone=true&serverTimezone=America/Sao_Paulo&useSSL=false", user, password);
		} catch(Exception e) {
			System.err.println("Falha: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
