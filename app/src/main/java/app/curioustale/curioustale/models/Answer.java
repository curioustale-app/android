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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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
