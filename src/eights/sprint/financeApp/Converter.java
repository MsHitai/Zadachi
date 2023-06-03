package eights.sprint.financeApp;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Converter {
    private final HttpClient client;

    public Converter() {
        this.client = HttpClient.newHttpClient();
    }

    public void convert(double rubles, String currency) {

        if (getRate(currency) != 0) {
            double rate = getRate(currency);
            System.out.println("Ваши сбережения: " + rubles * rate + " " + currency);
        }
    }

    private Double getRate(String currencySymbol) {
        URI url = URI.create("https://api.exchangerate.host/latest?base=RUB&symbols=" + currencySymbol);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        double rate = 0.0;
        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonElement jsonElement = JsonParser.parseString(response.body());
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonObject ratesObject = jsonObject.get("rates").getAsJsonObject();
                rate = ratesObject.get(currencySymbol).getAsDouble();
            } else {
                System.out.println("Что-то пошло не так. Сервер вернул код состояния: " + response.statusCode());
            }
        } catch (NullPointerException | IOException | InterruptedException e) { // обрабатываем ошибки отправки запроса
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
        return rate;
    }
}
