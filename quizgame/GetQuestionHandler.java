package quizgame;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

class GetQuestionHandler implements HttpHandler {
    private List<Question> quizQuestions;

    public GetQuestionHandler(List<Question> quizQuestions) {
        this.quizQuestions = quizQuestions;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!quizQuestions.isEmpty()) {
            Question question = quizQuestions.get(0);
            sendResponse(exchange, question.getQuestion());
        } else {
            sendResponse(exchange, "No more questions available.");
        }
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
