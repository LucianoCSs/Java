package br.com.crudjava.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/dbproduto";
    private static final String USER = "usuario2024";
    private static final String PASSWORD = "1234";
    
    public static Connection obterConexao() throws SQLException{
        // Gerencia o driver e cria uma conexão com o
        // banco de dados
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
