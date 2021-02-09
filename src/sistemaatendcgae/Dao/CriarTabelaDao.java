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
                + "email text, \n"
                + "tipoAtendimento text not null, \n"
                + "dataSolicitacao date not null, \n"
                + "horaSolicitacao time not null, \n"
                + "status text \n"
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
                + "setor text not null"
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
                + "senha_atendimento integer PRIMARY KEY, \n"
                + "fk_publico_cpf text, \n"
                + "fk_publico_nome text, \n"
                + "fk_publico_tipo text, \n"
                + "fk_servidor_matricula text, \n"
                + "fk_servidor_nome text, \n"
                + "data_solicitacao text, \n"
                + "data_atendimento text, \n"
                + "data_encerramento text, \n"
                + "CONSTRAINT fk_publicoAtend FOREIGN KEY (fk_publico_cpf) REFERENCES tabela_publico (cpf), \n"
                + "CONSTRAINT fk_publicoAtend2 FOREIGN KEY (fk_publico_nome) REFERENCES tabela_publico (nome), \n"
                + "CONSTRAINT fk_publicoAtend3 FOREIGN KEY (fk_publico_tipo) REFERENCES tabela_publico (tipoAtendimento), \n"
                + "CONSTRAINT fk_servidorAtend1 FOREIGN KEY (fk_servidor_matricula) REFERENCES tabela_servidor (matricula), \n"
                + "CONSTRAINT fk_servidorAtend2 FOREIGN KEY (fk_servidor_nome) REFERENCES tabela_servidor (nome) \n"
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
}
