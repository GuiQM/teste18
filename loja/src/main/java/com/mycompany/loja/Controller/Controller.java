package com.mycompany.loja.Controller;

import com.mycompany.loja.Model.NewProduto;
import com.mycompany.loja.Model.NewUser;
import com.mycompany.loja.Model.Produto;
import java.util.ArrayList;


public class Controller {
    private NewUser newuser = new NewUser();
    private NewProduto newproduct = new NewProduto();
    
     public boolean registrarUsuario(String email, String senha) {
        return newuser.registrar(email, senha);
    }

    public boolean validarLogin(String email, String senha) {
        return newuser.validarLogin(email, senha);
    }
    
    //Produtos\\
    
    public boolean registrarProduto(String nome, String desc, float preco, int quantidade){
        return newproduct.registrarProduto(nome, desc, preco, quantidade);
    }
    
    public boolean atualizarProduto(int id, String Nome, String desc, float preco, int quantidade){
        return newproduct.atualizarProduto(Nome, desc, preco, quantidade);
    }
    
    public boolean deletarProduto(int id){
        return newproduct.deletarProduto(id);
    }
    
    public ArrayList<Produto> listarProduto(){
        return newproduct.listarProduto();
    }
}