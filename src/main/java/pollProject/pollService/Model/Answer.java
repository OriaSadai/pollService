package pollProject.pollService.Model;

import java.util.List;

public class Answer {
    private Long userId;
    private Integer pollId;
    private AnswerNumber answerNumber;
    public Answer() {
    }
    public Answer(Long userId, Integer pollId, AnswerNumber answerNumber) {
        this.userId = userId;
        this.pollId = pollId;
        this.answerNumber = answerNumber;
    }
    public Long getUserId() {
        return userId;
    }
    public Integer getPollId() {
        return pollId;
    }
    public AnswerNumber getAnswerNumber() {
        return answerNumber;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setPollId(Integer pollId) {
        this.pollId = pollId;
    }
    public void setAnswerNumber(AnswerNumber answerNumber) {
        this.answerNumber = answerNumber;
    }
    public UserAnswers toUserAnswers(Long userId, List<Answer> answers) {
        return new UserAnswers(userId, answers);
    }
}
