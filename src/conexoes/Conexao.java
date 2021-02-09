package conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        try {
            //COMANDO PARA CONECTAR AO SQLIT
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/NETO/Documents/NetBeansProjects/SistemaAtendCgae/src/banco_de_dados/banco_sqlite.db";
            //conectar
            this.conexao = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("Conectou com o banco");
        return true;
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
