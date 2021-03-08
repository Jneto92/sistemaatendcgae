/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.controller;

import java.io.IOException;
import static java.lang.reflect.Array.get;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import sistemaatendcgae.Dao.AtendimentoDao;
import sistemaatendcgae.Dao.PublicoDao;
import sistemaatendcgae.model.domain.Atendimento;
import sistemaatendcgae.model.domain.Publico;
import sistemaatendcgae.model.domain.TipoAtendimento;



/**
 * FXML Controller class
 *
 * @author NETO
 */
public class FXMLTelaPublicoController implements Initializable {

    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField campoCpf;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoEmail;
    @FXML
    private ComboBox selecionarTipo;
    @FXML
    public Label campoObg1;
    @FXML
    private Label campoObg2;
    @FXML
    private Label campoObg3;
    @FXML
    public Label campoObg4;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        selecionarTipo.getItems().addAll("Monitoria", "Auxilios", "Pincel", "Psicologa", "outros");
        System.out.println(selecionarTipo.getValue());
        
        
    }    
    

    @FXML
    private void registrarAtendimento(ActionEvent event) throws ParseException {
        
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dataFormatada = formatterData.format(agora);

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = formatterHora.format(agora);
        
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
        Time hora = new Time(format2.parse(horaFormatada).getTime());
        //System.out.println(hora);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date(format.parse(dataFormatada).getTime());
        //System.out.println(data);
        
        if(campoCpf.getText().isEmpty() || campoNome.getText().isEmpty() || campoEmail.getText().isEmpty() || selecionarTipo.getValue()==null){
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            if(campoCpf.getText().isEmpty()){
                campoObg1.setVisible(true);
            }else{
                campoObg1.setVisible(false);
            }
            if(campoNome.getText().isEmpty()){
                campoObg2.setVisible(true);
            }else{
                campoObg2.setVisible(false);
            }
            if(campoEmail.getText().isEmpty()){
                campoObg3.setVisible(true);
            }else{
                campoObg3.setVisible(false);
            }    
        }else{
            
            
            
            
            
            String cpf = campoCpf.getText();
            String nome = campoNome.getText();
            String email = campoEmail.getText();
            String tipo = selecionarTipo.getValue().toString();
            String status = "Na fila";
            Publico pub = new Publico(cpf, nome, email);
            PublicoDao dao = new PublicoDao();
            dao.solicitarAtendimento(pub);
            Atendimento at = new Atendimento(nome, email, status, tipo, dataFormatada, horaFormatada);
            AtendimentoDao aDao = new AtendimentoDao();
            aDao.solicitarAtendimento(at);
            JOptionPane.showMessageDialog(null, "Registro feito com sucesso");
            campoObg1.setVisible(false);
            campoObg2.setVisible(false);
            campoObg3.setVisible(false);
            limparCampos(event);
        }
        
        
    }
    
    @FXML
    private void limparCampos(ActionEvent event) {
        campoCpf.setText("");
        campoNome.setText("");
        campoEmail.setText("");
        selecionarTipo.setValue(null);
        campoObg1.setVisible(false);
        campoObg2.setVisible(false);
        campoObg3.setVisible(false);
        
    }

    public Button getBtnConfirmar() {
        return btnConfirmar;
    }

    public void setBtnConfirmar(Button btnConfirmar) {
        this.btnConfirmar = btnConfirmar;
    }

    public Button getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(Button btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public TextField getCampoCpf() {
        return campoCpf;
    }

    public void setCampoCpf(TextField campoCpf) {
        this.campoCpf = campoCpf;
    }

    public TextField getCampoNome() {
        return campoNome;
    }

    public void setCampoNome(TextField campoNome) {
        this.campoNome = campoNome;
    }

    public TextField getCampoEmail() {
        return campoEmail;
    }

    public void setCampoEmail(TextField campoEmail) {
        this.campoEmail = campoEmail;
    }

    public ComboBox getSelecionarTipo() {
        return selecionarTipo;
    }

    public void setSelecionarTipo(ComboBox selecionarTipo) {
        this.selecionarTipo = selecionarTipo;
    }

    public Label getCampoObg1() {
        return campoObg1;
    }

    public void setCampoObg1(Label campoObg1) {
        this.campoObg1 = campoObg1;
    }

    public Label getCampoObg2() {
        return campoObg2;
    }

    public void setCampoObg2(Label campoObg2) {
        this.campoObg2 = campoObg2;
    }

    public Label getCampoObg3() {
        return campoObg3;
    }

    public void setCampoObg3(Label campoObg3) {
        this.campoObg3 = campoObg3;
    }

    public Label getCampoObg4() {
        return campoObg4;
    }

    public void setCampoObg4(Label campoObg4) {
        this.campoObg4 = campoObg4;
    }


    
    
    
    
    
}
