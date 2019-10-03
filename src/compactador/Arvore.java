/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compactador;

/**
 *
 * @author PC
 */
public class Arvore {
    
    No Raiz;
    
    public Arvore(){
        Raiz = null;
    }
    
    public void inserir(int ocorrencia,char caracter){
        inserir(new No(ocorrencia, caracter));       
        
    }

    private void inserir(No novo) {
        if(novo == null) return;
        if(Raiz == null) Raiz = novo;
        else
            inserir(Raiz,novo);
    }
    
    private void inserir(No Raiz,No novo){
        if(Raiz == null) Raiz = novo;
        
        else if(novo.getOcorrencia()>Raiz.getDir().getOcorrencia()){
            if(Raiz.getDir() == null)
                Raiz.setDir(novo);
            else
                inserir(Raiz.getDir(),novo);
        }
        else if(Raiz.getEsq() == null)
            Raiz.setEsq(novo);
        else
            inserir(Raiz.getEsq(),novo);
    }
    
}
