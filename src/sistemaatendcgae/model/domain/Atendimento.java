/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.model.domain;


import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author NETO
 */
public class Atendimento {
    private int senha_atendimento;
    private String data_solicitacao;
    private String hora_solicitacao;
    private String data_atendimento;
    private String hora_atendimento;
    private String data_encerramento;
    private String hora_encerramento;
    private String status;
    private String nome;
    private String email;
    private String servidor;
    private int matriculaServ;
    private String tipoAtendimento;
    private String observacao;
    //contrutor para o servidor for encerrar o atendimento
    public Atendimento(int senha_atendimento, String data_encerramento, String hora_encerramento, String status, int matricula, String nomeServ, String observacoes) {
        this.senha_atendimento = senha_atendimento;
        this.data_encerramento = data_encerramento;
        this.hora_encerramento = hora_encerramento;
        this.status = status;
        this.matriculaServ = matricula;
        this.servidor = nomeServ;
        this.observacao = observacoes;
    }

    //Construtor para a solicitação de atendimento feito pelo publico
    public Atendimento(String nome, String email, String status, String tipo, String data_solicitacao,String hora_solicitacao) {
        
        this.data_solicitacao = data_solicitacao;
        this.status = status;
        this.nome = nome;
        this.email = email;
        this.tipoAtendimento = tipo;
        this.hora_solicitacao = hora_solicitacao;
    }
    //construtor para o servidor for atender a solicitação na fila 
    public Atendimento(int senha_atendimento, String data_atendimento, String hora_atendimento, String status) {
        this.senha_atendimento = senha_atendimento;
        this.data_atendimento = data_atendimento;
        this.hora_atendimento = hora_atendimento;
        this.status = status;
    }

    public Atendimento(int senha_atendimento, String data_solicitacao, String hora_solicitacao, String data_atendimento, String hora_atendimento, String data_encerramento, String hora_encerramento, String status, String nome, String email, String servidor, int matriculaServ, String tipoAtendimento, String observacao) {
        this.senha_atendimento = senha_atendimento;
        this.data_solicitacao = data_solicitacao;
        this.hora_solicitacao = hora_solicitacao;
        this.data_atendimento = data_atendimento;
        this.hora_atendimento = hora_atendimento;
        this.data_encerramento = data_encerramento;
        this.hora_encerramento = hora_encerramento;
        this.status = status;
        this.nome = nome;
        this.email = email;
        this.servidor = servidor;
        this.matriculaServ = matriculaServ;
        this.tipoAtendimento = tipoAtendimento;
        this.observacao = observacao;
    }
    
    public Atendimento() {
        
    }

    public int getMatriculaServ() {
        return matriculaServ;
    }

    public void setMatriculaServ(int matriculaServ) {
        this.matriculaServ = matriculaServ;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    

    public String getHora_atendimento() {
        return hora_atendimento;
    }

    public void setHora_atendimento(String hora_atendimento) {
        this.hora_atendimento = hora_atendimento;
    }

    public String getHora_encerramento() {
        return hora_encerramento;
    }

    public void setHora_encerramento(String hora_encerramento) {
        this.hora_encerramento = hora_encerramento;
    }

    
    public String getData_solicitacao() {
        return data_solicitacao;
    }

    public void setData_solicitacao(String data_solicitacao) {
        this.data_solicitacao = data_solicitacao;
    }

    public String getHora_solicitacao() {
        return hora_solicitacao;
    }

    public void setHora_solicitacao(String hora_solicitacao) {
        this.hora_solicitacao = hora_solicitacao;
    }

    public String getData_atendimento() {
        return data_atendimento;
    }

    public void setData_atendimento(String data_atendimento) {
        this.data_atendimento = data_atendimento;
    }

    public String getData_encerramento() {
        return data_encerramento;
    }

    public void setData_encerramento(String data_encerramento) {
        this.data_encerramento = data_encerramento;
    }

    

    public String getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(String tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public int getSenha_atendimento() {
        return senha_atendimento;
    }

    public void setSenha_atendimento(int senha_atendimento) {
        this.senha_atendimento = senha_atendimento;
    }

    
    
    
    
    
}
