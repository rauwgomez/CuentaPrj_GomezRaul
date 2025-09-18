import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalCuenta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Cuenta> cuentas = new ArrayList<>();
        int actual = -1; 

        System.out.println("======================================");
        System.out.println("                Menu                  ");
        System.out.println("======================================");

        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú principal");
            System.out.println("1) Crear Cuenta");
            System.out.println("2) Conocer la cantidad de Cuentas Creadas");
            System.out.println("3) Listar Cuentas");
            System.out.println("4) Seleccionar Cuenta actual");
            System.out.println("5) Depositar");
            System.out.println("6) Retirar");
            System.out.println("7) Consultar Saldo");
            System.out.println("8) Consultar Estado (toString)");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1": { // Crear Cuenta
                    System.out.print("Ingrese el saldo inicial: ");
                    String saldoStr = sc.nextLine().trim();
                    double saldoInicial;
                    try {
                        saldoInicial = Double.parseDouble(saldoStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido. Se va a asignar 0.0.");
                        saldoInicial = 0;
                    }

                    System.out.print("Ingrese el nombre del titular (Presione "Enter" para omitir): ");
                    String nombre = sc.nextLine().trim();

                    Cuenta nuevaCuenta;
                    if (nombre.isEmpty()) {
                        nuevaCuenta = new Cuenta(saldoInicial);
                    } else {
                        nuevaCuenta = new Cuenta(nombre, saldoInicial);
                    }

                    cuentas.add(nuevaCuenta);
                    actual = cuentas.size() - 1; 
                    System.out.println("Cuenta creada exitosamente Índice: " + actual);
                    break;
                }
                case "2": { // Conocer la cantidad de Cuentas Creadas
                    System.out.println("Total de cuentas creadas: " + Cuenta.getCantCuentasCreadas());
                    break;
                }
                case "3": { // Listar Cuentas
                    if (cuentas.isEmpty()) {
                        System.out.println("No hay cuentas");
                    } else {
                        System.out.println("--- Lista de Cuentas ---");
                        System.out.printf("%-8s | %-10s | %-20s | %s%n", "Índice", "Código", "Titular", "Saldo");
                        System.out.println("----------------------------------------------------------");
                        for (int i = 0; i < cuentas.size(); i++) {
                            Cuenta c = cuentas.get(i);
                            System.out.printf("%-8d | %-10s | %-20s | ₡%.2f%n",
                                    i,
                                    c.getCodCuenta(),
                                    c.getNombreCuentaHabiente(), 
                                    c.getSaldo());
                        }
                    }
                    break;
                }
                case "4": { // Seleccionar Cuenta actual
                    if (cuentas.isEmpty()) {
                        System.out.println("Error: No hay cuentas creadas.");
                        break;
                    }
                    System.out.print("Ingrese el índice de la cuenta a seleccionar: ");
                    String idxStr = sc.nextLine().trim();
                    try {
                        int idx = Integer.parseInt(idxStr);
                        if (idx >= 0 && idx < cuentas.size()) {
                            actual = idx;
                            System.out.println("Cuenta " + actual + " seleccionada.");
                        } else {
                            System.out.println("Índice fuera de rango.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Índice inválido.");
                    }
                    break;
                }
                case "5": { // Depositar
                    if (actual < 0) {
                        System.out.println("Error: Primero debe seleccionar una cuenta.");
                        break;
                    }
                    System.out.print("Monto a depositar: ");
                    String montoStr = sc.nextLine().trim();
                    try {
                        double monto = Double.parseDouble(montoStr);
                        if (monto > 0) {
                            double nuevoSaldo = cuentas.get(actual).depositar(monto);
                            System.out.printf("Depósito exitoso. Nuevo saldo: ₡%.2f%n", nuevoSaldo);
                        } else {
                            System.out.println("El monto a depositar debe ser positivo.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido.");
                    }
                    break;
                }
                case "6": { // Retirar
                    if (actual < 0) {
                        System.out.println("Error: Primero debe seleccionar una cuenta.");
                        break;
                    }
                    System.out.print("Monto a retirar: ");
                    String montoStr = sc.nextLine().trim();
                    try {
                        double monto = Double.parseDouble(montoStr);
                        Cuenta cuentaActual = cuentas.get(actual);
                        double saldoAntes = cuentaActual.getSaldo();
                        double saldoDespues = cuentaActual.retirar(monto);

                        if (saldoAntes > saldoDespues) {
                            System.out.printf("Retiro exitoso. Nuevo saldo: ₡%.2f%n", saldoDespues);
                        } else if (monto <= 0) {
                             System.out.println("El monto a retirar debe ser positivo.");
                        }
                        else {
                            System.out.println("Fondos insuficientes. El retiro no fue realizado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido.");
                    }
                    break;
                }
                case "7": { // Consultar Saldo
                    if (actual < 0) {
                        System.out.println("Error: Primero debe seleccionar una cuenta.");
                        break;
                    }
                    System.out.printf("Saldo de la cuenta %d: ₡%.2f%n", actual, cuentas.get(actual).getSaldo());
                    break;
                }
                case "8": { // Consultar Estado (toString)
                    if (actual < 0) {
                        System.out.println("Error: Primero debe seleccionar una cuenta.");
                        break;
                    }
                    System.out.println(cuentas.get(actual));
                    break;
                }
                case "0": { // Salir
                    salir = true;
                    System.out.println("Hasta luego!");
                    break;
                }
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        sc.close();
    }
}