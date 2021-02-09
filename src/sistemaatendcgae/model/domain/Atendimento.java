/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.model.domain;

import java.sql.Date;

/**
 *
 * @author NETO
 */
public class Atendimento {
    private int senha_atendimento;
    private Date data_solicitacao;
    private Date data_atendimento;
    private Date data_encerramento;

    public Atendimento(int senha_atendimento, Date data_solicitacao, Date data_atendimento, Date data_encerramento) {
        this.senha_atendimento = senha_atendimento;
        this.data_solicitacao = data_solicitacao;
        this.data_atendimento = data_atendimento;
        this.data_encerramento = data_encerramento;
    }

    public int getSenha_atendimento() {
        return senha_atendimento;
    }

    public void setSenha_atendimento(int senha_atendimento) {
        this.senha_atendimento = senha_atendimento;
    }

    public Date getData_solicitacao() {
        return data_solicitacao;
    }

    public void setData_solicitacao(Date data_solicitacao) {
        this.data_solicitacao = data_solicitacao;
    }

    public Date getData_atendimento() {
        return data_atendimento;
    }

    public void setData_atendimento(Date data_atendimento) {
        this.data_atendimento = data_atendimento;
    }

    public Date getData_encerramento() {
        return data_encerramento;
    }

    public void setData_encerramento(Date data_encerramento) {
        this.data_encerramento = data_encerramento;
    }
    
    
    
    
}
