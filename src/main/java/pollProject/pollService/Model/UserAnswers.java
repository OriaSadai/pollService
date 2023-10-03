package pollProject.pollService.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserAnswers {
    private Long userId;
    @JsonProperty(value = "answers")
    private List<Answer> userAnswers;
    public UserAnswers() {}
    public UserAnswers(Long userId, List<Answer> userAnswers) {
        this.userId = userId;
        this.userAnswers = userAnswers;
    }
    public Long getUserId() {
        return userId;
    }
    public List<Answer> getUserAnswers() {
        return userAnswers;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setUserAnswers(List<Answer> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
