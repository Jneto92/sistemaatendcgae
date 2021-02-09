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
public class LoginSistema {

    private int id_login;
    private String usuario;
    private String senha;
    private String permissao;
    private Date data_login;
    private Date data_logout;

    public LoginSistema(int id_login, String usuario, String senha, String permissao, Date data_login, Date data_logout) {
        this.id_login = id_login;
        this.usuario = usuario;
        this.senha = senha;
        this.permissao = permissao;
        this.data_login = data_login;
        this.data_logout = data_logout;
    }

    public int getId_login() {
        return id_login;
    }

    public void setId_login(int id_login) {
        this.id_login = id_login;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public Date getData_login() {
        return data_login;
    }

    public void setData_login(Date data_login) {
        this.data_login = data_login;
    }

    public Date getData_logout() {
        return data_logout;
    }

    public void setData_logout(Date data_logout) {
        this.data_logout = data_logout;
    }
    
    
    
}
