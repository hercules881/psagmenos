package Database;

/**
 * Created by h.lionakis on 13/11/2015.
 */
public class Answer {

    private int answerId;
    private int questionId;
    private String text;

    public Answer(int answerId, int questionId, String text) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.text = text;
    }
    public Answer() {

    }
    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
