/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        
        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dataFormatada = formatterData.format(agora);

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = formatterHora.format(agora);
        
        /*SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
        Time hora = new Time(format2.parse(horaFormatada).getTime());
        System.out.println(hora);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date(format.parse(dataFormatada).getTime());
        System.out.println(data);
        */
        
        
        String sql = "INSERT INTO tabela_publico(cpf, nome, email, tipoAtendimento, dataSolicitacao, horaSolicitacao, status) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = this.conectar();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pub.getCpf());
            pstmt.setString(2, pub.getNome());
            pstmt.setString(3, pub.getEmail());
            pstmt.setString(4, pub.getTipoAtendimento());
            pstmt.setString(5, dataFormatada);
            pstmt.setString(6, horaFormatada);
            pstmt.setString(7, "Na fila");
            
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
