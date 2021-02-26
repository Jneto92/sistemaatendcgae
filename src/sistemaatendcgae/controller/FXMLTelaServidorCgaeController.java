/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import sistemaatendcgae.Dao.AtendimentoDao;
import sistemaatendcgae.Dao.PublicoDao;
import sistemaatendcgae.model.domain.Publico;
import static sistemaatendcgae.controller.FXMLTelaLoginController.userLogado;
import sistemaatendcgae.model.domain.Arquivos;
import sistemaatendcgae.model.domain.Atendimento;
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
    private TableView<Atendimento> tabelaAtendimento;
    @FXML
    private TableColumn<Atendimento, String> colSenha;
    @FXML
    private TableColumn<Atendimento, String> colNome;
    @FXML
    private TableColumn<Atendimento, String> colTipo;
    
    private Atendimento atselecionada;
    private boolean ocupado;
    private Atendimento atendimentoAt;
    private int senhaAt=0;
    private List<Atendimento> listAtendimento;
    private ObservableList<Atendimento> observableAtendimento;
    private final AtendimentoDao atendimentoDao = new AtendimentoDao();
    @FXML
    private Button btnLogout1;
    @FXML
    private Button btnLogout11;
    @FXML
    private Label lbMatricula11112;
    @FXML
    private JFXTextField cmpFuncao;
    @FXML
    private Label lbMatricula112;
    @FXML
    private Button btnEncerrarAt;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private JFXTextField cmpSenhaAt;
    @FXML
    private JFXTextField cmpNomeAt;
    @FXML
    private JFXTextField cmpEmailAt;
    @FXML
    private JFXTextField cmpTipoAt;
    @FXML
    private JFXTextArea cmpObservacoesAt;
    @FXML
    private Button btnCancelarAt;
    @FXML
    private TableView<Atendimento> tabelaAtEncerrados;
    @FXML
    private TableColumn<Atendimento, String> colSenha1;
    @FXML
    private TableColumn<Atendimento, String> colNome1;
    @FXML
    private TableColumn<Atendimento, String> colEmail1;
    @FXML
    private TableColumn<Atendimento, String> colServidor1;
    @FXML
    private TableColumn<Atendimento, String> colTipo1;
    @FXML
    private JFXTabPane tabPane1;
    @FXML
    private Tab tabPerfil;
    @FXML
    private Button btnSistema;
    
    
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
        tabelaAtendimento.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                atselecionada = (Atendimento) newValue;
            }
        });
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
            cmpFuncao.setText(rs.getString("funcao"));
            rs.close();
            stmt.close();
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

    @FXML
    private void deslogarSistema(ActionEvent event) throws IOException, ParseException {
        if(ocupado==true){
            int input = JOptionPane.showConfirmDialog(null, "Você possui um atendimento em andamento. \n"
                    + "clique Sim para encerrar o atendimento atual e delogar. \n"
                    + "Clique em Não para cancelar o atendimento e deslogar. \n"
                    + "Clique em Cancelar para voltar a tela do servidor.", "Alterar Senha", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE );
            System.out.println(input);
            if(input==0){
                encerrarAtendimento(event);
                limparCampos();
        
                logout();
            }
            if(input==1){
                cancelarAtendimento(event);
                limparCampos();
                logout();
            }else{
                
            } 
        }else{
            limparCampos();
            logout();
            
        }
        
    }
    private void logout() throws IOException{
        Main.getStage().close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sistemaatendcgae/view/FXMLTelaPrincipal2.fxml"));
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
        //JOptionPane.showMessageDialog(null, "Carrega");
        colSenha.setCellValueFactory(new PropertyValueFactory<>("senha_atendimento"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));//coluna para setar na table view
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoAtendimento"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        listAtendimento = atendimentoDao.lista();
        observableAtendimento = FXCollections.observableArrayList(listAtendimento);
        tabelaAtendimento.setItems(observableAtendimento);
        
        
        
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
    private void limparCamposAtend(){
        cmpEmailAt.setText("");
        cmpNomeAt.setText("");
        cmpTipoAt.setText("");
        cmpSenhaAt.setText("");
        ocupado=false;
        senhaAt=0;
    }
    
    @FXML
    private void atualizarLista(ActionEvent event) {
        carregarLista(event);
    }

    @FXML
    private void atenderSenha(ActionEvent event) throws ParseException {
        if(atselecionada != null){
            if(ocupado==true){
                JOptionPane.showMessageDialog(null, "Você já possui um atendimento em andamento, finalize para prosseguir com outro!");
            }else{
                ocupado=true;
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
                System.out.println(atselecionada.getSenha_atendimento());
                Atendimento at = new Atendimento(atselecionada.getSenha_atendimento(), dataFormatada, horaFormatada, "Em andamento");
                AtendimentoDao atDao = new AtendimentoDao();
                senhaAt = (int)atselecionada.getSenha_atendimento();
                atDao.updateStatusAtendimento(at);
                carregarAtendimento(event);

                carregarLista(event);
            }
            
            
            
            
            
        }else{
            JOptionPane.showMessageDialog(null, "Selecione alguma senha para poder prosseguir com o atendimento!");
        }
        
        
    }
    @FXML
    private void encerrarAtendimento(ActionEvent event) throws ParseException {
        //ocupado=true;
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
        Atendimento at = new Atendimento(senhaAt, dataFormatada, horaFormatada, "Encerrada", Integer.parseInt(cmpMatricula.getText()), cmpNome.getText(), cmpObservacoesAt.getText());
        AtendimentoDao atDao = new AtendimentoDao();
        atDao.encerrarAtendimento(at);
        limparCamposAtend();
        
    }

    @FXML
    private void carregarAtendimento(Event event) {
        String sql = "SELECT * FROM tabela_atendimento WHERE senha_atendimento="+senhaAt+"";
        try {
            Connection conn = this.conectar();
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            cmpSenhaAt.setText(rs.getString("senha_atendimento"));
            cmpNomeAt.setText(rs.getString("fk_publico_nome"));
            cmpEmailAt.setText(rs.getString("fk_publico_email"));
            cmpTipoAt.setText(rs.getString("tipoAtendimento"));
            
            rs.close();
            stmt.close();
           
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Nenhuma atendimento em andamento!");
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void cancelarAtendimento(ActionEvent event) throws ParseException {
        Atendimento at = new Atendimento(senhaAt, "", "", "Na fila");
        AtendimentoDao atDao = new AtendimentoDao();
        atDao.updateStatusAtendimento(at);
        limparCamposAtend();
    }
    
    @FXML
    private void mostrarEncerrados(Event event) {
        colSenha1.setCellValueFactory(new PropertyValueFactory<>("senha_atendimento"));
        colNome1.setCellValueFactory(new PropertyValueFactory<>("nome"));//coluna para setar na table view
        colTipo1.setCellValueFactory(new PropertyValueFactory<>("tipoAtendimento"));
        colEmail1.setCellValueFactory(new PropertyValueFactory<>("email"));
        colServidor1.setCellValueFactory(new PropertyValueFactory<>("servidor"));
        listAtendimento = atendimentoDao.listaEnc();
        observableAtendimento = FXCollections.observableArrayList(listAtendimento);
        tabelaAtEncerrados.setItems(observableAtendimento);
    }
    
    
    private void gerarRelatorio(ActionEvent event) {
        listAtendimento = atendimentoDao.listaCompleta();
        Arquivos ar = new Arquivos();
        String sql = "SELECT * FROM tabela_atendimento";
        try {
            Connection conn = this.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Atendimento at = new Atendimento();
                at.setSenha_atendimento(resultado.getInt("senha_atendimento"));
                at.setNome(resultado.getString("fk_publico_nome"));
                at.setTipoAtendimento(resultado.getString("tipoAtendimento"));
                at.setEmail(resultado.getString("fk_publico_email"));
                at.setMatriculaServ(resultado.getInt("fk_servidor_matricula"));
                at.setServidor(resultado.getString("fk_servidor_nome"));
                at.setData_solicitacao(resultado.getString("data_solicitacao"));
                at.setHora_solicitacao(resultado.getString("hora_solicitacao"));
                at.setData_atendimento(resultado.getString("data_atendimento"));
                at.setHora_atendimento(resultado.getString("hora_atendimento"));
                at.setData_encerramento(resultado.getString("data_encerramento"));
                at.setHora_encerramento(resultado.getString("hora_encerramento"));
                at.setObservacao(resultado.getString("observacoes"));
                at.setStatus(resultado.getString("status"));
                
                System.out.println(at.getEmail());
                ar.criarArquivo(at);
                
            }
        } catch (Exception e) {
        }
        
    }
    
    private void adcServidor(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sistemaatendcgae/view/FXMLTelaCadastroServidor.fxml"));
            Scene scene = new Scene(root);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(FXMLTelaPrincipalController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    @FXML
    private void mostrarPerfil(Event event) {
    }
    
    
    @FXML
    private void acesssarSistema(ActionEvent event) {
        if(cmpFuncao.getText().equals("Admin")){
            JOptionPane.showMessageDialog(null, "Você tem permissão!");
        }else{
            JOptionPane.showMessageDialog(null, "Você não tem permissão!");
        }
    }
    
    
    //geters e seters
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

    public TableView<Atendimento> getTabelaAtendimento() {
        return tabelaAtendimento;
    }

    public void setTabelaAtendimento(TableView<Atendimento> tabelaAtendimento) {
        this.tabelaAtendimento = tabelaAtendimento;
    }

    public TableColumn<Atendimento, String> getColSenha() {
        return colSenha;
    }

    public void setColSenha(TableColumn<Atendimento, String> colSenha) {
        this.colSenha = colSenha;
    }

    public TableColumn<Atendimento, String> getColNome() {
        return colNome;
    }

    public void setColNome(TableColumn<Atendimento, String> colNome) {
        this.colNome = colNome;
    }

    public TableColumn<Atendimento, String> getColTipo() {
        return colTipo;
    }

    public void setColTipo(TableColumn<Atendimento, String> colTipo) {
        this.colTipo = colTipo;
    }

    public Atendimento getAtselecionada() {
        return atselecionada;
    }

    public void setAtselecionada(Atendimento atselecionada) {
        this.atselecionada = atselecionada;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Atendimento getAtendimentoAt() {
        return atendimentoAt;
    }

    public void setAtendimentoAt(Atendimento atendimentoAt) {
        this.atendimentoAt = atendimentoAt;
    }

    public int getSenhaAt() {
        return senhaAt;
    }

    public void setSenhaAt(int senhaAt) {
        this.senhaAt = senhaAt;
    }

    public List<Atendimento> getListAtendimento() {
        return listAtendimento;
    }

    public void setListAtendimento(List<Atendimento> listAtendimento) {
        this.listAtendimento = listAtendimento;
    }

    public ObservableList<Atendimento> getObservableAtendimento() {
        return observableAtendimento;
    }

    public void setObservableAtendimento(ObservableList<Atendimento> observableAtendimento) {
        this.observableAtendimento = observableAtendimento;
    }

    public Button getBtnLogout1() {
        return btnLogout1;
    }

    public void setBtnLogout1(Button btnLogout1) {
        this.btnLogout1 = btnLogout1;
    }

    public Button getBtnLogout11() {
        return btnLogout11;
    }

    public void setBtnLogout11(Button btnLogout11) {
        this.btnLogout11 = btnLogout11;
    }

    public Label getLbMatricula11112() {
        return lbMatricula11112;
    }

    public void setLbMatricula11112(Label lbMatricula11112) {
        this.lbMatricula11112 = lbMatricula11112;
    }

    public JFXTextField getCmpFuncao() {
        return cmpFuncao;
    }

    public void setCmpFuncao(JFXTextField cmpFuncao) {
        this.cmpFuncao = cmpFuncao;
    }

    public Label getLbMatricula112() {
        return lbMatricula112;
    }

    public void setLbMatricula112(Label lbMatricula112) {
        this.lbMatricula112 = lbMatricula112;
    }

    public Button getBtnEncerrarAt() {
        return btnEncerrarAt;
    }

    public void setBtnEncerrarAt(Button btnEncerrarAt) {
        this.btnEncerrarAt = btnEncerrarAt;
    }

    public TableColumn<?, ?> getColEmail() {
        return colEmail;
    }

    public void setColEmail(TableColumn<?, ?> colEmail) {
        this.colEmail = colEmail;
    }

    public JFXTextField getCmpSenhaAt() {
        return cmpSenhaAt;
    }

    public void setCmpSenhaAt(JFXTextField cmpSenhaAt) {
        this.cmpSenhaAt = cmpSenhaAt;
    }

    public JFXTextField getCmpNomeAt() {
        return cmpNomeAt;
    }

    public void setCmpNomeAt(JFXTextField cmpNomeAt) {
        this.cmpNomeAt = cmpNomeAt;
    }

    public JFXTextField getCmpEmailAt() {
        return cmpEmailAt;
    }

    public void setCmpEmailAt(JFXTextField cmpEmailAt) {
        this.cmpEmailAt = cmpEmailAt;
    }

    public JFXTextField getCmpTipoAt() {
        return cmpTipoAt;
    }

    public void setCmpTipoAt(JFXTextField cmpTipoAt) {
        this.cmpTipoAt = cmpTipoAt;
    }

    public JFXTextArea getCmpObservacoesAt() {
        return cmpObservacoesAt;
    }

    public void setCmpObservacoesAt(JFXTextArea cmpObservacoesAt) {
        this.cmpObservacoesAt = cmpObservacoesAt;
    }

    public Button getBtnCancelarAt() {
        return btnCancelarAt;
    }

    public void setBtnCancelarAt(Button btnCancelarAt) {
        this.btnCancelarAt = btnCancelarAt;
    }

    public TableView<Atendimento> getTabelaAtEncerrados() {
        return tabelaAtEncerrados;
    }

    public void setTabelaAtEncerrados(TableView<Atendimento> tabelaAtEncerrados) {
        this.tabelaAtEncerrados = tabelaAtEncerrados;
    }

    public TableColumn<Atendimento, String> getColSenha1() {
        return colSenha1;
    }

    public void setColSenha1(TableColumn<Atendimento, String> colSenha1) {
        this.colSenha1 = colSenha1;
    }

    public TableColumn<Atendimento, String> getColNome1() {
        return colNome1;
    }

    public void setColNome1(TableColumn<Atendimento, String> colNome1) {
        this.colNome1 = colNome1;
    }

    public TableColumn<Atendimento, String> getColEmail1() {
        return colEmail1;
    }

    public void setColEmail1(TableColumn<Atendimento, String> colEmail1) {
        this.colEmail1 = colEmail1;
    }

    public TableColumn<Atendimento, String> getColServidor1() {
        return colServidor1;
    }

    public void setColServidor1(TableColumn<Atendimento, String> colServidor1) {
        this.colServidor1 = colServidor1;
    }

    public TableColumn<Atendimento, String> getColTipo1() {
        return colTipo1;
    }

    public void setColTipo1(TableColumn<Atendimento, String> colTipo1) {
        this.colTipo1 = colTipo1;
    }

    

    

    

    

    

    

    

    

    
    
    
    
    
}
