/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.controller;

import conexoes.Conexao;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author NETO
 */
public class FXMLTelaPrincipalController implements Initializable {

    @FXML
    private AnchorPane painelEsq;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnPublico;
    @FXML
    private Button btnServidor;
    @FXML
    private AnchorPane painelDir;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        abrirHome("/sistemaatendcgae/view/FXMLTelaInicial.fxml", btnHome);
    }    

    @FXML
    private void paginaPublico(ActionEvent event) {
        abrirHome("/sistemaatendcgae/view/FXMLTelaPublico.fxml", btnPublico);
    }

    @FXML
    private void paginaServidor(ActionEvent event) {
        
        abrirHome("/sistemaatendcgae/view/FXMLTelaLogin.fxml", btnServidor);
            
        
    }
    public void paginaCadastro(){
        abrirHome("/sistemaatendcgae/view/FXMLTelaCadastroServidor.fxml", btnServidor);
        
    }
    @FXML
    private void paginaHome(ActionEvent event) throws IOException, Exception {
        abrirHome("/sistemaatendcgae/view/FXMLTelaInicial.fxml", btnHome);
        
    }
    public void abrirHome(String tela, Button btn){
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource(tela));
            AnchorPane.setTopAnchor(a, 0.0);
            AnchorPane.setLeftAnchor(a, 0.0);
            AnchorPane.setRightAnchor(a, 0.0);
            AnchorPane.setBottomAnchor(a, 0.0);
            painelDir.getChildren().setAll(a);
            btnServidor.setStyle("-fx-background-color: "+ "#aaaaaa"+";");
            btnPublico.setStyle("-fx-background-color: "+ "#aaaaaa"+";");
            btnHome.setStyle("-fx-background-color: "+ "#aaaaaa"+";");
            mudarCorBotao(btn);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLTelaPublicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void mudarCorBotao(Button btn){
        btn.setStyle("-fx-background-color: "+ "#ff0000"+";");
    }

    @FXML
    private void sair(ActionEvent event) {
        
        System.exit(0);
        
        
    }

    public AnchorPane getPainelEsq() {
        return painelEsq;
    }

    public void setPainelEsq(AnchorPane painelEsq) {
        this.painelEsq = painelEsq;
    }

    public ImageView getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(ImageView imgLogo) {
        this.imgLogo = imgLogo;
    }

    public Button getBtnPublico() {
        return btnPublico;
    }

    public void setBtnPublico(Button btnPublico) {
        this.btnPublico = btnPublico;
    }

    public Button getBtnServidor() {
        return btnServidor;
    }

    public void setBtnServidor(Button btnServidor) {
        this.btnServidor = btnServidor;
    }

    public AnchorPane getPainelDir() {
        return painelDir;
    }

    public void setPainelDir(AnchorPane painelDir) {
        this.painelDir = painelDir;
    }

    public Button getBtnHome() {
        return btnHome;
    }

    public void setBtnHome(Button btnHome) {
        this.btnHome = btnHome;
    }

    public Button getBtnSair() {
        return btnSair;
    }

    public void setBtnSair(Button btnSair) {
        this.btnSair = btnSair;
    }
    
    
    
}
