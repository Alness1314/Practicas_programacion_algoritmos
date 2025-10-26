package com.example.main;

import java.time.LocalDate;
import java.util.Scanner;

public class CalcularEdad {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fechaNacimiento;

        System.out.println("=== CALCULADORA DE EDAD ===");
        System.out.print("Ingrese su fecha de nacimiento (DD-MM-AAAA): ");
        fechaNacimiento = sc.nextLine().trim();

        if (!esFechaValida(fechaNacimiento)) {
            System.out.println("Fecha inválida. Asegúrese de usar el formato DD-MM-AAAA y valores correctos.");
            return;
        }

        // Separar día, mes y año
        String[] partes = fechaNacimiento.split("-");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);

        int edad = calcularEdad(dia, mes, anio);
        if (edad < 0) {
            System.out.println("La fecha de nacimiento no puede ser posterior a la fecha actual.");
            return;
        }

        // Mostrar resultado
        System.out.println("\nAño de nacimiento: " + anio);
        System.out.println("Edad: " + edad + " años");

        if (yaCumplioAnio(dia, mes)) {
            System.out.println("✅ Ya cumplió años este año.");
        } else {
            System.out.println("❌ Aún no ha cumplido años este año.");
        }

        sc.close();
    }

    // Valida que la fecha sea correcta según las reglas
    static boolean esFechaValida(String fecha) {
        if (fecha == null || !fecha.matches("\\d{2}-\\d{2}-\\d{4}")) {
            return false;
        }

        String[] partes = fecha.split("-");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);

        if (anio <= 1900) return false;
        if (mes < 1 || mes > 12) return false;
        if (dia < 1 || dia > 31) return false;

        // Febrero 29 inválido
        if (mes == 2 && dia == 29) return false;

        // Validación de días máximos por mes
        if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) return false;
        if (mes == 2 && dia > 28) return false;

        return true;
    }

    // Calcula la edad basándose en la fecha actual
    static int calcularEdad(int dia, int mes, int anio) {
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - anio;

        // Si aún no ha cumplido años este año, resta uno
        if (mes > hoy.getMonthValue() || (mes == hoy.getMonthValue() && dia > hoy.getDayOfMonth())) {
            edad--;
        }
        return edad;
    }

    // Determina si ya cumplió años este año
    static boolean yaCumplioAnio(int dia, int mes) {
        LocalDate hoy = LocalDate.now();
        return (mes < hoy.getMonthValue()) ||
                (mes == hoy.getMonthValue() && dia <= hoy.getDayOfMonth());
    }
}
