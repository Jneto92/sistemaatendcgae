/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import sistemaatendcgae.model.domain.Main;
/**
 * FXML Controller class
 *
 * @author Neto
 */
public class FXMLTelaLoginController implements Initializable {

    public static int userLogado;
    @FXML
    private Button btnLogin;
    private Button btnSingIn;
    @FXML
    private Label labelLSincorreto;
    @FXML
    public JFXTextField cmpLogin;
    @FXML
    private JFXPasswordField cmpSenha;
    @FXML
    private Button btnTrocaSenha;
    @FXML
    private JFXButton btnVisualizarKey;
    
    public boolean v = false;
    @FXML
    private JFXTextField cmpMostarSenha;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    public static int getUserLogado() {
        return userLogado;
    }

    public static void setUserLogado(int userLogado) {
        FXMLTelaLoginController.userLogado = userLogado;
    }

    public Button getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(Button btnLogin) {
        this.btnLogin = btnLogin;
    }

    public Button getBtnSingIn() {
        return btnSingIn;
    }

    public void setBtnSingIn(Button btnSingIn) {
        this.btnSingIn = btnSingIn;
    }

    public Label getLabelLSincorreto() {
        return labelLSincorreto;
    }

    public void setLabelLSincorreto(Label labelLSincorreto) {
        this.labelLSincorreto = labelLSincorreto;
    }

    public JFXTextField getCmpLogin() {
        return cmpLogin;
    }

    public void setCmpLogin(JFXTextField cmpLogin) {
        this.cmpLogin = cmpLogin;
    }

    public JFXPasswordField getCmpSenha() {
        return cmpSenha;
    }

    public void setCmpSenha(JFXPasswordField cmpSenha) {
        this.cmpSenha = cmpSenha;
    }

    private Connection conectar(){ 
        String url = "jdbc:mysql://192.168.15.14:3306/sistemacgae";
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url, "user", "123456");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    @FXML
    private void logarSistema(ActionEvent event) throws IOException {
        logarnoSistema();
    }

    

    public void logarnoSistema() throws IOException{
        
        String sql = "SELECT * FROM tabela_servidor WHERE matricula=? and senha_acesso=?";
        try {
            Connection conn = this.conectar();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cmpLogin.getText());
            pstmt.setString(2, cmpSenha.getText());
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Login feito");
                
                userLogado = Integer.parseInt(cmpLogin.getText());
                //System.out.println(userLogado);
                cmpLogin.setText("");
                cmpSenha.setText("");
                labelLSincorreto.setVisible(false);
                Main.getStage().close();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/sistemaatendcgae/view/FXMLTelaServidor.fxml"));
                Scene scene = new Scene(root);
                Main.setStage(stage);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
                rs.close();
                
            }else{
                JOptionPane.showMessageDialog(null, "login n feito");
                //campoLogin.setText("");
                cmpSenha.setText("");
                labelLSincorreto.setVisible(true);
            }    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void logar(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){ 
            logarnoSistema();
        }
        else if(event.getCode() == KeyCode.F10){
                
                //logarnoSistema();
                //JOptionPane.showMessageDialog(null, "Deu certo");
            try {
                Main.getStage().close();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/sistemaatendcgae/view/FXMLTelaCadastroServidor.fxml"));
                Scene scene = new Scene(root);
                Main.setStage(stage);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                Logger.getLogger(FXMLTelaPrincipalController.class.getName()).log(Level.SEVERE, null, e);
            }
            
        }
    }
    
    

    @FXML
    private void trocarSenha(ActionEvent event) throws IOException {
        try {
            Main.getStage().close();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sistemaatendcgae/view/FXMLTelaEsqueceuSenha.fxml"));
            Scene scene = new Scene(root);
            Main.setStage(stage);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(FXMLTelaPrincipalController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void visualizarKey(ActionEvent event) {
        String vizualizar = cmpSenha.getText();
        cmpMostarSenha.setText(vizualizar);
        if(v==false){
            cmpMostarSenha.setVisible(true);
            cmpSenha.setVisible(false);
            v=true;
        }else{
            cmpMostarSenha.setVisible(false);
            cmpSenha.setVisible(true);
            v=false;
        }
    }

    
    

    
    
}
