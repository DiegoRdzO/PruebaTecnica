package com.diego.pruebatecnicadb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.Stack;

@SpringBootApplication
public class PruebaTecnicaDbApplication {

    public static void main(String[] args){
        SpringApplication.run(PruebaTecnicaDbApplication.class, args);

        //Variable de entrada
        Scanner scanner = new Scanner(System.in);

        //Consola de entrada
        System.out.print("Ingresa la cadena de llaves, corchetes o paréntesis, por favor: ");
        String cadenaIngresada = scanner.nextLine();


        //Imprimir resultados
        if (esBalanceada(cadenaIngresada)){
            System.out.println("La cadena está balanceada.");
        }else {
            System.out.println("La cadena no está balanceada.");
        }

        //Liberar el escáner
        scanner.close();
    }

    //Method para comprobar
    public static boolean esBalanceada(String cadenaIngresada){

        //Declaración de variables
        Stack<Character> pilaDeCaracteres = new Stack<>();
        char[] caracteresIndividuales = cadenaIngresada.toCharArray();

        for (char caracter : caracteresIndividuales) {
            //Comprobar que únicamente se ingresen llaves, corchetes o paréntesis.
            if (caracter != '(' && caracter != ')' && caracter != '[' && caracter != ']' && caracter != '{' && caracter != '}') {
                System.out.println("Únicamente puedes ingresar llaves, corchetes o paréntesis.");
                return false;
            }
            //Qué hacer cuando sea un símbolo de apertura
            if(caracter == '(' || caracter == '[' || caracter == '{'){
                pilaDeCaracteres.push(caracter);
            }

            //Qué hacer si es un símbolo de cierre
            if (caracter == ')' || caracter == ']' || caracter == '}') {
                if (pilaDeCaracteres.empty()) {
                    System.out.println("La cadena no está balanceada.");
                }
                char caracterSacado = pilaDeCaracteres.pop();
                if ((caracter == ')' && caracterSacado != '(') || (caracter == ']' && caracterSacado != '[') || (caracter == '}' && caracterSacado != '{')) {
                    System.out.println("La cadena no está balanceada.");
                }
            }
        }

        //Retornar si la cadena está vacía o no.
        return pilaDeCaracteres.isEmpty();
    }
}
