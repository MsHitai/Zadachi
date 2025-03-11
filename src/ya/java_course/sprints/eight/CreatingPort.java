package ya.java_course.sprints.eight;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Random;


public class CreatingPort {
    private static final int PORT = 8080;


    // IOException могут сгенерировать методы create() и bind(...)
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create();

        httpServer.bind(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/hello", new HelloHandler());
        //httpServer.createContext("/day", new DayHandler());
        // добавьте новый обработчик для /day тут
        httpServer.start(); // запускаем сервер

        System.out.println("HTTP-сервер запущен на " + PORT + " порту!");
        //httpServer.stop(1);
    }

    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            System.out.println("Началась обработка /hello запроса от клиента.");

            Headers headers = httpExchange.getResponseHeaders();
            headers.set("Content-Type", "text/plain");
            headers.set("X-your-own-header", "any-information-you-want");
            headers.set("X-your-own-header-2", "any-information-you-want-2");

            String response = "Привет! Рады видеть на нашем сервере.";
            httpExchange.sendResponseHeaders(200, 0);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    // объявите класс-обработчик тут

    static class DayHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String[] days = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};

            Random random = new Random();

            int index = random.nextInt(6);

            String response = days[index];

            exchange.sendResponseHeaders(200, 0);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}

/**
 * Handle the given request and generate an appropriate response.
 * See {@link HttpExchange} for a description of the steps
 * involved in handling an exchange.
 *
 * @param exchange the exchange containing the request from the
 * client and used to send the response
 * @throws NullPointerException if exchange is <code>null</code>
 */