import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class Principal {

    public static void main(String[] args)  {

        Scanner lectura = new Scanner(System.in);
        var salir= false;
        int opcion = 0;
        Double valor = 0.0;
        Double resultado = 0.0;
        String moneda = "";
        String moneda2 = "";


        while ( !salir) {

            System.out.println("*************************************************");
            System.out.println("Sea Bienvenido/a al Conversor de Moneda =]");
            System.out.println();
            System.out.println("1) Dolar =>> Peso Argentino ");
            System.out.println("2) Peso Argentino =>> Dolar ");
            System.out.println("3) Dolar =>> Real Brasileño ");
            System.out.println("4) Real Brasileño =>> Dolar ");
            System.out.println("5) Dolar =>> Peso Colombiano");
            System.out.println("6) Peso Colombiano =>> Dolar");
            System.out.println("7) Salir ");


            try {

                System.out.println("Elija una opcion Valida: ");
                System.out.println("*************************************************");
                opcion = lectura.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el valor que desea convertir:");
                        valor = lectura.nextDouble();
                        moneda = "USD";
                        moneda2 = "ARS";
                        getRatesFromApi(moneda, moneda2, valor);
                        break;
                    case 2:
                        System.out.println("Ingrese el valor que desea convertir:");
                        valor = lectura.nextDouble();
                        moneda = "ARS";
                        moneda2 = "USD";
                        getRatesFromApi(moneda, moneda2, valor);
                        break;
                    case 3:
                        System.out.println("Ingrese el valor que desea convertir:");
                        valor = lectura.nextDouble();
                        moneda = "USD";
                        moneda2 = "BRL";
                        getRatesFromApi(moneda, moneda2, valor);
                        break;
                    case 4:
                        System.out.println("Ingrese el valor que desea convertir:");
                        valor = lectura.nextDouble();
                        moneda = "BRL";
                        moneda2 = "USD";
                        getRatesFromApi(moneda, moneda2, valor);
                        break;
                    case 5:
                        System.out.println("Ingrese el valor que desea convertir:");
                        valor = lectura.nextDouble();
                        moneda = "USD";
                        moneda2 = "COP";
                        getRatesFromApi(moneda, moneda2, valor);
                        break;
                    case 6:
                        System.out.println("Ingrese el valor que desea convertir:");
                        valor = lectura.nextDouble();
                        moneda = "COP";
                        moneda2 = "USD";
                        getRatesFromApi(moneda, moneda2, valor);
                        break;
                    case 7:
                        System.out.println("Seleccionaste Salir(7) Bye!!!");
                        salir=true;
                        break;
                    default:
                        System.out.println("Solo numeros entre 1 y 7 ");
                }
            } catch ( InputMismatchException e ) {
                System.out.println("Debes insertar un numero!!!!");
                lectura.next();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public static void getRatesFromApi(String moneda, String moneda2, double valor) throws URISyntaxException, IOException, InterruptedException {

        String sel_moneda = moneda;
        String api_key = "ac1487b3b99bfd166b061764";
        HttpResponse<String> response = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://v6.exchangerate-api.com/v6/"+ api_key +"/latest/"+ sel_moneda))
                .build();

        HttpResponse<String> res = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = res.body();
        //System.out.println("base: "+ sel_moneda);
        //System.out.println(json);
        //System.out.println("Convirtiendo JSON to Object Rates");
        Rates obj_rates = new Gson().fromJson(res.body(), Rates.class);
        String base=obj_rates.getBase_code();
        //System.out.println("BaseCode: " + obj_rates.getBase_code());
        //System.out.println("Conversion Rates Size Hash: "+ obj_rates.getRates().size());
        //System.out.println("Value of Conversion: "+ moneda2 + " = "+ obj_rates.getRates().get(moneda2));
        //System.out.println("Value to convert "+ base + " to " + moneda2+ " = " + valor );
        //System.out.println(""+ base + ":" + valor+" to "+ moneda2+"="+ obj_rates.getRates().get(moneda2));
        String valMoneda2 = String.valueOf(obj_rates.getRates().get(moneda2));
        // Castear Valor
        double result = valor * Double.parseDouble(valMoneda2);
        System.out.println("El Valor de: " + valor + " {"+ base+ "} to ==> {" + moneda2 + "} " + result );
    }

}
