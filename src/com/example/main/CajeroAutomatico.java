package com.example.main;

import java.util.Scanner;

public class CajeroAutomatico {
    // Denominaciones disponibles
    static int[] denominaciones = {1000, 500, 200, 100, 50, 20};
    static int[] inventario = new int[denominaciones.length];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        inicializarInventario();

        System.out.println("=== CAJERO AUTOMÁTICO ===");
        String comando;

        while (true) {
            System.out.println("\nComandos disponibles:");
            System.out.println("1. retirar");
            System.out.println("2. inventario");
            System.out.println("3. salir");
            System.out.print("Seleccione una opción: ");
            comando = sc.nextLine().trim().toLowerCase();

            switch (comando) {
                case "retirar", "1" -> retirarDinero(sc);
                case "inventario", "2" -> mostrarInventario();
                case "salir", "3" -> {
                    System.out.println("Saliendo del sistema...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    // Inicializa inventario con 10 billetes de cada denominación
    static void inicializarInventario() {
        for (int i = 0; i < inventario.length; i++) {
            inventario[i] = 10;
        }
    }

    // Mostrar inventario actual
    static void mostrarInventario() {
        System.out.println("\nInventario actual de billetes:");
        for (int i = 0; i < denominaciones.length; i++) {
            System.out.println("$" + denominaciones[i] + " → " + inventario[i] + " billetes");
        }
    }

    // Solicitar retiro
    static void retirarDinero(Scanner sc) {
        System.out.print("Ingrese el monto a retirar: ");
        int monto;
        try {
            monto = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Monto inválido.");
            return;
        }

        if (monto <= 0 || monto % 10 != 0) {
            System.out.println("El monto debe ser múltiplo de 10 y mayor a 0.");
            return;
        }

        int[] billetesEntregar = calcularBilletes(monto);

        if (billetesEntregar == null) {
            System.out.println("No hay suficientes billetes para dispensar $" + monto);
        } else {
            System.out.println("\nDispensando:");
            for (int i = 0; i < denominaciones.length; i++) {
                if (billetesEntregar[i] > 0) {
                    System.out.println("$" + denominaciones[i] + " → " + billetesEntregar[i] + " billetes");
                    inventario[i] -= billetesEntregar[i];
                }
            }
            System.out.println("Retiro exitoso: $" + monto);
        }
    }

    // Calcula la combinación de billetes (si es posible)
    static int[] calcularBilletes(int monto) {
        int[] copiaInventario = inventario.clone();
        int[] billetesAUsar = new int[denominaciones.length];
        int restante = monto;

        for (int i = 0; i < denominaciones.length; i++) {
            int valor = denominaciones[i];
            int maxBilletes = Math.min(restante / valor, copiaInventario[i]);
            billetesAUsar[i] = maxBilletes;
            restante -= maxBilletes * valor;
        }

        // Si no se pudo completar el monto exacto, no dispensar nada
        if (restante != 0) {
            return null;
        }
        return billetesAUsar;
    }
}
