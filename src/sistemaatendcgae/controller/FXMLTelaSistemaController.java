/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.controller;

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
import javafx.stage.Stage;
import sistemaatendcgae.model.domain.Main;

/**
 * FXML Controller class
 *
 * @author Neto
 */
public class FXMLTelaSistemaController implements Initializable {

    @FXML
    private Button btnLogout;
    @FXML
    private Button btnGerarRel1;
    @FXML
    private Button btnAddServidor;
    @FXML
    private Button btnGerarRel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void deslogarSistema(ActionEvent event) {
    }

    @FXML
    private void gerarRelatorio(ActionEvent event) {
    }

    @FXML
    private void adcServidor(ActionEvent event) {
        try {
                Main.getStage().close();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/sistemaatendcgae/view/FXMLTelaCadastroServidor.fxml"));
                Scene scene = new Scene(root);
                Main.setStage(stage);
                //stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                Logger.getLogger(FXMLTelaPrincipalController.class.getName()).log(Level.SEVERE, null, e);
            }
    }
    
}
