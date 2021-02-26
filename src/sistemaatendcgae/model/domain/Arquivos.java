/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaatendcgae.model.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Neto
 */
public class Arquivos {
    File diretorio = new File("C:\\Users\\Neto\\Documents\\sistemaatedcgae");
    File subdiretorio = new File(diretorio.getAbsolutePath()+"\\"+"relatorios");
    
    public void criarDiretorio(){
       
        if(subdiretorio.exists()){
            System.out.println("Diretorio já existe");
        }else{
            System.out.println("Diretório criado com sucesso");
            subdiretorio.mkdir();
        }
        
    }
    
    public void criarArquivo(Atendimento at){
        
        try {
            FileWriter arquivoTxt = new FileWriter(subdiretorio.getAbsolutePath()+"\\"+"lista de atendimentos.csv", true);
            
            PrintWriter escreverArq = new PrintWriter(arquivoTxt);
            escreverArq.print(at.getSenha_atendimento()+";");
            escreverArq.print(at.getNome()+";");
            escreverArq.print(at.getEmail()+";");
            escreverArq.print(at.getTipoAtendimento()+";");
            escreverArq.print(at.getMatriculaServ()+";");
            escreverArq.print(at.getServidor()+";");
            escreverArq.print(at.getData_solicitacao()+";");
            escreverArq.print(at.getHora_solicitacao()+";");
            escreverArq.print(at.getData_atendimento()+";");
            escreverArq.print(at.getHora_atendimento()+";");
            escreverArq.print(at.getData_encerramento()+";");
            escreverArq.print(at.getHora_encerramento()+";");
            escreverArq.print(at.getObservacao()+";");
            escreverArq.println(at.getStatus()+";");
            
            arquivoTxt.close();
        } catch (IOException e) {
            
        }
        
    }
    
}
