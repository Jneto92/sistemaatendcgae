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
        String url = "jdbc:sqlite:C:/Users/NETO/Documents/NetBeansProjects/SistemaAtendCgae/src/banco_de_dados/banco_sqlite.db";
            //sql statement
        
        String sql = "CREATE TABLE IF NOT EXISTS tabela_publico (\n"
                + "cpf text PRIMARY KEY, \n"
                + "nome text NOT NULL, \n"
                + "email text"
                + ");";
        
        try {
            Connection conn = DriverManager.getConnection(url);
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
        String url = "jdbc:sqlite:C:/Users/NETO/Documents/NetBeansProjects/SistemaAtendCgae/src/banco_de_dados/banco_sqlite.db";
            //sql statement
        String sql = "CREATE TABLE IF NOT EXISTS tabela_servidor (\n"
                + "matricula integer PRIMARY KEY, \n"
                + "nome text NOT NULL, \n"
                + "email text not null, \n"
                + "cpf text NOT NULL, \n"
                + "senha_acesso text NOT NULL, \n"
                + "telefone text, \n"
                + "setor text not null, \n"
                + "funcao text not null"
                + ");";
        
        
        
        try {
            Connection conn = DriverManager.getConnection(url);
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
        String url = "jdbc:sqlite:C:/Users/NETO/Documents/NetBeansProjects/SistemaAtendCgae/src/banco_de_dados/banco_sqlite.db";
            //sql statement
        String sql = "CREATE TABLE IF NOT EXISTS tabela_atendimento (\n"
                + "senha_atendimento integer primary key AUTOINCREMENT not null, \n"
                + "fk_publico_nome text, \n"
                + "fk_publico_email text, \n"
                + "tipoAtendimento text, \n"
                + "fk_servidor_matricula integer, \n"
                + "fk_servidor_nome text, \n"
                + "data_solicitacao text, \n"
                + "hora_solicitacao text, \n"
                + "data_atendimento text, \n"
                + "hora_atendimento text, \n"
                + "data_encerramento text, \n"
                + "hora_encerramento text, \n"
                + "observacoes varchar(500), \n"
                + "status text, \n"
                + "CONSTRAINT fk_publicoAtend FOREIGN KEY (fk_publico_nome) REFERENCES tabela_publico (nome) ON DELETE CASCADE, \n"
                + "CONSTRAINT fk_publicoAtend2 FOREIGN KEY (fk_publico_email) REFERENCES tabela_publico (email) ON DELETE CASCADE, \n"
                + "CONSTRAINT fk_servidorAtend1 FOREIGN KEY (fk_servidor_matricula) REFERENCES tabela_servidor (matricula) ON DELETE CASCADE, \n"
                + "CONSTRAINT fk_servidorAtend2 FOREIGN KEY (fk_servidor_nome) REFERENCES tabela_servidor (nome) ON DELETE CASCADE\n"
                + ");";
        
        
        try {
            Connection conn = DriverManager.getConnection(url);
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
        String url = "jdbc:sqlite:C:/Users/NETO/Documents/NetBeansProjects/SistemaAtendCgae/src/banco_de_dados/banco_sqlite.db";
        
        String sql = "CREATE TABLE IF NOT EXISTS tabela_tipoAtendimento (\n"
                + "id_tipo integer PRIMARY KEY, \n"
                + "nome text"
                + ");";
        
        try {
            Connection conn = DriverManager.getConnection(url);
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
        String url = "jdbc:sqlite:C:/Users/NETO/Documents/NetBeansProjects/SistemaAtendCgae/src/banco_de_dados/banco_sqlite.db";
        
        String sql = "CREATE TABLE IF NOT EXISTS tabela_status (\n"
                + "id_status integer PRIMARY KEY, \n"
                + "nome text"
                + ");";
        
        try {
            Connection conn = DriverManager.getConnection(url);
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
        String url = "jdbc:sqlite:C:/Users/NETO/Documents/NetBeansProjects/SistemaAtendCgae/src/banco_de_dados/banco_sqlite.db";
        String sql = "DROP TABLE IF EXISTS tabela_atendimento";
        try {
            Connection conn = DriverManager.getConnection(url);
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
        String url = "jdbc:sqlite:C:/Users/NETO/Documents/NetBeansProjects/SistemaAtendCgae/src/banco_de_dados/banco_sqlite.db";
        String sql = "DROP TABLE IF EXISTS tabela_servidor";
        try {
            Connection conn = DriverManager.getConnection(url);
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
        String url = "jdbc:sqlite:C:/Users/NETO/Documents/NetBeansProjects/SistemaAtendCgae/src/banco_de_dados/banco_sqlite.db";
        String sql = "DROP TABLE IF EXISTS tabela_publico";
        try {
            Connection conn = DriverManager.getConnection(url);
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
