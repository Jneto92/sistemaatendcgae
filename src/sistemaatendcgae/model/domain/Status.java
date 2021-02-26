/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.model.domain;

/**
 *
 * @author Neto
 */
public class Status {
    private int id_status;
    private String nome;

    public Status(int id_status, String nome) {
        this.id_status = id_status;
        this.nome = nome;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
     
    
}
