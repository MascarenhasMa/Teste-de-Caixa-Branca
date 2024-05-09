package com.mycompany.testeequalidadedesoftware;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException; // Importação faltando para tratar exceções SQL
import java.sql.Statement;

public class User {
    // Método para conectar ao banco de dados
    public Connection conectarBD() {
        Connection conn = null;
        try {
            // Correção no nome do driver: "com.mysql.jdbc.Driver"
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Correção na string de conexão: "password" em vez de "passqord"
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace(); // Adição para identificar erros na conexão
        }
        return conn;
    }
    // Variáveis com nomes que não possuem boa nomeclatura
    public String nome = ""; // Sugestão: nomeUsuario
    public boolean result = false; // Sugestão: autenticado

    // Método para verificar usuário
    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        Connection conn = conectarBD();

        sql += "select nome from usuarios ";
        sql += "where login = '" + login + "'";
        sql += " and senha = '" + senha + "';";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }
        } catch (SQLException e) { // Correção para capturar exceções SQL
            e.printStackTrace(); // Adição para identificar erros de SQL
        } finally { // Fechar conexão ao banco de dados no final do método
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Adição para identificar erros ao fechar a conexão
            }
        }
        return result;
    }
}