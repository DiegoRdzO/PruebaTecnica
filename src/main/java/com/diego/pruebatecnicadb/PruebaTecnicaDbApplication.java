package com.diego.pruebatecnicadb;

import com.diego.pruebatecnicadb.entities.Resultado;
import com.diego.pruebatecnicadb.repositories.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.Stack;

@SpringBootApplication
public class PruebaTecnicaDbApplication implements CommandLineRunner {

    //Inyección de dependencias para spring
    private final ResultadoRepository resultadoRepository;

    public PruebaTecnicaDbApplication(ResultadoRepository resultadoRepository) {
        this.resultadoRepository = resultadoRepository;
    }

    public static void main(String[] args){
        SpringApplication.run(PruebaTecnicaDbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //Variables necesarias
        Scanner scanner = new Scanner(System.in);
        int eleccion = 0;

        do{
            System.out.println("Bienvenido a mi prueba técnica.");
            System.out.println("1.- Deseo comprobar una nueva cadena.");
            System.out.println("2.- Deseo comprobar datos anteriores.");
            System.out.println("3.- Salir.");
            System.out.print("Mi elección: ");
            try {
                eleccion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Ingresa un número, por favor.");
            }

            switch (eleccion){
                case 1:
                    //Consola de entrada
                    System.out.println("----- NUEVA COMPROBACIÓN -----");
                    System.out.print("Ingresa la cadena de llaves, corchetes o paréntesis, por favor: ");
                    String cadenaIngresada = scanner.nextLine();

                    //Imprimir resultados
                    if (esBalanceada(cadenaIngresada)){
                        System.out.println("La cadena está balanceada.");
                        resultadoRepository.save(new Resultado(cadenaIngresada, true)); //Guardamos en la base de datos.
                    }else {
                        System.out.println("La cadena no está balanceada.");
                        resultadoRepository.save(new Resultado(cadenaIngresada, false)); //Guardamos en la base de datos.
                    }
                    break;

                case 2:
                    //Imprimir resultados
                    System.out.println("----- RESULTADOS ANTERIORES -----");
                    resultadoRepository.findAll().forEach(resultado ->
                        System.out.println(resultado.getCadenaIngresada() + " - " + resultado.isResultado()));
                    break;

                case 3:
                    System.out.println("Hasta luego.");
                    break;
            }
        }while(eleccion != 3);

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
