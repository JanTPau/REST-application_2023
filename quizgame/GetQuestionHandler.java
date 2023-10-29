package quizgame;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class GetQuestionHandler implements HttpHandler {
    private List<Question> quizQuestions;
    private int currentQuestionIndex;

    public GetQuestionHandler(List<Question> quizQuestions) {
        this.quizQuestions = quizQuestions;
        this.currentQuestionIndex = 0;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (currentQuestionIndex < quizQuestions.size()) {
            Question question = quizQuestions.get(currentQuestionIndex);
            String response = question.getQuestion();
            sendResponse(exchange, response);

            currentQuestionIndex++;
        } else {
            String noMoreQuestionsResponse = "No more questions available.";
            sendResponse(exchange, noMoreQuestionsResponse);
        }
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
