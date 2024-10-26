package resources;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {

    }

    public Monedas moneda(String coin) {
        // Validar que el código de moneda tenga exactamente 3 caracteres y esté en mayúsculas
        if (coin.length() != 3) {
            throw new IllegalArgumentException("El código de la moneda debe tener 3 letras.");
       } else if (coin == null || coin.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de la moneda no puede estar vacío");
        }



        URI direccion = URI.create
                ("https://v6.exchangerate-api.com/v6/9f6920761436b691ec8f5b98/latest/"+coin);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();


        try{
            HttpResponse <String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

// Imprimir la URL generada y el cuerpo de la respuesta para depurar
            System.out.println("URL solicitada: " + direccion.toString());
            System.out.println("Código de estado HTTP: " + response.statusCode());
            System.out.println("Respuesta del servidor: " + response.body());

            // Verificar el estado HTTP de la respuesta
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error al obtener datos de la API. Código de estado: " + response.statusCode());
            }
Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setPrettyPrinting()
        .create();
            Monedas moneda = new Gson().fromJson(response.body(), Monedas.class);

            // Verificar que el objeto Monedas y los rates no sean nulos
            if (moneda == null || moneda.conversion_rates() == null) {
                throw new RuntimeException("Error al procesar la respuesta de la API.");
            }
            return moneda;


        } catch (IOException | InterruptedException e) {
            System.out.println("Error de conexión o interrupción: " + e.getMessage());
            throw new RuntimeException("No se pudo conectar al servidor");
        } catch (Exception e) {
            System.out.println("Otro error ocurrió: " + e.getMessage());
            throw new RuntimeException("No encontré la moneda");
        }

    }
}

