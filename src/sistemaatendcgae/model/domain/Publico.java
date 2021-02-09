/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.model.domain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import javax.swing.JOptionPane;
import sistemaatendcgae.controller.FXMLTelaPublicoController;

/**
 *
 * @author NETO
 */
public class Publico {
    
    private int id_pessoa;
    private String cpf;
    private String nome;
    private String email;
    private String tipoAtendimento;
    

    public Publico(String cpf, String nome, String email, String tipoAtedimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.tipoAtendimento = tipoAtedimento;
        
        
    }
    public Publico(String nome, String email, String tipoAtedimento) {
        this.nome = nome;
        this.email = email;
        this.tipoAtendimento = tipoAtedimento;
        
        
    }
    public Publico() {
        
        
        
    }
    
    
    public String getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(String tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }
    
    
    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    
    
    
}
