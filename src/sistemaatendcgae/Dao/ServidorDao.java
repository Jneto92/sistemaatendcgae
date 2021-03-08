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
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import sistemaatendcgae.model.domain.Servidor;
import sistemaatendcgae.controller.FXMLTelaLoginController;
import static sistemaatendcgae.controller.FXMLTelaLoginController.userLogado;
import sistemaatendcgae.controller.FXMLTelaServidorCgaeController;
import sistemaatendcgae.model.domain.Atendimento;
import sistemaatendcgae.model.domain.Main;

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
        String sql = "INSERT INTO tabela_servidor(matricula, nome, email, cpf, senha_acesso, telefone, setor, funcao) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
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
            pstmt.setString(8, serv.getFuncao());
            
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Registros inseridos");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateServidor(Servidor serv){
        String sql = "UPDATE tabela_servidor SET matricula=?, email=?, cpf=?, telefone=?, setor=?, funcao=? WHERE nome=?";
        //String sql = "INSERT INTO tabela_servidor(matricula, nome, email, cpf, telefone, setor, funcao) VALUES(?, ?, ?, ?, ?, ?, ?)";
        System.out.println(serv.getFuncao());
        try {
            Connection conn = this.conectar();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, serv.getMatricula());
            pstmt.setString(2, serv.getEmail());
            pstmt.setString(3, serv.getCpf());
            pstmt.setString(4, serv.getTelefone());
            pstmt.setString(5, serv.getSetor());
            pstmt.setString(6, serv.getFuncao());
            pstmt.setString(7, serv.getNome());
            
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Registros atualizados");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deletarServidor(Servidor serv){
        String sql = "DELETE FROM tabela_servidor WHERE matricula=?";
        try {
            Connection conn = this.conectar();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, serv.getMatricula());
            
            
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Registros deletados");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean procurarServ(Servidor sv){
        String sql = "SELECT * FROM tabela_servidor WHERE matricula=? and email=? and cpf=?";
        boolean retorno = false;
        try {
            Connection conn = this.conectar();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sv.getMatricula());
            pstmt.setString(2, sv.getEmail());
            pstmt.setString(3, sv.getCpf());
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                //JOptionPane.showMessageDialog(null, "Servidor encontrado");
                alterarSenhaServ(sv);
                retorno = true;
                
            }else{
                
                retorno = false;
            }    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }
    public void alterarSenhaServ(Servidor sv) throws SQLException{
        String sql = "UPDATE tabela_servidor SET senha_acesso=? WHERE matricula=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        try {
            conn = this.conectar();
            
            
            pstmt.setString(1, sv.getSenha_acesso());
            pstmt.setInt(2, sv.getMatricula());
            
            
            
            pstmt.executeUpdate();
            
            System.out.println("Registros atualizados");
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Campos digitados não conferem!");
        }
        pstmt.close();
    }
    
    public List<Servidor> lista(){
        String sql = "SELECT * FROM tabela_servidor";
        List<Servidor> retorno = new ArrayList<>();
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Servidor sv = new Servidor();
                sv.setMatricula(resultado.getInt("matricula"));
                sv.setNome(resultado.getString("nome"));
                sv.setEmail(resultado.getString("email"));
                sv.setFuncao(resultado.getString("funcao"));
                
                
                //System.out.println(sv.getEmail());
                
                retorno.add(sv);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        return retorno;
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
