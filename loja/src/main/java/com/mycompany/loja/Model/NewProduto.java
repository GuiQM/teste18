/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loja.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author GUILHERMEQUAIATOMOTA
 */
public class NewProduto {

    public boolean registrarProduto(String nome, String desc, float preco, int quantidade) {
        String sql = "INSERT INTO produtos(nome, descricao, preco, quantidade) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, desc);
            stmt.setFloat(3, preco);
            stmt.setInt(4, quantidade);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("ERRO AO INSERIR PRODUTO: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarProduto(String novoNome, String desc, float preco, int quantidade) {
        String sql = "UPDATE produtos SET nome = (?) descricao = (?) preco = (?) quantidade = (?) WHERE id = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setString(2, desc);
            stmt.setFloat(3, preco);
            stmt.setInt(4, quantidade);
            int atualizado = stmt.executeUpdate();

            if (atualizado > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com o ID fornecido");
            }

            return true;
        } catch (SQLException e) {
            System.out.println("ERRO AO ATUALIZAR PRODUTO: " + e.getMessage());
            return false;
        }
    }

    public boolean deletarProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = (?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int deleted = stmt.executeUpdate();
            if (deleted > 0) {
                System.out.println("Produto deletado com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com o ID fornecido.");
            }
            return true;
        } catch (SQLException e) {
            System.out.println("ERRO AO DELETAR PRODUTO: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Produto> listarProduto() {
        String sql = "SELECT * FROM produtos";
        ArrayList<Produto> produtos = new ArrayList<Produto>();
       
        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String nome = rs.getString("nome");
                String desc = rs.getString("descricao");
                float preco = Float.parseFloat(rs.getString("preco"));
                int quantidade = Integer.parseInt("quantidade");
               produtos.add(new Produto(id,nome,desc,preco,quantidade));
            }
            return produtos;
        } catch (SQLException e) {
            System.out.println("ERRO AO LISTAR PRODUTOS: " + e.getMessage());
            return produtos;
        }
    }
    
    

}
