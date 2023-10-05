package pollProject.pollService.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import pollProject.pollService.Model.AnswerNumber;
import pollProject.pollService.Model.UserAnswers;

import java.util.List;
import java.util.Map;

public interface AnswersService {
    public void createAnswers(UserAnswers userAnswers) throws JsonProcessingException;
    public void deleteAnswers(Long userId, String delToken);

    public Map<AnswerNumber,Integer> getUsersChosenByPoll(Integer pollId);
    public Integer getUsersAnswerByPoll(Integer pollId);
    public Map<Integer, AnswerNumber> getAnswerEachPollByUser(Long userId);
    public Integer getAnswersByUser(Long userId);
    public List<Map<AnswerNumber, Integer>> getAllAnswerEachPollEachOption();
}
