/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.Pane;
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
public class FXMLTelaAtualizarServidorController implements Initializable {

    @FXML
    private Pane pnTitulo;
    @FXML
    private AnchorPane apTabCad;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnLimpar;
    @FXML
    private JFXTextField cmpMatricula;
    @FXML
    private JFXTextField cmpNome;
    @FXML
    private JFXTextField cmpCpf;
    @FXML
    private JFXTextField cmpEmail;
    @FXML
    private JFXTextField cmpFone;
    @FXML
    private JFXTextField cmpSetor;
    @FXML
    private Label lb1;
    @FXML
    private Label lb3;
    @FXML
    private Label lb5;
    @FXML
    private Label lb8;
    @FXML
    private Label lb2;
    @FXML
    private Label lb4;
    @FXML
    private Label lb6;
    @FXML
    private Button btnVoltar;
    @FXML
    private JFXComboBox<String> cmbBoxFuncao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbBoxFuncao.getItems().addAll("Admin", "Servidor");
    }    

    @FXML
    private void salvarCadastro(ActionEvent event) {
        if(cmpMatricula.getText().isEmpty() || cmpNome.getText().isEmpty() || cmpEmail.getText().isEmpty() 
                || cmpFone.getText().isEmpty() || cmpSetor.getText().isEmpty() || cmpCpf.getText().isEmpty() || cmbBoxFuncao.getValue()==null){
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            if(cmpMatricula.getText().isEmpty()){
                lb1.setVisible(true);
            }else{
                lb1.setVisible(false);
            }
            if(cmpNome.getText().isEmpty()){
                lb2.setVisible(true);
            }else{
                lb2.setVisible(false);
            }
            if(cmpCpf.getText().isEmpty()){
                lb3.setVisible(true);
            }else{
                lb3.setVisible(false);
            } 
            if(cmpEmail.getText().isEmpty()){
                lb4.setVisible(true);
            }else{
                lb4.setVisible(false);
            }
            if(cmpFone.getText().isEmpty()){
                lb5.setVisible(true);
            }else{
                lb5.setVisible(false);
            }
            if(cmpSetor.getText().isEmpty()){
                lb6.setVisible(true);
            }else{
                lb6.setVisible(false);
            }
            if(cmbBoxFuncao.getValue()==null){
                lb8.setVisible(true);
            }else{
                lb8.setVisible(false);
            }
        }else{
            int matricula = Integer.parseInt(cmpMatricula.getText());
            String nome = cmpNome.getText();
            String cpf = cmpCpf.getText();
            String email = cmpEmail.getText();
            String fone = cmpFone.getText();
            String setor = cmpSetor.getText();
            String funcao = cmbBoxFuncao.getValue();
            System.out.println(cmbBoxFuncao.getValue());
            Servidor serv = new Servidor(matricula, nome, email, cpf, fone, setor, funcao);
            ServidorDao dao = new ServidorDao();
            dao.updateServidor(serv);
            limparCampos(event);
            JOptionPane.showMessageDialog(null, "Registro feito com sucesso");
        }
        
    }

    @FXML
    private void limparCampos(ActionEvent event) {
        lb1.setVisible(false);
        lb2.setVisible(false);
        lb3.setVisible(false);
        lb4.setVisible(false);
        lb5.setVisible(false);
        lb6.setVisible(false);
        lb8.setVisible(false);
        
        cmpMatricula.setText("");
        cmpNome.setText("");
        cmpCpf.setText("");
        cmpEmail.setText("");
        cmpFone.setText("");
        cmpSetor.setText("");
        cmbBoxFuncao.setValue(null);
    }

    @FXML
    private void voltarSistema(ActionEvent event) throws IOException {
        Main.getStage().close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sistemaatendcgae/view/FXMLTelaSistema.fxml"));
        Scene scene = new Scene(root);
        Main.setStage(stage);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
    
}
