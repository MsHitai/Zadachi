package ya.java_course.sprints.eight;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Post {
    private int id;
    private String text;
    private List<Comment> commentaries = new ArrayList<>();

    private Post() {
    }

    public Post(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public void addComment(Comment comment) {
        commentaries.add(comment);
    }

    public List<Comment> getCommentaries() {
        return commentaries;
    }

    public int getId() {
        return id;
    }
}

class Comment {
    private String user;
    private String text;

    private Comment() {
    }

    public Comment(String user, String text) {
        this.user = user;
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
}

public class SocialNetworkPort {
    private static final int PORT = 8080;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static Gson gson = new Gson();
    private static List<Post> posts = new ArrayList<>();

    static {
        Post post1 = new Post(1, "Это первый пост, который я здесь написал.");
        post1.addComment(new Comment("Пётр Первый", "Я успел откомментировать первым!"));
        posts.add(post1);

        Post post2 = new Post(22, "Это будет второй пост. Тоже короткий.");
        posts.add(post2);

        Post post3 = new Post(333, "Это пока последний пост.");
        posts.add(post3);
    }


    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create();

        httpServer.bind(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/posts", new PostsHandler());
        httpServer.start(); // запускаем сервер

        System.out.println("HTTP-сервер запущен на " + PORT + " порту!");
    }

    static class PostsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Endpoint endpoint = getEndpoint(exchange.getRequestURI().getPath(), exchange.getRequestMethod());

            switch (endpoint) {
                case GET_POSTS: {
                    handleGetPosts(exchange);
                    break;
                }
                case GET_COMMENTS: {
                    handleGetComments(exchange);
                    break;
                }
                case POST_COMMENT: {
                    handlePostComments(exchange);
                    break;
                }
                default:
                    writeResponse(exchange, "Такого эндпоинта не существует", 404);
            }
        }

        private Endpoint getEndpoint(String requestPath, String requestMethod) {
            String[] pathParts = requestPath.split("/");

            if (pathParts.length == 2 && pathParts[1].equals("posts")) {
                return Endpoint.GET_POSTS;
            }
            if (pathParts.length == 4 && pathParts[1].equals("posts") && pathParts[3].equals("comments")) {
                if (requestMethod.equals("GET")) {
                    return Endpoint.GET_COMMENTS;
                }
                if (requestMethod.equals("POST")) {
                    return Endpoint.POST_COMMENT;
                }
            }
            return Endpoint.UNKNOWN;
        }

        private void handleGetPosts(HttpExchange exchange) throws IOException {
            // верните JSON, представляющий список постов. Код ответа должен быть 200.
            // ..
            String response = gson.toJson(posts);
            int code = 200;
            writeResponse(exchange, response, code);
        }

        private void handleGetComments(HttpExchange exchange) throws IOException {
            Optional<Integer> postIdOpt = getPostId(exchange);
            if (postIdOpt.isEmpty()) {
                writeResponse(exchange, "Некорректный идентификатор поста", 400);
                return;
            }
            int postId = postIdOpt.get();

            for (Post post : posts) {
                if (post.getId() == postId) {
                    String commentsJson = gson.toJson(post.getCommentaries());
                    writeResponse(exchange, commentsJson, 200);
                    return;
                }
            }

            writeResponse(exchange, "Пост с идентификатором " + postId + " не найден", 404);

        }

        private Optional<Integer> getPostId(HttpExchange exchange) {
            /* Реализуйте метод получения идентификатора поста.
            Если идентификатор не является числом, верните Optional.empty(). */
            // ...
            String[] pathParts = exchange.getRequestURI().getPath().split("/");
            try {
                return Optional.of(Integer.parseInt(pathParts[2]));
            } catch (NumberFormatException exception) {
                return Optional.empty();
            }
        }

        private void handlePostComments(HttpExchange exchange) throws IOException {
            // реализуйте обработку добавления комментария
            Optional<Integer> postIdOpt = getPostId(exchange);
            if (postIdOpt.isEmpty()) {
                writeResponse(exchange, "Некорректный идентификатор поста", 400);
                return;
            }
            int postId = postIdOpt.get();

            int count = 0;
            for (Post p : posts) {
                if (p.getId() == postId) {
                    count++;
                }
            }

            if (count == 0) {
                writeResponse(exchange, "Пост с идентификатором " + postId + " не найден", 404);
            } else {
                InputStream inputStream = exchange.getRequestBody();
                String body = new String(inputStream.readAllBytes(), DEFAULT_CHARSET);

                Comment comment = null;

                try {
                    comment = gson.fromJson(body, Comment.class);
                } catch (JsonSyntaxException exception) {
                    writeResponse(exchange, "Получен некорректный JSON", 400);
                }

                if (comment == null || comment.getText() == null || comment.getUser() == null) {
                    writeResponse(exchange, "Поля комментария не могут быть пустыми", 400);
                } else {
                    for (Post p : posts) {
                        if (p.getId() == postId) {
                            p.addComment(comment);
                            writeResponse(exchange, "Комментарий добавлен", 201);
                        }
                    }
                }
            }

            // извлеките идентификатор поста и обработайте исключительные ситуации
            /* Получите тело запроса в виде текста в формате JSON и преобразуйте его в объект Comment.
            Учтите, что может быть передан некоректный JSON — эту ситуацию нужно обработать.
            Подумайте, какие ещё ситуации требуют обработки. */
            // ...

            // найдите пост с указанным идентификатором и добавьте в него комментарий
        }

        private void writeResponse(HttpExchange exchange,
                                   String responseString,
                                   int responseCode) throws IOException {
            if (responseString.isBlank()) {
                exchange.sendResponseHeaders(responseCode, 0);
            } else {
                byte[] bytes = responseString.getBytes(DEFAULT_CHARSET);
                exchange.sendResponseHeaders(responseCode, bytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(bytes);
                }
            }
            exchange.close();
        }

        enum Endpoint {GET_POSTS, GET_COMMENTS, POST_COMMENT, UNKNOWN}
    }
}

