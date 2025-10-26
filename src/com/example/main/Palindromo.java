package com.example.main;

import java.util.Scanner;

public class Palindromo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto;

        System.out.println("=== VERIFICADOR DE PALÍNDROMOS ===");
        System.out.print("Ingrese una cadena de texto: ");
        texto = sc.nextLine().trim();

        // Paso 1: Mostrar texto invertido
        String invertida = invertirCadena(texto);
        System.out.println("Texto invertido: " + invertida);

        // Paso 2: Verificar si es palíndromo
        boolean esPalindromo = verificarPalindromo(texto);
        System.out.println("¿Es palíndromo?: " + (esPalindromo ? "Verdadero" : "Falso"));

        sc.close();
    }

    // Invierte la cadena
    static String invertirCadena(String texto) {
        String resultado = "";
        for (int i = texto.length() - 1; i >= 0; i--) {
            resultado += texto.charAt(i);
        }
        return resultado;
    }

    // Verifica si una cadena es palíndromo
    static boolean verificarPalindromo(String texto) {
        // Normalizar: quitar espacios y convertir a minúsculas
        String limpio = texto.replaceAll("\\s+", "").toLowerCase();
        String invertido = invertirCadena(limpio);
        return limpio.equals(invertido);
    }
}
