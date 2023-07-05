package app.curioustale.curioustale.models;

import androidx.annotation.NonNull;

import com.google.firebase.Timestamp;

@SuppressWarnings("unused") // required for firebase
public class Answer {
    private String question;
    private String questionId;
    private String answer;
    private Timestamp timestamp;

    public Answer() {
        // Required for firebase
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @NonNull
    @Override
    public String toString() {
        return "Answer{" +
                "question='" + question + '\'' +
                ", questionId='" + questionId + '\'' +
                ", answer='" + answer + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
