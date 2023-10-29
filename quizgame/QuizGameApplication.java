package quizgame;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.net.InetSocketAddress;

public class QuizGameApplication {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        List<Question> quizQuestions = initializeQuestions();

        server.createContext("/api/game/question", new GetQuestionHandler(quizQuestions));
        server.createContext("/api/game/start", new StartGameHandler());
        server.createContext("/api/game/answer", new SubmitAnswerHandler());
        server.start();
        System.out.println("Server is running on port " + port);
    }

    private static List<Question> quizQuestions = initializeQuestions();

    private static List<Question> initializeQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is 2 + 2?", 4));
        questions.add(new Question("What is 5 + 3?", 8));
        questions.add(new Question("What is 10 + 5?", 15));
        return questions;
    }
}
