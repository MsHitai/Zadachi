package test.assignments;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CrptApi {
    private final Lock lock;
    private final AtomicInteger requestCount;
    private final int requestLimit;
    private final HttpClient httpClient;

    private final Condition requestLimitCondition;

    public CrptApi(int requestLimit, Condition requestLimitCondition) {
        this.requestLimitCondition = requestLimitCondition;
        this.lock = new ReentrantLock();
        this.requestCount = new AtomicInteger(0);
        this.requestLimit = requestLimit;
        this.httpClient = new HttpClient();
    }

    public void createDocument(Object document, String signature) throws InterruptedException {
        try {
            lock.lock();
            while (requestCount.get() >= requestLimit) {
                requestLimitCondition.await();
            }
            sendDocumentToApi(document, signature);
            requestCount.incrementAndGet();
        } finally {
            lock.unlock();
        }
    }

    private void sendDocumentToApi(Object document, String signature) {

        try {
            String requestBody = createRequestBody(document, signature);
            HttpResponse response = httpClient.post("https://api.example.com/create-document", requestBody);

            if (response.isSuccessful()) {
                System.out.println("Document created successfully.");
            } else {
                System.out.println("Failed to create document. API error: " + response.getStatusMessage());
            }
        } catch (Exception e) {
            System.out.println("Failed to create document. Exception: " + e.getMessage());
        }
    }

    private String createRequestBody(Object document, String signature) {

        JsonObject requestBodyJson = new JsonObject();
        requestBodyJson.addProperty("document", document.toString());
        requestBodyJson.addProperty("signature", signature);
        return requestBodyJson.toString();
    }

    private static class HttpClient {
        public HttpResponse post(String url, String body) {
            try {

                URL apiUrl = new URL(url);

                HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

                connection.setRequestMethod("POST");

                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");

                connection.setDoOutput(true);
                connection.setDoInput(true);

                connection.getOutputStream().write(body.getBytes(StandardCharsets.UTF_8));
                connection.getOutputStream().flush();
                connection.getOutputStream().close();

                int statusCode = connection.getResponseCode();

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBody = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    responseBody.append(line);
                }
                br.close();

                connection.disconnect();

                return new HttpResponse(statusCode, responseBody.toString());
            } catch (Exception e) {
                e.printStackTrace();
                return new HttpResponse(500, "Internal Server Error");
            }
        }
    }

    private static class HttpResponse {
        private final int statusCode;
        private final String statusMessage;

        public HttpResponse(int statusCode, String statusMessage) {
            this.statusCode = statusCode;
            this.statusMessage = statusMessage;
        }

        public boolean isSuccessful() {
            return statusCode >= 200 && statusCode < 300;
        }

        public String getStatusMessage() {
            return statusMessage;
        }
    }
}
