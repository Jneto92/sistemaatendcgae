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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Neto
 */
public class Arquivos {
    File home = new File(System.getProperty("user.name"));
    File diretorio = new File("C:\\Users\\"+home+"\\Documents\\sistemaatedcgae");
    File subdiretorio = new File(diretorio.getAbsolutePath()+"\\"+"relatorios");
    String data;
    
    public void criarDiretorio(){
       
        if(diretorio.exists()){
            System.out.println("Diretorio já existe");
        }else{
            System.out.println("Diretório criado com sucesso");
            diretorio.mkdir();
        }
        
    }
    
    public void criarsubDiretorio(){
       
        if(subdiretorio.exists()){
            System.out.println("Diretorio já existe");
        }else{
            System.out.println("Diretório criado com sucesso");
            subdiretorio.mkdir();
        }
        
    }
    
    public void criarArquivo(Atendimento at) throws IOException{
        LocalDateTime agora = LocalDateTime.now();

            // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        String dataFormatada = formatterData.format(agora);
        String dia = "hoje";
        
        //System.out.println("aqui");
        File subdiretorio2 = new File(subdiretorio.getAbsolutePath()+"\\"+dataFormatada+".csv");
        if(!subdiretorio2.exists()){
            //System.out.println("a");
            FileWriter arquivoCsv = new FileWriter(subdiretorio.getAbsolutePath()+"\\"+dataFormatada+".csv");

            PrintWriter escreverArq = new PrintWriter(arquivoCsv);
            escreverArq.print("Senha;");
            escreverArq.print("Nome;");
            escreverArq.print("E-mail;");
            escreverArq.print("Tipo;");
            escreverArq.print("Matrícula;");
            escreverArq.print("Servidor;");
            escreverArq.print("Data solicitação;");
            escreverArq.print("Hora solicitação;");
            escreverArq.print("Data atendimento;");
            escreverArq.print("Hora atendimento;");
            escreverArq.print("Data encerramento;");
            escreverArq.print("Hora encerramento;");
            escreverArq.print("Observações;");
            escreverArq.println("Status;");


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
            //System.out.println("Arquivo gerado"+at.toString());
            arquivoCsv.close();
        }else{
            //System.out.println("b");
            FileWriter arquivoCsv = new FileWriter(subdiretorio.getAbsolutePath()+"\\"+dataFormatada+".csv", true);

            PrintWriter escreverArq = new PrintWriter(arquivoCsv);
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
            //System.out.println("Arquivo gerado"+at.toString());
            arquivoCsv.close();
        }

        
    }
    
}
