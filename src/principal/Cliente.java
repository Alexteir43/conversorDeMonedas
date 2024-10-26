package principal;

import resources.Monedas;
import resources.Server;

import java.util.Map;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner usuario = new Scanner(System.in);
        Server servidor = new Server();




        int conversion = 1;
        int historial = 2;
        int salida = 3;

//Menú principal
        while (true) {

            System.out.println("*******************");

            System.out.println("Escriba el número de la acción deseada:");

            System.out.println(conversion + "- Realizar una nueva conversión");
            System.out.println(historial + "- Consultar historial de conversiones");
            System.out.println(salida + "- Salir");


            try {
                int accion = usuario.nextInt();
                usuario.nextLine();

                if (accion == conversion) {
                    //Ejecución de la acción 1
//1.1 Pedir al usuario la moneda base
                    System.out.println("Ingrese la moneda que desea convertir (ej.USD): ");
                    String monedaBase = usuario.next().toUpperCase().trim();

                    // Validar moneda base
                    if (monedaBase.length() != 3) {
                        throw new IllegalArgumentException("El código de la moneda base debe tener 3 letras.");
                        //Volver al inicio del menú si la entrada es incorrecta
                    }

//1.2 Pedir la cantidad a convertir
                    System.out.println("Ingrese la cantidad que desea convertir: ");
                    double cantidad = usuario.nextDouble();
                   // usuario.nextLine(); //Consumir línea sobrante

//1.3 Pedir la moneda a convertir
                    System.out.println("Ingresa la moneda que deseas convertir (ej.ARS): ");
                    String monedaDestino = usuario.next().toUpperCase().trim();
                    // Validar moneda destino
                    if (monedaDestino.length() != 3) {
                        throw new IllegalArgumentException("El código de la moneda debe tener 3 letras.");
                        //Volver al inicio del menú si la entrada es incorrecta
                    }

//1.4 Lógica
                    try {


                        Monedas monedas = servidor.moneda(monedaBase);
                        Map<String, Double> tasasConversion = monedas.conversion_rates();

                        if (tasasConversion.containsKey(monedaDestino)) {
                            //Calcular la cantidad convertida
                            double tasa = tasasConversion.get(monedaDestino);
                            System.out.println("Tasa de conversión : " + tasa);

                            double cantidadConvertida = cantidad * tasa;
                            //Mostrar resultado
                            System.out.println("Cantidad original: " + cantidad +" " + monedaBase);
                            String resultadoConversion = String.format("La cantidad convertida es: %.2f %s%n", cantidadConvertida, monedaDestino);
                            System.out.println(resultadoConversion);

                        } else {
                            System.out.println("No se encontró la tasa de conversión para la moneda destino");
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Ocurrió un error: " + e.getMessage());
                    }


                } else if (accion == salida) {
                    System.out.println("Gracias por usar el conversor de monedas. ¡Hasta pronto!");
                    break;
                } else {
                    System.out.println("Por favor, ingrese una opción válida.");
                }

            } catch (Exception e) {
                System.out.println("Entrada no válida. Por favor, intente de nuevo.");
                usuario.nextLine();
            }
        }
        usuario.close(); //Cerrar el scanner al final
    }
}