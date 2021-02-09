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
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import sistemaatendcgae.Dao.PublicoDao;
import sistemaatendcgae.model.domain.Publico;
import static sistemaatendcgae.controller.FXMLTelaLoginController.userLogado;
import sistemaatendcgae.model.domain.Main;

/**
 * FXML Controller class
 *
 * @author Neto
 */
public class FXMLTelaServidorCgaeController implements Initializable {

    String senhaLogada;
    @FXML
    private Label lbMatricula;
    @FXML
    private JFXTextField cmpMatricula;
    @FXML
    private Label lbMatricula1;
    @FXML
    private Label lbMatricula11;
    @FXML
    private Button btnLogout;
    @FXML
    private JFXTextField cmpNome;
    @FXML
    private JFXTextField cmpEmail;
    @FXML
    private Label lbMatricula111;
    @FXML
    private JFXTextField cmpTelefone;
    @FXML
    private Label lbMatricula1111;
    @FXML
    private JFXTextField cmpSetor;
    @FXML
    private Button btnAltSenha;
    @FXML
    private JFXPasswordField novaSenha;
    @FXML
    private TableView<Publico> tabelaAtendimento;
    @FXML
    private TableColumn<Publico, String> colSenha;
    @FXML
    private TableColumn<Publico, String> colNome;
    @FXML
    private TableColumn<Publico, String> colTipo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // TODO
        FXMLTelaLoginController a = new FXMLTelaLoginController();
        //System.out.println(FXMLTelaLoginController.userLogado);
        setarPerfil();
        //initTable();
        //cmpMatricula.setText(a.cmpLogin.getText());
    }    
    private Connection conectar(){ 
        String url = "jdbc:sqlite:C:/Users/NETO/Documents/NetBeansProjects/SistemaAtendCgae/src/banco_de_dados/banco_sqlite.db";
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void setarPerfil(){
        String sql = "SELECT * FROM tabela_servidor WHERE matricula="+FXMLTelaLoginController.userLogado+"";
        try {
            Connection conn = this.conectar();
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            cmpMatricula.setText(rs.getString("matricula"));
            cmpNome.setText(rs.getString("nome"));
            cmpEmail.setText(rs.getString("email"));
            cmpSetor.setText(rs.getString("setor"));
            cmpTelefone.setText("telefone");
            senhaLogada = rs.getString("senha_acesso");
            rs.close();
            stmt.close();
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Label getLbMatricula() {
        return lbMatricula;
    }

    public void setLbMatricula(Label lbMatricula) {
        this.lbMatricula = lbMatricula;
    }

    public JFXTextField getCmpMatricula() {
        return cmpMatricula;
    }

    public void setCmpMatricula(JFXTextField cmpMatricula) {
        this.cmpMatricula = cmpMatricula;
    }

    @FXML
    private void deslogarSistema(ActionEvent event) throws IOException {
        
        limparCampos();
        
        Main.getStage().close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sistemaatendcgae/view/FXMLTelaPrincipal.fxml"));
        Scene scene = new Scene(root);
        Main.setStage(stage);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void alterarSenha(ActionEvent event) {
        String sql = "UPDATE tabela_servidor SET senha_acesso=? WHERE matricula="+FXMLTelaLoginController.userLogado+"";
        
        try {
            Connection conn = this.conectar();
            
            
            if (!senhaLogada.equals(novaSenha.getText())) {
                int input = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja alterar a senha antiga?", "Alterar Senha", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
                if(input==1){
                   
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, novaSenha.getText());

                    pstmt.executeUpdate();
                    pstmt.close();
                    //System.out.println("Alterado para "+novaSenha.getText());
                    novaSenha.setText("");
                    JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
                }else{
                    novaSenha.setText("");
                }
                
           }else{
                JOptionPane.showMessageDialog(null, "A nova senha é igual a senha antiga");
                novaSenha.setText("");
            }
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    @FXML
    private void carregarLista(Event event) {
        JOptionPane.showMessageDialog(null, "Carrega");
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colSenha.setCellValueFactory(new PropertyValueFactory("email"));
        colTipo.setCellValueFactory(new PropertyValueFactory("tipoAtendimento"));
        tabelaAtendimento.setItems(atualizaTabela());
        
        
    }
   
   
    private void limparCampos(){
        
        FXMLTelaLoginController.userLogado = 0;
        cmpMatricula.setText("");
        cmpNome.setText("");
        cmpEmail.setText("");
        cmpTelefone.setText("");
        cmpSetor.setText("");
        senhaLogada = "";
    }    
    public ObservableList<Publico> atualizaTabela(){
        String sql = "SELECT * FROM tabela_publico";
        try {
            
            Connection conn = this.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int i = 0;
            System.out.println(rs.getString("nome"));
            while (rs.next()) {                
                
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String tipo = rs.getString("tipoAtendimento");
                String status = rs.getString("status");
                Publico p1 = new Publico(nome, email, tipo);
                System.out.println(i);
                
                return FXCollections.observableArrayList(p1);
                
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        //Publico p = new PublicoDao();
        return null;
    }
    public void mostrarDados(){
        String sql = "SELECT * FROM tabela_publico";
        try {
            Connection conn = this.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getSenhaLogada() {
        return senhaLogada;
    }

    public void setSenhaLogada(String senhaLogada) {
        this.senhaLogada = senhaLogada;
    }

    public Label getLbMatricula1() {
        return lbMatricula1;
    }

    public void setLbMatricula1(Label lbMatricula1) {
        this.lbMatricula1 = lbMatricula1;
    }

    public Label getLbMatricula11() {
        return lbMatricula11;
    }

    public void setLbMatricula11(Label lbMatricula11) {
        this.lbMatricula11 = lbMatricula11;
    }

    public Button getBtnLogout() {
        return btnLogout;
    }

    public void setBtnLogout(Button btnLogout) {
        this.btnLogout = btnLogout;
    }

    public JFXTextField getCmpNome() {
        return cmpNome;
    }

    public void setCmpNome(JFXTextField cmpNome) {
        this.cmpNome = cmpNome;
    }

    public JFXTextField getCmpEmail() {
        return cmpEmail;
    }

    public void setCmpEmail(JFXTextField cmpEmail) {
        this.cmpEmail = cmpEmail;
    }

    public Label getLbMatricula111() {
        return lbMatricula111;
    }

    public void setLbMatricula111(Label lbMatricula111) {
        this.lbMatricula111 = lbMatricula111;
    }

    public JFXTextField getCmpTelefone() {
        return cmpTelefone;
    }

    public void setCmpTelefone(JFXTextField cmpTelefone) {
        this.cmpTelefone = cmpTelefone;
    }

    public Label getLbMatricula1111() {
        return lbMatricula1111;
    }

    public void setLbMatricula1111(Label lbMatricula1111) {
        this.lbMatricula1111 = lbMatricula1111;
    }

    public JFXTextField getCmpSetor() {
        return cmpSetor;
    }

    public void setCmpSetor(JFXTextField cmpSetor) {
        this.cmpSetor = cmpSetor;
    }

    public Button getBtnAltSenha() {
        return btnAltSenha;
    }

    public void setBtnAltSenha(Button btnAltSenha) {
        this.btnAltSenha = btnAltSenha;
    }

    public JFXPasswordField getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(JFXPasswordField novaSenha) {
        this.novaSenha = novaSenha;
    }

    public TableView<Publico> getTabelaAtendimento() {
        return tabelaAtendimento;
    }

    public void setTabelaAtendimento(TableView<Publico> tabelaAtendimento) {
        this.tabelaAtendimento = tabelaAtendimento;
    }

    public TableColumn<Publico, String> getColSenha() {
        return colSenha;
    }

    public void setColSenha(TableColumn<Publico, String> colSenha) {
        this.colSenha = colSenha;
    }

    public TableColumn<Publico, String> getColNome() {
        return colNome;
    }

    public void setColNome(TableColumn<Publico, String> colNome) {
        this.colNome = colNome;
    }

    public TableColumn<Publico, String> getColTipo() {
        return colTipo;
    }

    public void setColTipo(TableColumn<Publico, String> colTipo) {
        this.colTipo = colTipo;
    }
    
    
    
    
}
