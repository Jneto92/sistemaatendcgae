/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import sistemaatendcgae.Dao.AtendimentoDao;
import sistemaatendcgae.Dao.CriarTabelaDao;
import sistemaatendcgae.Dao.ServidorDao;
import sistemaatendcgae.model.domain.Atendimento;
import sistemaatendcgae.model.domain.Main;
import sistemaatendcgae.model.domain.Servidor;

/**
 * FXML Controller class
 *
 * @author Neto
 */
public class FXMLTelaSistemaController implements Initializable {

    @FXML
    private Button btnAddServidor;
    @FXML
    private TableView<Servidor> tbServidor;
    @FXML
    private TableColumn<Servidor, Integer> colMatricula;
    @FXML
    private TableColumn<Servidor, String> colNome;
    @FXML
    private TableColumn<Servidor, String> colEmail;
    @FXML
    private TableColumn<Servidor, String> colFuncao;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnListaServidor;
    
    
    private Servidor svselecionada;
    private Servidor ServidorSv;
    private List<Servidor> listServidor;
    private ObservableList<Servidor> observableServidor;
    private final ServidorDao servidorDao = new ServidorDao();
    
    private Atendimento atselecionado;
    private Atendimento AtendimentoAt;
    private List<Atendimento> listAtendimento;
    private ObservableList<Atendimento> observableAtendimento;
    private final AtendimentoDao atendimentoDao = new AtendimentoDao();
    
    @FXML
    private Button btnUpdateSv;
    @FXML
    private Button btnDeletarServ;
    @FXML
    private Button btnResetarTbAt;
    @FXML
    private Button btnResetarSist;
    @FXML
    private Button btnListaAtendimento;
    private int visualizacao;
    @FXML
    private TableView<Atendimento> tbAtendimento;
    @FXML
    private TableView<?> tbServidor2;
    @FXML
    private TableColumn<?, ?> colMatricula2;
    @FXML
    private TableColumn<?, ?> colNome2;
    @FXML
    private TableColumn<?, ?> colEmail2;
    @FXML
    private TableColumn<?, ?> colFuncao2;
    @FXML
    private TableColumn<Atendimento, String> colSenha;
    @FXML
    private TableColumn<Atendimento, String> colNomeAt;
    @FXML
    private TableColumn<Atendimento, String> colEmailAt;
    @FXML
    private TableColumn<Atendimento, String> colTipo;
    @FXML
    private TableColumn<Atendimento, String> colStatus;
    @FXML
    private Button btnMudarStatus;
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbServidor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                svselecionada = (Servidor) newValue;
            }
        });
        
        tbAtendimento.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                atselecionado = (Atendimento) newValue;
            }
        });
        
    }    



    @FXML
    private void adcServidor(ActionEvent event) {
        try {
            FXMLTelaCadastroServidorController a = new FXMLTelaCadastroServidorController();
            a.normal = true;
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

    @FXML
    private void sairSistema(ActionEvent event) throws IOException {
        Main.getStage().close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sistemaatendcgae/view/FXMLTelaServidor.fxml"));
        Scene scene = new Scene(root);
        Main.setStage(stage);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gerarListaServidor(ActionEvent event) {
        
        visualizacao = 0;
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));//coluna para setar na table view
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        listServidor = servidorDao.lista();
        observableServidor = FXCollections.observableArrayList(listServidor);
        tbServidor.setItems(observableServidor);
    }

    @FXML
    private void updateServidor(ActionEvent event) {
         try {
                Main.getStage().close();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/sistemaatendcgae/view/FXMLTelaAtualizarServidor.fxml"));
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
    private void deletarServ(ActionEvent event) {
        if(svselecionada != null){
            //System.out.println(svselecionada.getMatricula());
            Servidor sv = new Servidor(svselecionada.getMatricula());
            ServidorDao daoSv = new ServidorDao();
            daoSv.deletarServidor(sv);
            gerarListaServidor(event);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione algum servidor para a exclusão.");
        }
        
        
    }

    @FXML
    private void resetarAtendimentos(ActionEvent event) {
        CriarTabelaDao ct = new  CriarTabelaDao();
        ct.deletarTbAtendimento();
        ct.tabelaAtendimento();
        
    }

    @FXML
    private void gerarListaAtendimento(ActionEvent event) {
        
        visualizacao = 1;
        colSenha.setCellValueFactory(new PropertyValueFactory<>("senha_atendimento"));
        colNomeAt.setCellValueFactory(new PropertyValueFactory<>("nome"));//coluna para setar na table view
        colEmailAt.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoAtendimento"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        listAtendimento = atendimentoDao.listaTodosStatus();
        observableAtendimento = FXCollections.observableArrayList(listAtendimento);
        tbAtendimento.setItems(observableAtendimento);
    }

    @FXML
    private void mudarStatusAtendimento(ActionEvent event) throws ParseException {
        if(atselecionado != null){
            if(atselecionado.getStatus().equals("Encerrada")){
                JOptionPane.showMessageDialog(null, "Você não pode mudar o status de um atendimento encerrado.");
            }else{
                
                String[] tipo = {"Na fila", "Ausente"};
                JComboBox<String> tp = new JComboBox<>(tipo);
                tp.setEditable(true);
                String[] option = {"Ok", "Cancelar"};
                int esc = JOptionPane.showOptionDialog(null, tp, "Mudar status do atendimento", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                String escolha = tp.getSelectedItem().toString();
                if(esc==0){
                    if(atselecionado.getStatus().equals(escolha)){
                    JOptionPane.showMessageDialog(null, "O atendimento já se encontra no status "+escolha);
                    }else{
                        if(escolha.equals("Na fila")){
                            Atendimento at = new Atendimento(atselecionado.getSenha_atendimento(), "", "", "Na fila");
                            AtendimentoDao atDao = new AtendimentoDao();
                            atDao.updateStatusAtendimento(at, 1);
                            gerarListaAtendimento(event);
                        }else if(escolha.equals("Ausente")){
                            Atendimento at = new Atendimento(atselecionado.getSenha_atendimento(), "", "", "Ausente");
                            AtendimentoDao atDao = new AtendimentoDao();
                            atDao.updateStatusAtendimento(at, 1);
                            gerarListaAtendimento(event);
                        }

                    }
                }else{
                    
                }
                
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Selecione algum atendimento para a mudança de status.");
        }
        
        
    } 
    
    
    @FXML
    private void resetarSistema(ActionEvent event) throws IOException {
        int input = JOptionPane.showConfirmDialog(null, "Você tem certeza que quer resetar o sistemas completamente?", "Resetar sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
        if(input==0){
            CriarTabelaDao ct = new  CriarTabelaDao();
            ct.deletarTbAtendimento();
            ct.deletarTbPublico();
            ct.deletarTbServidor();
            ct.tabelaServidor();
            ct.tabelaPublico();
            ct.tabelaAtendimento();
            Servidor admin = new Servidor(1861, "Admin", "netolima1992@gmail.com", "03581719118", "040510", "61991065677", "TSI-ADMIN", "Admin");
            ServidorDao svDao = new ServidorDao();
            svDao.registrarServidor(admin);

            Main.getStage().close();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sistemaatendcgae/view/FXMLTelaPrincipal2.fxml"));
            Scene scene = new Scene(root);
            Main.setStage(stage);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }else{
            
        }
        
    }
    
    
    
    
    
    
}
