package pollProject.pollService.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Poll {
    @JsonProperty(value = "id")
    private Integer pollId;
    private String question;
    private String firstAnswer;
    private String secondAnswer;
    private String thirdAnswer;
    private String fourthAnswer;
    public Poll() {}
    public Poll(Integer pollId, String question, String firstAnswer, String secondAnswer, String thirdAnswer, String fourthAnswer) {
        this.pollId = pollId;
        this.question = question;
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.fourthAnswer = fourthAnswer;
    }
    public Integer getPollId() {
        return pollId;
    }
    public String getQuestion() {
        return question;
    }
    public String getFirstAnswer() {
        return firstAnswer;
    }
    public String getSecondAnswer() {
        return secondAnswer;
    }
    public String getThirdAnswer() {
        return thirdAnswer;
    }
    public String getFourthAnswer() {
        return fourthAnswer;
    }
    public void setPollId(Integer pollId) {
        this.pollId = pollId;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public void setFirstAnswer(String firstAnswer) {
        this.firstAnswer = firstAnswer;
    }
    public void setSecondAnswer(String secondAnswer) {
        this.secondAnswer = secondAnswer;
    }
    public void setThirdAnswer(String thirdAnswer) {
        this.thirdAnswer = thirdAnswer;
    }
    public void setFourthAnswer(String fourthAnswer) {
        this.fourthAnswer = fourthAnswer;
    }
}
