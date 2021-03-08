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
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemaatendcgae.controller.FXMLTelaLoginController;
import sistemaatendcgae.controller.FXMLTelaServidorCgaeController;
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
    
    public void updateStatusAtendimento(Atendimento at, int op) throws ParseException{
        
        if(op==1){
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
        }else if(op==2){
            String sql = "UPDATE tabela_atendimento SET status=?, data_atendimento=?, hora_atendimento=?, fk_servidor_matricula=? WHERE senha_atendimento=?";
            try {
                conn = this.conectar();

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, at.getStatus());
                pstmt.setString(2, at.getData_atendimento());
                pstmt.setString(3, at.getHora_atendimento());
                pstmt.setInt(4, at.getMatriculaServ());
                pstmt.setInt(5, at.getSenha_atendimento());



                pstmt.executeUpdate();
                pstmt.close();
                System.out.println("Registros atualizados");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            String sql = "UPDATE tabela_atendimento SET status=?, data_atendimento=?, hora_atendimento=?, tipoAtendimento=? WHERE senha_atendimento=?";
            try {
                conn = this.conectar();

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, at.getStatus());
                pstmt.setString(2, at.getData_atendimento());
                pstmt.setString(3, at.getHora_atendimento());
                pstmt.setString(4, at.getTipoAtendimento());
                pstmt.setInt(5, at.getSenha_atendimento());



                pstmt.executeUpdate();
                pstmt.close();
                System.out.println("Registros atualizados");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
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
    public List<Atendimento> listaPesq(String ate, int n){
        List<Atendimento> retorno = new ArrayList<>();
        if(n==0){
            String sql = "SELECT * FROM tabela_atendimento WHERE fk_publico_nome='"+ate+"' and (status='Ausente' or status='Na fila')";
            
            try {
                Connection conn = this.conectar();
                Statement stmt = conn.createStatement();
                ResultSet resultado = stmt.executeQuery(sql);
                while(resultado.next()){
                    Atendimento at = new Atendimento();
                    at.setSenha_atendimento(resultado.getInt("senha_atendimento"));
                    at.setNome(resultado.getString("fk_publico_nome"));
                    at.setTipoAtendimento(resultado.getString("tipoAtendimento"));
                    at.setEmail(resultado.getString("fk_publico_email"));
                    at.setStatus(resultado.getString("status"));


                    retorno.add(at);
                
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }else if(n==1){
            
            String sql = "SELECT * FROM tabela_atendimento WHERE status='"+ate+"'";
            
            try {
                Connection conn = this.conectar();
                Statement stmt = conn.createStatement();
                ResultSet resultado = stmt.executeQuery(sql);
                while(resultado.next()){
                    Atendimento at = new Atendimento();
                    at.setSenha_atendimento(resultado.getInt("senha_atendimento"));
                    at.setNome(resultado.getString("fk_publico_nome"));
                    at.setTipoAtendimento(resultado.getString("tipoAtendimento"));
                    at.setEmail(resultado.getString("fk_publico_email"));
                    at.setStatus(resultado.getString("status"));


                    retorno.add(at);
                
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }else if(n==3){
            
            String sql = "SELECT * FROM tabela_atendimento WHERE fk_publico_nome='"+ate+"' and status='Encerrada'";
            try {
                Connection conn = this.conectar();
                Statement stmt = conn.createStatement();
                ResultSet resultado = stmt.executeQuery(sql);
                while(resultado.next()){
                    Atendimento at = new Atendimento();
                    at.setSenha_atendimento(resultado.getInt("senha_atendimento"));
                    at.setNome(resultado.getString("fk_publico_nome"));
                    at.setTipoAtendimento(resultado.getString("tipoAtendimento"));
                    at.setEmail(resultado.getString("fk_publico_email"));
                    at.setServidor(resultado.getString("fk_servidor_nome"));


                    retorno.add(at);
                
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }else if(n==2){
            String sql = "SELECT * FROM tabela_atendimento WHERE tipoAtendimento='"+ate+"' and (status='Ausente' or status='Na fila')";
            
            try {
                Connection conn = this.conectar();
                Statement stmt = conn.createStatement();
                ResultSet resultado = stmt.executeQuery(sql);
                while(resultado.next()){
                    Atendimento at = new Atendimento();
                    at.setSenha_atendimento(resultado.getInt("senha_atendimento"));
                    at.setNome(resultado.getString("fk_publico_nome"));
                    at.setTipoAtendimento(resultado.getString("tipoAtendimento"));
                    at.setEmail(resultado.getString("fk_publico_email"));
                    at.setStatus(resultado.getString("status"));


                    retorno.add(at);
                
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(n==4){
            String sql = "SELECT * FROM tabela_atendimento WHERE fk_servidor_nome='"+ate+"' and status='Encerrada'";
            try {
                Connection conn = this.conectar();
                Statement stmt = conn.createStatement();
                ResultSet resultado = stmt.executeQuery(sql);
                while(resultado.next()){
                    Atendimento at = new Atendimento();
                    at.setSenha_atendimento(resultado.getInt("senha_atendimento"));
                    at.setNome(resultado.getString("fk_publico_nome"));
                    at.setTipoAtendimento(resultado.getString("tipoAtendimento"));
                    at.setEmail(resultado.getString("fk_publico_email"));
                    at.setServidor(resultado.getString("fk_servidor_nome"));


                    retorno.add(at);
                
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(n==5){
            String sql = "SELECT * FROM tabela_atendimento WHERE data_encerramento='"+ate+"' and status='Encerrada'";
            try {
                Connection conn = this.conectar();
                Statement stmt = conn.createStatement();
                ResultSet resultado = stmt.executeQuery(sql);
                while(resultado.next()){
                    Atendimento at = new Atendimento();
                    at.setSenha_atendimento(resultado.getInt("senha_atendimento"));
                    at.setNome(resultado.getString("fk_publico_nome"));
                    at.setTipoAtendimento(resultado.getString("tipoAtendimento"));
                    at.setEmail(resultado.getString("fk_publico_email"));
                    at.setServidor(resultado.getString("fk_servidor_nome"));


                    retorno.add(at);
                
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(n==6){
            String sql = "SELECT * FROM tabela_atendimento WHERE tipoAtendimento='"+ate+"' and status='Encerrada'";
            try {
                Connection conn = this.conectar();
                Statement stmt = conn.createStatement();
                ResultSet resultado = stmt.executeQuery(sql);
                while(resultado.next()){
                    Atendimento at = new Atendimento();
                    at.setSenha_atendimento(resultado.getInt("senha_atendimento"));
                    at.setNome(resultado.getString("fk_publico_nome"));
                    at.setTipoAtendimento(resultado.getString("tipoAtendimento"));
                    at.setEmail(resultado.getString("fk_publico_email"));
                    at.setServidor(resultado.getString("fk_servidor_nome"));


                    retorno.add(at);
                
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(n==7){
            String sql = "SELECT * FROM tabela_atendimento WHERE senha_atendimento='"+ate+"' and status='Encerrada'";
            try {
                Connection conn = this.conectar();
                Statement stmt = conn.createStatement();
                ResultSet resultado = stmt.executeQuery(sql);
                while(resultado.next()){
                    Atendimento at = new Atendimento();
                    at.setSenha_atendimento(resultado.getInt("senha_atendimento"));
                    at.setNome(resultado.getString("fk_publico_nome"));
                    at.setTipoAtendimento(resultado.getString("tipoAtendimento"));
                    at.setEmail(resultado.getString("fk_publico_email"));
                    at.setServidor(resultado.getString("fk_servidor_nome"));


                    retorno.add(at);
                
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return retorno;
        
    }
    
    public List<Atendimento> lista(){
        String sql = "SELECT * FROM tabela_atendimento WHERE status = 'Na fila' or status = 'Ausente'";
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
                at.setStatus(resultado.getString("status"));
                
                
                retorno.add(at);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    };
    
    public List<Atendimento> listaTodosStatus(){
        String sql = "SELECT * FROM tabela_atendimento";
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
                at.setStatus(resultado.getString("status"));
                
                
                retorno.add(at);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    }
    
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
                //System.out.println(at.getEmail());
                
                retorno.add(at);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    }
    public List<Atendimento> verifcarAt(int n){
        List<Atendimento> retorno = new ArrayList<>();
        String sql = "SELECT * FROM tabela_atendimento WHERE fk_servidor_matricula="+n+" and status='Em andamento'";
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
                
                //System.out.println(at.getEmail());
                
                retorno.add(at);
            }
        } catch (SQLException e) {
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
                
                //System.out.println(at.getEmail());
                
                retorno.add(at);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    }
    
    public void deletarAtendimento(Atendimento at){
        String sql = "DELETE FROM tabela_atendimento WHERE senha_atendimento=?";
        try {
            Connection conn = this.conectar();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, at.getSenha_atendimento());
            
            
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Registros deletados");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void verificarAtendimento(){
        String sql = "SELECT * FROM tabela_atendimento WHERE matricula=? and status='Em andamento'";
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
