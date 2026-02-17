package cards;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionAndAnswer {

    private static final String CARD = "Карточка";
    private static final String QUESTION = "Вопрос: ";
    private static final String ANSWER = "Ответ: ";
    private static final String LINE_BREAK = "\n";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            List<Card> cards = new ArrayList<>();
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.startsWith(CARD)) {
                    String question = reader.readLine();
                    question = question.substring(QUESTION.length());
                    String answer = reader.readLine();
                    answer = "{{c1::" + answer.substring(ANSWER.length()) + "}}";
                    Card card = new Card(question, answer);
                    cards.add(card);
                }
            }
            cards.forEach(card -> System.out.println(card.getQuestion() + LINE_BREAK + card.getAnswer() + LINE_BREAK + LINE_BREAK));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Getter
    @AllArgsConstructor
    private static class Card {
        private String question;
        private String answer;

        @Override
        public String toString() {
            return question + '\n' +
                    answer + '\n';
        }
    }
}
