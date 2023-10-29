package quizgame;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class SubmitAnswerHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            String response = "Answer submitted. Use GET request to /api/game/question to get the next question.";
            sendResponse(exchange, response);
        } else {
            exchange.sendResponseHeaders(405, 0);
            exchange.getResponseBody().close();
        }
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
