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
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemaatendcgae.model.domain.Publico;

/**
 *
 * @author Neto
 */
public class PublicoDao {
    private Connection conn;

    public PublicoDao() {
        conectar();
    }
    
    public void solicitarAtendimento(Publico pub) throws ParseException{
        
        
        
        
        String sql = "INSERT INTO tabela_publico(cpf, nome, email) VALUES(?, ?, ?)";
        try {
            conn = this.conectar();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pub.getCpf());
            pstmt.setString(2, pub.getNome());
            pstmt.setString(3, pub.getEmail());
            
            
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Registros inseridos");
            
        } catch (SQLException e) {
            System.out.println("Usuário já está no banco");
        }
        
        
    }
    
    public List<Publico> lista(){
        String sql = "SELECT * FROM tabela_publico WHERE status = 'Na fila'";
        List<Publico> retorno = new ArrayList<>();
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Publico pub = new Publico();
                pub.setNome(resultado.getString("nome"));
                pub.setEmail(resultado.getString("email"));
                pub.setCpf(resultado.getString("cpf"));
                
                retorno.add(pub);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    };
    
    
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
