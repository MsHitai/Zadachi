package ya.java_course.sprints.eight;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class HttpClientPracticum {

    public static void main(String[] args) throws IOException, InterruptedException {
        // укажите URL-адрес ресурса
        try {
            URI uri = URI.create("https://ya.ru/white");

            // создайте объект, описывающий HTTP-запрос
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(uri)
                    .version(HttpClient.Version.HTTP_1_1)
                    .header("Accept", "text/html")
                    .build();

            // создайте HTTP-клиент с настройками по умолчанию
            HttpClient client = HttpClient.newHttpClient();

            // получите стандартный обработчик тела запроса
            // с конвертацией содержимого в строку
            HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

            // отправьте запрос
            HttpResponse<String> response = client.send(request, handler);

            // выведите код состояния
            System.out.println(response.statusCode());
        } catch (IOException | InterruptedException exc) {
            System.out.println("Во время выполнения запроса возникла ошибка. Проверьте, пожалуйста, URL-адрес и повторите попытку");
        } catch (IllegalArgumentException exc) {
            System.out.println("Введённый вами адрес не соответствует формату URL. Попробуйте, пожалуйста, снова");
        }
    }
}
