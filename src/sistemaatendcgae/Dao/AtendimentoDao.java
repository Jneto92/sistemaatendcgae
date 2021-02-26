/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemaatendcgae.model.domain.Atendimento;
import sistemaatendcgae.model.domain.Publico;

/**
 *
 * @author Neto
 */
public class AtendimentoDao {
    private Connection conn;

    public AtendimentoDao() {
        conectar();
    }
    
    
    public void solicitarAtendimento(Atendimento at) throws ParseException{
        
        
        
        String sql = "INSERT INTO tabela_atendimento(fk_publico_nome, fk_publico_email, tipoAtendimento, status, data_solicitacao, hora_solicitacao) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            conn = this.conectar();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, at.getNome());
            pstmt.setString(2, at.getEmail());
            pstmt.setString(3, at.getTipoAtendimento());
            pstmt.setString(4, at.getStatus());
            pstmt.setString(5, at.getData_solicitacao());
            pstmt.setString(6, at.getHora_solicitacao());
            
            
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Registros inseridos");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    };
    
    public void updateStatusAtendimento(Atendimento at) throws ParseException{
        
        
        
        String sql = "UPDATE tabela_atendimento SET status=?, data_atendimento=?, hora_atendimento=? WHERE senha_atendimento=?";
        try {
            conn = this.conectar();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, at.getStatus());
            pstmt.setString(2, at.getData_atendimento());
            pstmt.setString(3, at.getHora_atendimento());
            pstmt.setInt(4, at.getSenha_atendimento());
            
            
            
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Registros atualizados");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    };
    
    public void encerrarAtendimento(Atendimento at){
        String sql = "UPDATE tabela_atendimento SET status=?, data_encerramento=?, hora_encerramento=?, fk_servidor_matricula=?, fk_servidor_nome=?, observacoes=? WHERE senha_atendimento=?";
        try {
            conn = this.conectar();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, at.getStatus());
            pstmt.setString(2, at.getData_encerramento());
            pstmt.setString(3, at.getHora_encerramento());
            pstmt.setInt(4, at.getMatriculaServ());
            pstmt.setString(5, at.getServidor());
            pstmt.setString(6, at.getObservacao());
            pstmt.setInt(7, at.getSenha_atendimento());
            
            
            
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Registros atualizados");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    };
    
    public List<Atendimento> lista(){
        String sql = "SELECT * FROM tabela_atendimento WHERE status = 'Na fila'";
        List<Atendimento> retorno = new ArrayList<>();
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Atendimento at = new Atendimento();
                at.setSenha_atendimento(resultado.getInt("senha_atendimento"));
                at.setNome(resultado.getString("fk_publico_nome"));
                at.setTipoAtendimento(resultado.getString("tipoAtendimento"));
                at.setEmail(resultado.getString("fk_publico_email"));
                
                
                retorno.add(at);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    };
    
    public List<Atendimento> listaEnc(){
        List<Atendimento> retorno = new ArrayList<>();
        String sql = "SELECT * FROM tabela_atendimento WHERE status = 'Encerrada'";
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Atendimento at = new Atendimento();
                at.setSenha_atendimento(resultado.getInt("senha_atendimento"));
                at.setNome(resultado.getString("fk_publico_nome"));
                at.setTipoAtendimento(resultado.getString("tipoAtendimento"));
                at.setEmail(resultado.getString("fk_publico_email"));
                at.setServidor(resultado.getString("fk_servidor_nome"));
                System.out.println(at.getEmail());
                
                retorno.add(at);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    }
    public List<Atendimento> listaCompleta(){
        List<Atendimento> retorno = new ArrayList<>();
        String sql = "SELECT * FROM tabela_atendimento";
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Atendimento at = new Atendimento();
                at.setSenha_atendimento(resultado.getInt("senha_atendimento"));
                at.setNome(resultado.getString("fk_publico_nome"));
                at.setTipoAtendimento(resultado.getString("tipoAtendimento"));
                at.setEmail(resultado.getString("fk_publico_email"));
                at.setMatriculaServ(resultado.getInt("fk_servidor_matricula"));
                at.setServidor(resultado.getString("fk_servidor_nome"));
                at.setServidor(resultado.getString("data_solicitacao"));
                at.setServidor(resultado.getString("hora_solicitacao"));
                at.setServidor(resultado.getString("data_atendimento"));
                at.setServidor(resultado.getString("hora_atendimento"));
                at.setServidor(resultado.getString("data_encerramento"));
                at.setServidor(resultado.getString("hora_encerramento"));
                at.setServidor(resultado.getString("observacoes"));
                at.setServidor(resultado.getString("status"));
                
                System.out.println(at.getEmail());
                
                retorno.add(at);
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
