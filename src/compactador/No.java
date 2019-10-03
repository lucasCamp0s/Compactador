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
public class No {
    private int ocorrencia;
    private char caracter;
    private No Dir;
    private No Esq;

    public No(int ocorrencia,char caracter){
        this.ocorrencia = ocorrencia;
        this.caracter = caracter;       
	}
    public void setDir(No D){
       Dir= D;
	}
    public No getDir(){
		return Dir;
	}
    public void setEsq(No E){
		Esq = E;
	}
    public No getEsq(){
	    return Esq;
	}
    public char getChar()
	{return caracter;}
    
    public void setChar(char c){
	   caracter = c;
	}

    public String toString(){
        return this.ocorrencia + "-" + this.caracter + "";
    }

    public int getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(int ocorrencia) {
        this.ocorrencia = ocorrencia;
    }
   
}
