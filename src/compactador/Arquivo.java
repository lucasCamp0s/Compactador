/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compactador;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

/**
 *
 * @author PC
 */
//essa classe será responsável por cuidar do arquivo em geral
public class Arquivo {

    private File arquivo; //responsável por armazenar o arquivo
    private JFileChooser file = new JFileChooser();
    private StringBuilder sb = new StringBuilder();
    private BufferedInputStream buffReader;
    private DataInputStream data;
    private FileInputStream arquivoFisico;
    private String pathArquivo;
    private Map<String, Integer> tabelaLetras = new HashMap<>();

    public void buscarArquivo() throws FileNotFoundException, IOException { //método responsável por bucas arquivos

        if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            arquivo = file.getSelectedFile();//pega o arquivo selecionado pelo usuario

            pathArquivo = arquivo.toPath().toAbsolutePath().toString(); //guarda o endereço do arquivo

            arquivoFisico = new FileInputStream(arquivo.toPath().toFile());//atravez do endereço do aquivo o filieInputStream pegará os dados desse arquivo
            buffReader = new BufferedInputStream(arquivoFisico);//essa linha de codigo tranfoma o "arquivoFisico" em bufferedInputStream
            data = new DataInputStream(buffReader);
            byte vetByte[];

            vetByte = new byte[arquivoFisico.available()];
            data.read(vetByte);
            String dadosArquivo = new String(vetByte);
         
            //  System.out.println(dadosArquivo);
            int contadorDeRepetidos = 0;
            for (int i = 0; i < dadosArquivo.length(); i++) {
                contadorDeRepetidos = 0;
                for (int j = 0; j < dadosArquivo.length(); j++) {
                    if (dadosArquivo.charAt(i) == dadosArquivo.charAt(j)) {
                        contadorDeRepetidos++;

                    }
                    tabelaLetras.put(dadosArquivo.charAt(i) + "", contadorDeRepetidos);
                   // dadosArquivo.replaceAll(dadosArquivo.charAt(i) + "", "");
                    //tentei usar o raplace all para reduzir o consumo de processamento, porém tem palavras reservadas e isso
                    //impossibilitou usar o replace
                }

            }
            
            //Referências para ordenar o map = https://www.javacodegeeks.com/2017/09/java-8-sorting-hashmap-values-ascending-descending-order.html
            
            /*
             1.   Get the set of entries by calling the Map.entrySet() method
             2.   Get the stream of entries by calling stream() method
             3.   Call the sorted method with a Comparator
             4.   Use the Map.Entry.comparingByValue() comparator to sort entries by values
             5.   Collect the result using the collect() method
             6.   Use Collectors.toMap() method to get the result in another Map.
             7.   Provide LinkedHashMap::new to the last parameter to force it to return a LinkedHashMap, to keep the sorted order preserved
             8.   In order to sort in decreasing order, just reverse the order of Comparator
             using Collections.reverseOrder() or Comparator.reverse() method of Java 8. 
             See Java SE 8 for Really Impatient for the full list of new methods added
             into key Java classes e.g. Java Collection Framework, String, and Comparator etc.
             */

            Map<String, Integer> sorted = tabelaLetras
                    .entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(comparingByValue()))
                    .collect(
                            toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                    LinkedHashMap::new));
            tabelaLetras = sorted;
            
            tabelaDeletras();
            /*   for(char c : new String(vetByte).toCharArray()){
             System.out.print(c);
             }*/

        } else {
            pathArquivo = "Arquivo não encontrado / selecionado";
        }

    }

    public String tabelaDeletras() {
        //  Iterator iterator = tabelaLetras.entrySet().iterator();

        /*   while(iterator.hasNext()){
         Map.Entry entry = (Map.Entry) iterator.next();
         System.out.println(entry.getKey() + " : " + entry.getValue());
         }*/
        
        //esse método retorna todos os dados dentro do map
      return tabelaLetras.toString();

    }

    public String getPath() {

        return pathArquivo;

    }



}
