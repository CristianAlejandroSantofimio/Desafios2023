package main.java.paquetes_java;

import java.util.Scanner;

public class Main  {
    public static void main(String[] args) {

        String [][] matrizTexto = new String[3][3];
        llenarMatriz(matrizTexto);


    }

    public static void llenarMatriz(String [][] matrizTexto) {

        Scanner valor = new Scanner(System.in);
        for( int i=0; i< matrizTexto.length ;i++)
        {
            for ( int j= 0 ; j < matrizTexto[0].length; j++)
            {

                System.out.println("ingrese el valor de esta posicion : ");
                matrizTexto[i][j] = valor.next();
            }
        }
    }


}
