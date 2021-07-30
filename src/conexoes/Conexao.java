package conexoes;
import java.sql.*;

/**
 *
 * @author NETO
 */
public class Conexao {
    
    private Connection conexao;
    /**
     * método para conectar o banco de dados
     * @return true - A conexão foi realizada com sucesso! 
     * @throws java.lang.ClassNotFoundException 
     */
    public boolean conectar() throws ClassNotFoundException{
        
      /*try(
                Connection conn = DriverManager.getConnection(DB_URL);
         Statement stmt = conn.createStatement();
      ) {		      
         String sql = "CREATE DATABASE banco_sqlite";
         stmt.executeUpdate(sql);
         System.out.println("Database created successfully...");   	  
      } catch (SQLException e) {
         e.printStackTrace();
      } */
      /*try {
            //COMANDO PARA CONECTAR AO SQLIT
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite://localhost/";
            //conectar
            this.conexao = DriverManager.getConnection(url);
            Statement stmt = this.conexao.createStatement();
            String sql = "CREATE DATABASE banco_sqlite";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("Conectou com o banco");
        
        return true;*/
        /*try {
            //COMANDO PARA CONECTAR AO SQLIT
            Class.forName("org.sqlite.JDBC");
            //String url = "jdbc:sqlite:\\DESKTOP-FK88HHU/banco_sqlite.db";
            String url = "jdbc:sqlite:C:/Users/NETO/Documents/NetBeansProjects/SistemaAtendCgae/src/banco_de_dados/banco_sqlite_teste.db";
            //conectar
            this.conexao = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("Conectou com o banco");*/
        try{	
            Class.forName("com.mysql.jdbc.Driver");
            this.conexao = DriverManager.getConnection("jdbc:mysql://192.168.15.14:3306/sistemacgae", "user", "123456"); 
            System.out.println("Base de dados conectado com sucesso!!!");
            return false;
            /*String sql = "CREATE DATABASE sistemacgae";
            stmt.executeUpdate(sql);*/
               	  
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Erro na conexão com o banco");
            return true;
        } 
        
    }
    /**
     * Método para desconectar
     * @return true - caso desconecte 
     */
    public boolean desconectar(){
        
        try {
            //comando para a conexão sqlite
            if(this.conexao.isClosed() == false)
                this.conexao.close();
            //conectar
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        
        System.out.println("Desconectou");
        return true;
    }
}
