/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.model.domain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import conexoes.Conexao;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.stage.StageStyle;
import sistemaatendcgae.Dao.CriarTabelaDao;




/**
 *
 * @author NETO
 */
public class Main extends Application {
    private static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("uuMMdd");
        String dataFormatada = formatterData.format(agora);

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HHmmss");
        String horaFormatada = formatterHora.format(agora);
        System.out.println(dataFormatada+""+horaFormatada);
        
        
        Parent root = FXMLLoader.load(getClass().getResource("/sistemaatendcgae/view/FXMLTelaPrincipal2.fxml"));
        
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("fxmlTelaPublico.css").toExternalForm());
        
        stage.setScene(scene);
        //stage.setResizable(false);
        //stage.setMaximized(true);
        //stage.setFullScreen(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        setStage(stage);
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException, ParseException {
        Conexao conn = new Conexao();
        conn.conectar();
        CriarTabelaDao pub = new CriarTabelaDao();
        Arquivos a = new Arquivos();
        System.out.println(System.getProperty("user.home"));
        
        //pub.tabelaPublico();
        //pub.tabelaServidor();
        //pub.tabelaAtendimento();
        //pub.tabelaStatus();
        //pub.tabelaTipoAtendimento();
        //a.criarDiretorio();
        launch(args);
        conn.desconectar();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
    
    
    
}
