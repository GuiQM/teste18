/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loja.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/loja_db";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";
    private static Connection connection;

    public static Connection conectar() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USUARIO, SENHA);
                criarTabelaUsuarios();
                criarTabelaProdutos();
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco de dados", e);
        }
    }

    private static void criarTabelaUsuarios() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "email VARCHAR(255) NOT NULL, "
                + "senha VARCHAR(255) NOT NULL) ";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
             System.out.println("ERRO AO CRIAR TABELA 'USUARIOS': "+ e.getMessage());
        }
    }
    
    private static void criarTabelaProdutos(){
        String sql = "CREATE TABLE IF NOT EXISTS produtos ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "nome VARCHAR(255) NOT NULL,"
                + "descricao TEXT NOT NULL,"
                + "quantidade INT NOT NULL,"
                + "preco DECIMAL(10,2) NOT NULL)";
        try(Statement stmt = connection.createStatement()){
            stmt.execute(sql);
        }catch(SQLException e){
            System.out.println("ERRO AO CRIAR TABELA 'PRODUTOS': "+ e.getMessage());
        }
    }
}
