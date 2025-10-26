package com.example.main;

import java.util.Scanner;

public class EncontrarRepetidos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String entrada;
        double[] numeros;
        boolean repetido, primero, encontrado;

        System.out.println("Ingrese los valores separados por comas (ejemplo: 10,20,1,10,20,2,5,3.3,3.3):");
        entrada = sc.nextLine();

        // Eliminar espacios
        entrada = entrada.replace(" ", "");

        // Separar por comas
        String[] partes = entrada.split(",");
        int n = partes.length;

        // Convertir a números
        numeros = new double[n];
        for (int i = 0; i < n; i++) {
            try {
                numeros[i] = Double.parseDouble(partes[i]);
            } catch (NumberFormatException e) {
                System.out.println("Error: valor inválido en la posición " + (i + 1));
                return;
            }
        }

        // Mostrar los números repetidos
        System.out.print("Números repetidos: ");
        primero = true;

        for (int i = 0; i < n; i++) {
            repetido = false;

            // Verificar si ya fue mostrado antes
            for (int k = 0; k < i; k++) {
                if (numeros[i] == numeros[k]) {
                    repetido = true;
                    break;
                }
            }

            // Si no fue mostrado antes, buscar si aparece después
            if (!repetido) {
                encontrado = false;
                for (int j = i + 1; j < n && !encontrado; j++) {
                    if (numeros[i] == numeros[j]) {
                        if (!primero) {
                            System.out.print(", ");
                        }
                        System.out.print(numeros[i]);
                        primero = false;
                        encontrado = true;
                    }
                }
            }
        }

        System.out.println(); // salto de línea final
        sc.close();
    }
}
