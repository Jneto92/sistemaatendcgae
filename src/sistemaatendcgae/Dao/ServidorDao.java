/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sistemaatendcgae.model.domain.Servidor;
import sistemaatendcgae.controller.FXMLTelaLoginController;
import sistemaatendcgae.controller.FXMLTelaServidorCgaeController;

/**
 *
 * @author Neto
 */
public class ServidorDao {
    private Connection conn;

    public ServidorDao() {
        conectar();
    }
    
    public void registrarServidor(Servidor serv){
        String sql = "INSERT INTO tabela_servidor(matricula, nome, email, cpf, senha_acesso, telefone, setor) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = this.conectar();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, serv.getMatricula());
            pstmt.setString(2, serv.getNome());
            pstmt.setString(3, serv.getEmail());
            pstmt.setString(4, serv.getCpf());
            pstmt.setString(5, serv.getSenha_acesso());
            pstmt.setString(6, serv.getTelefone());
            pstmt.setString(7, serv.getSetor());
            
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Registros inseridos");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private Connection conectar(){ 
        String url = "jdbc:sqlite:C:/Users/NETO/Documents/NetBeansProjects/SistemaAtendCgae/src/banco_de_dados/banco_sqlite.db";
        conn = null;
        
        try {
            conn = DriverManager.getConnection(url);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    
}
