package com.example.main;

import java.util.Scanner;

public class MaquinaExpendedora {
    // Lista de productos (código, nombre, precio)
    static String[] codigos = {"A1", "A2", "B1", "B2", "C1"};
    static String[] nombres = {"Agua", "Refresco", "Papas", "Chocolate", "Galletas"};
    static int[] precios = {10, 15, 12, 20, 8};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int saldo = 0;
        boolean ejecutando = true;

        System.out.println("=== MÁQUINA EXPENDEDORA ===");
        mostrarProductos();

        while (ejecutando) {
            System.out.print("\nIngrese comando o moneda: ");
            String entrada = sc.nextLine().trim().toUpperCase();

            if (entrada.equals("PARA")) {
                if (saldo > 0) {
                    System.out.println("Devolviendo $" + saldo + " antes de salir.");
                    saldo = 0;
                }
                System.out.println("Apagando máquina...");
                ejecutando = false;
            }
            else if (entrada.equals("RETIRAR")) {
                if (saldo > 0) {
                    System.out.println("Operación cancelada. Devolviendo $" + saldo);
                    saldo = 0;
                } else {
                    System.out.println("No hay saldo que devolver.");
                }
            }
            else if (esMonedaValida(entrada)) {
                int valor = Integer.parseInt(entrada);
                saldo += valor;
                System.out.println("Moneda aceptada: $" + valor + ". Saldo actual: $" + saldo);
            }
            else if (esCodigoProducto(entrada)) {
                int indice = obtenerIndiceProducto(entrada);
                if (indice == -1) {
                    System.out.println("Producto no encontrado.");
                } else {
                    int precio = precios[indice];
                    String nombre = nombres[indice];
                    if (saldo >= precio) {
                        saldo -= precio;
                        System.out.println("Dispensando " + nombre + " ($" + precio + ")");
                        System.out.println("Saldo restante: $" + saldo);
                    } else {
                        System.out.println("Saldo insuficiente. Precio del producto: $" + precio + ". Saldo actual: $" + saldo);
                    }
                }
            }
            else {
                System.out.println("Entrada inválida. Use monedas válidas (1,2,5,10,20), códigos de producto, RETIRAR (cancela la operacion) o PARA (detener la operacion).");
            }
        }

        sc.close();
    }

    // Muestra la lista de productos disponibles
    static void mostrarProductos() {
        System.out.println("\nProductos disponibles:");
        for (int i = 0; i < codigos.length; i++) {
            System.out.println(codigos[i] + " - " + nombres[i] + " - $" + precios[i]);
        }
    }

    // Verifica si la entrada es una moneda válida
    static boolean esMonedaValida(String entrada) {
        try {
            int valor = Integer.parseInt(entrada);
            return valor == 1 || valor == 2 || valor == 5 || valor == 10 || valor == 20;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Verifica si la entrada corresponde a un código de producto
    static boolean esCodigoProducto(String entrada) {
        for (String codigo : codigos) {
            if (codigo.equals(entrada)) return true;
        }
        return false;
    }

    // Obtiene el índice del producto según su código
    static int obtenerIndiceProducto(String codigo) {
        for (int i = 0; i < codigos.length; i++) {
            if (codigos[i].equals(codigo)) return i;
        }
        return -1;
    }
}