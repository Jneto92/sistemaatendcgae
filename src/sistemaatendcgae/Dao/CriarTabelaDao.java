/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author NETO
 */
public class CriarTabelaDao {
    
    public void tabelaPublico(){
        //url de conexao
        String url = "jdbc:mysql://192.168.15.14:3306/sistemacgae";
            //sql statement
        
        String sql = "CREATE TABLE IF NOT EXISTS tabela_publico (\n"
                + "cpf varchar(20), \n"
                + "nome VARCHAR(100), \n"
                + "email varchar(100),"
                + "constraint cpf_user PRIMARY KEY (cpf)"
                + ");";
        
        try {
            Connection conn = DriverManager.getConnection(url, "user", "123456");
            //intanciar o stantement
            Statement  stmt = conn.createStatement();
            //Criar nova tabela
            stmt.execute(sql);
            stmt.close();
            System.out.println("Criada a tabela");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void tabelaServidor(){
        //url de conexao
        String url = "jdbc:mysql://192.168.15.14:3306/sistemacgae";
            //sql statement
        String sql = "CREATE TABLE IF NOT EXISTS tabela_servidor (\n"
                + "matricula int PRIMARY KEY, \n"
                + "nome VARCHAR(100) NOT NULL, \n"
                + "email VARCHAR(100) not null, \n"
                + "cpf VARCHAR(100) NOT NULL, \n"
                + "senha_acesso VARCHAR(100) NOT NULL, \n"
                + "telefone VARCHAR(100), \n"
                + "setor VARCHAR(100) not null, \n"
                + "funcao VARCHAR(100) not null"
                + ");";
        
        
        
        try {
            Connection conn = DriverManager.getConnection(url, "user", "123456");
            //intanciar o stantement
            Statement  stmt = conn.createStatement();
            //Criar nova tabela
            stmt.execute(sql);
            stmt.close();
            System.out.println("Criada a tabela");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void tabelaAtendimento(){
        //url de conexao
        String url = "jdbc:mysql://192.168.15.14:3306/sistemacgae";
            //sql statement
        String sql = "CREATE TABLE IF NOT EXISTS tabela_atendimento (\n"
                + "senha_atendimento int primary key AUTO_INCREMENT, \n"
                + "fk_publico_nome VARCHAR(100), \n"
                + "fk_publico_email VARCHAR(100), \n"
                + "tipoAtendimento VARCHAR(100), \n"
                + "fk_servidor_matricula int, \n"
                + "fk_servidor_nome VARCHAR(100), \n"
                + "data_solicitacao VARCHAR(100), \n"
                + "hora_solicitacao VARCHAR(100), \n"
                + "data_atendimento VARCHAR(100), \n"
                + "hora_atendimento VARCHAR(100), \n"
                + "data_encerramento VARCHAR(100), \n"
                + "hora_encerramento VARCHAR(100), \n"
                + "observacoes varchar(500), \n"
                + "status VARCHAR(100), \n"
                + "CONSTRAINT fk_publicoAtend FOREIGN KEY (fk_publico_nome) REFERENCES tabela_publico (cpf) ON DELETE CASCADE, \n"
                + "CONSTRAINT fk_servidorAtend1 FOREIGN KEY (fk_servidor_matricula) REFERENCES tabela_servidor (matricula) ON DELETE CASCADE \n"
                + ");";
        
        
        try {
            Connection conn = DriverManager.getConnection(url, "user", "123456");
            //intanciar o stantement
            Statement  stmt = conn.createStatement();
            //Criar nova tabela
            stmt.execute(sql);
            stmt.close();
            System.out.println("Criada a tabela");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void tabelaTipoAtendimento(){
        String url = "jdbc:mysql://192.168.15.14:3306/sistemacgae";
        
        String sql = "CREATE TABLE IF NOT EXISTS tabela_tipoAtendimento (\n"
                + "id_tipo integer PRIMARY KEY, \n"
                + "nome text"
                + ");";
        
        try {
            Connection conn = DriverManager.getConnection(url, "user", "123456");
            //intanciar o stantement
            Statement  stmt = conn.createStatement();
            //Criar nova tabela
            stmt.execute(sql);
            stmt.close();
            System.out.println("Criada a tabela");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void tabelaStatus(){
        String url = "jdbc:mysql://192.168.15.14:3306/sistemacgae";
        
        String sql = "CREATE TABLE IF NOT EXISTS tabela_status (\n"
                + "id_status integer PRIMARY KEY, \n"
                + "nome text"
                + ");";
        
        try {
            Connection conn = DriverManager.getConnection(url, "user", "123456");
            //intanciar o stantement
            Statement  stmt = conn.createStatement();
            //Criar nova tabela
            stmt.execute(sql);
            stmt.close();
            System.out.println("Criada a tabela");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void deletarTbAtendimento(){
        String url = "jdbc:mysql://192.168.15.14:3306/sistemacgae";
        String sql = "DROP TABLE IF EXISTS tabela_atendimento";
        try {
            Connection conn = DriverManager.getConnection(url, "user", "123456");
            //intanciar o stantement
            Statement  stmt = conn.createStatement();
            //Criar nova tabela
            stmt.execute(sql);
            stmt.close();
            System.out.println("Tabela deletada");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void deletarTbServidor(){
        String url = "jdbc:mysql://192.168.15.14:3306/sistemacgae";
        String sql = "DROP TABLE IF EXISTS tabela_servidor";
        try {
            Connection conn = DriverManager.getConnection(url, "user", "123456");
            //intanciar o stantement
            Statement  stmt = conn.createStatement();
            //Criar nova tabela
            stmt.execute(sql);
            stmt.close();
            System.out.println("Tabela deletada");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void deletarTbPublico(){
        String url = "jdbc:mysql://192.168.15.14:3306/sistemacgae";
        String sql = "DROP TABLE IF EXISTS tabela_publico";
        try {
            Connection conn = DriverManager.getConnection(url, "user", "123456");
            //intanciar o stantement
            Statement  stmt = conn.createStatement();
            //Criar nova tabela
            stmt.execute(sql);
            stmt.close();
            System.out.println("Tabela deletada");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
