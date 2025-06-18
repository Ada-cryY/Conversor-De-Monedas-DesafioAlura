package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/d8c5074519a810db9327b05a/latest/USD"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

        //Valores
        double usdToArs = conversionRates.get("ARS").getAsDouble();
        double usdToBrl = conversionRates.get("BRL").getAsDouble();
        double usdToCop = conversionRates.get("COP").getAsDouble();

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 7) {
            System.out.println("Seleccione una opción:");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese cantidad en USD: ");
                    double usd1 = scanner.nextDouble();
                    System.out.println("Equivalente en Pesos argentinos: " + (usd1 * usdToArs));
                    break;
                case 2:
                    System.out.print("Ingrese cantidad en Pesos argentinos: ");
                    double ars = scanner.nextDouble();
                    System.out.println("Equivalente en USD: " + (ars / usdToArs));
                    break;
                case 3:
                    System.out.print("Ingrese cantidad en USD: ");
                    double usd2 = scanner.nextDouble();
                    System.out.println("Equivalente en Reales brasileños: " + (usd2 * usdToBrl));
                    break;
                case 4:
                    System.out.print("Ingrese cantidad en Reales brasileños: ");
                    double brl = scanner.nextDouble();
                    System.out.println("Equivalente en USD: " + (brl / usdToBrl));
                    break;
                case 5:
                    System.out.print("Ingrese cantidad en USD: ");
                    double usd3 = scanner.nextDouble();
                    System.out.println("Equivalente en Pesos colombianos: " + (usd3 * usdToCop));
                    break;
                case 6:
                    System.out.print("Ingrese cantidad en Pesos colombianos: ");
                    double cop = scanner.nextDouble();
                    System.out.println("Equivalente en USD: " + (cop / usdToCop));
                    break;
                case 7:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        scanner.close();
    }
}

