package ya.java_course.sprints.eight;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PortGreet {
    private static final int PORT = 8080;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create();

        httpServer.bind(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/hello", new HelloHandler());
        httpServer.start();
        System.out.println("HTTP-сервер запущен на " + PORT + " порту!");
        httpServer.stop(1);
    }

    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String response;

            // извлеките метод из запроса
            String method = httpExchange.getRequestMethod();

            switch (method) {
                // сформируйте ответ в случае, если был вызван POST-метод
                case "POST":
                    // извлеките тело запроса
                    InputStream bodyStream = httpExchange.getRequestBody();
                    String body = new String(bodyStream.readAllBytes(), DEFAULT_CHARSET);

                    URI requestURI = httpExchange.getRequestURI();
                    // извлеките path из запроса
                    String path = requestURI.getPath();
                    // а из path — профессию и имя
                    String[] splitStrings = path.split("/");
                    String profession = splitStrings[2];
                    String name = splitStrings[3];

                    Headers requestHeaders = httpExchange.getRequestHeaders();
                    // извлеките заголовок
                    if (requestHeaders.containsKey("X-Wish-Good-Day=true")) {
                        List<String> wishGoodDay = requestHeaders.get("X-Wish-Good-Day=true");
                        response = "Доброе утро, " + profession + name + "! Хорошего дня!";
                    } else {
                        // соберите ответ
                        response = "Доброе утро, " + profession + " " + name + "!";
                    }
                    // не забудьте про ответ для остальных методов
                    break;
                case "GET":
                    response = "Здравствуйте!";
                    break;
                default:
                    response = "Некорректный метод!";
            }

            httpExchange.sendResponseHeaders(200, 0);
            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}
