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
public class TipoAtendimento {
    private int id_tipo;
    private String nome;

    public TipoAtendimento(int id_tipo, String nome) {
        this.id_tipo = id_tipo;
        this.nome = nome;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
