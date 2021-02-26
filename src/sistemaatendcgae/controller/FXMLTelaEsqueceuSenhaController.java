/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import sistemaatendcgae.Dao.ServidorDao;
import sistemaatendcgae.model.domain.Main;
import sistemaatendcgae.model.domain.Servidor;

/**
 * FXML Controller class
 *
 * @author Neto
 */
public class FXMLTelaEsqueceuSenhaController implements Initializable {

    @FXML
    private AnchorPane apTabCad;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnLimpar;
    @FXML
    private JFXTextField cmpMatricula;
    @FXML
    private JFXTextField cmpCpf;
    @FXML
    private JFXTextField cmpEmail;
    @FXML
    private JFXPasswordField cmpSenha;
    @FXML
    private JFXPasswordField cmpCsenha;
    @FXML
    private Label lb1;
    @FXML
    private Label lb3;
    @FXML
    private Label lb5;
    @FXML
    private Label lb7;
    @FXML
    private Label lb9;
    @FXML
    private Button btnVoltar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void limparCampos(ActionEvent event) {
        limparCampos();
    }

    @FXML
    private void salvarTrocaSenha(ActionEvent event) throws IOException, SQLException {
        if(cmpMatricula.getText().isEmpty() || cmpEmail.getText().isEmpty() || cmpSenha.getText().isEmpty() || cmpCsenha.getText().isEmpty()
                ||  cmpCpf.getText().isEmpty()){
            
            if(cmpMatricula.getText().isEmpty()){
                lb1.setVisible(true);
            }else{
                lb1.setVisible(false);
            }
            
            if(cmpCpf.getText().isEmpty()){
                lb3.setVisible(true);
            }else{
                lb3.setVisible(false);
            } 
            if(cmpEmail.getText().isEmpty()){
                lb5.setVisible(true);
            }else{
                lb5.setVisible(false);
            }
          
            if(cmpSenha.getText().isEmpty()){
                lb7.setVisible(true);
            }else{
                lb7.setVisible(false);
            }
            if(cmpCsenha.getText().isEmpty()){
                lb9.setVisible(true);
            }else{
                lb9.setVisible(false);
            }
            
        }else{
            if(cmpSenha.getText().equals(cmpCsenha.getText())){
                int matricula = Integer.parseInt(cmpMatricula.getText());
                String cpf = cmpCpf.getText();
                String email = cmpEmail.getText();
                String senha = cmpSenha.getText();
                Servidor serv = new Servidor(matricula, senha, email, cpf);
                ServidorDao dao = new ServidorDao();
                if(dao.procurarServ(serv)){
                    JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
                    limparCampos();
                    voltar(event);
                }else{
                    JOptionPane.showMessageDialog(null, "Servidor não encontrado");
                }
            }else{
                JOptionPane.showMessageDialog(null, "O campo senha e confimar senha não são iguais!");
            }
            
        }
    }
    public void limparCampos(){
        lb1.setVisible(false);
        
        lb3.setVisible(false);
        
        lb5.setVisible(false);
        
        lb7.setVisible(false);
        
        lb9.setVisible(false);
        
        cmpMatricula.setText("");
        
        cmpCpf.setText("");
        cmpEmail.setText("");
        cmpSenha.setText("");
        cmpCsenha.setText("");
        
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        Main.getStage().close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sistemaatendcgae/view/FXMLTelaPrincipal2.fxml"));
        Scene scene = new Scene(root);
        Main.setStage(stage);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        
        
    }
    
}
