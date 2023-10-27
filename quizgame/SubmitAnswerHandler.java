package quizgame;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;


class SubmitAnswerHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        sendResponse(exchange, "Answer submitted. Use GET request to /api/game/question to get the next question.");
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
