/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
import sistemaatendcgae.model.domain.Publico;
import sistemaatendcgae.model.domain.Servidor;
import static sistemaatendcgae.model.domain.Main.setStage;

/**
 * FXML Controller class
 *
 * @author Neto
 */
public class FXMLTelaCadastroServidorController implements Initializable {

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
    private Label lb8;
    @FXML
    private Label lb2;
    @FXML
    private Label lb4;
    @FXML
    private Label lb6;
    @FXML
    private JFXTextField cmpFuncao;
    @FXML
    private Label lb9;
    @FXML
    private Button btnVoltar;
    @FXML
    private JFXComboBox<String> cmbBoxFuncao;
    public boolean normal = false;
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
        if(cmpMatricula.getText().isEmpty() || cmpNome.getText().isEmpty() || cmpEmail.getText().isEmpty() || cmpSenha.getText().isEmpty() || cmpCsenha.getText().isEmpty()
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
            if(cmpSenha.getText().equals(cmpCsenha.getText())){
                int matricula = Integer.parseInt(cmpMatricula.getText());
                String nome = cmpNome.getText();
                String cpf = cmpCpf.getText();
                String email = cmpEmail.getText();
                String senha = cmpSenha.getText();
                String fone = cmpFone.getText();
                String setor = cmpSetor.getText();
                String funcao = cmbBoxFuncao.getValue();
                Servidor serv = new Servidor(matricula, nome, email, cpf, senha, fone, setor, funcao);
                ServidorDao dao = new ServidorDao();
                dao.registrarServidor(serv);
                limparCampo();
                JOptionPane.showMessageDialog(null, "Registro feito com sucesso");
            }else{
                JOptionPane.showMessageDialog(null, "A senha digitada não confere com a senha confirmada.");
            }
            
           
            
        }
    }

    @FXML
    private void limparCampos(ActionEvent event) {
        limparCampo();
        
    }
    public void limparCampo(){
        lb1.setVisible(false);
        lb2.setVisible(false);
        lb3.setVisible(false);
        lb4.setVisible(false);
        lb5.setVisible(false);
        lb6.setVisible(false);
        lb7.setVisible(false);
        lb8.setVisible(false);
        lb9.setVisible(false);
        
        cmpMatricula.setText("");
        cmpNome.setText("");
        cmpCpf.setText("");
        cmpEmail.setText("");
        cmpSenha.setText("");
        cmpCsenha.setText("");
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
    
    
    

    public Pane getPnTitulo() {
        return pnTitulo;
    }

    public void setPnTitulo(Pane pnTitulo) {
        this.pnTitulo = pnTitulo;
    }

    public AnchorPane getApTabCad() {
        return apTabCad;
    }

    public void setApTabCad(AnchorPane apTabCad) {
        this.apTabCad = apTabCad;
    }

    public Button getBtnSalvar() {
        return btnSalvar;
    }

    public void setBtnSalvar(Button btnSalvar) {
        this.btnSalvar = btnSalvar;
    }

    public Button getBtnLimpar() {
        return btnLimpar;
    }

    public void setBtnLimpar(Button btnLimpar) {
        this.btnLimpar = btnLimpar;
    }

    public JFXTextField getCmpMatricula() {
        return cmpMatricula;
    }

    public void setCmpMatricula(JFXTextField cmpMatricula) {
        this.cmpMatricula = cmpMatricula;
    }

    public JFXTextField getCmpNome() {
        return cmpNome;
    }

    public void setCmpNome(JFXTextField cmpNome) {
        this.cmpNome = cmpNome;
    }

    public JFXTextField getCmpCpf() {
        return cmpCpf;
    }

    public void setCmpCpf(JFXTextField cmpCpf) {
        this.cmpCpf = cmpCpf;
    }

    public JFXTextField getCmpEmail() {
        return cmpEmail;
    }

    public void setCmpEmail(JFXTextField cmpEmail) {
        this.cmpEmail = cmpEmail;
    }

    public JFXTextField getCmpFone() {
        return cmpFone;
    }

    public void setCmpFone(JFXTextField cmpFone) {
        this.cmpFone = cmpFone;
    }

    public JFXTextField getCmpSetor() {
        return cmpSetor;
    }

    public void setCmpSetor(JFXTextField cmpSetor) {
        this.cmpSetor = cmpSetor;
    }

    public JFXPasswordField getCmpSenha() {
        return cmpSenha;
    }

    public void setCmpSenha(JFXPasswordField cmpSenha) {
        this.cmpSenha = cmpSenha;
    }

    public JFXPasswordField getCmpCsenha() {
        return cmpCsenha;
    }

    public void setCmpCsenha(JFXPasswordField cmpCsenha) {
        this.cmpCsenha = cmpCsenha;
    }

    public Label getLb1() {
        return lb1;
    }

    public void setLb1(Label lb1) {
        this.lb1 = lb1;
    }

    public Label getLb3() {
        return lb3;
    }

    public void setLb3(Label lb3) {
        this.lb3 = lb3;
    }

    public Label getLb5() {
        return lb5;
    }

    public void setLb5(Label lb5) {
        this.lb5 = lb5;
    }

    public Label getLb7() {
        return lb7;
    }

    public void setLb7(Label lb7) {
        this.lb7 = lb7;
    }

    public Label getLb8() {
        return lb8;
    }

    public void setLb8(Label lb8) {
        this.lb8 = lb8;
    }

    public Label getLb2() {
        return lb2;
    }

    public void setLb2(Label lb2) {
        this.lb2 = lb2;
    }

    public Label getLb4() {
        return lb4;
    }

    public void setLb4(Label lb4) {
        this.lb4 = lb4;
    }

    public Label getLb6() {
        return lb6;
    }

    public void setLb6(Label lb6) {
        this.lb6 = lb6;
    }

    public boolean isNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }

   

    
    
    
}
    
