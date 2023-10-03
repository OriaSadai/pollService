package pollProject.pollService.Repository;

import pollProject.pollService.Model.Answer;

import java.util.List;

public interface AnswerRepository {
    public void createAnswer(Answer answer, Long userId);
    public void deleteAnswers(Long userId);
    public List<Answer> readAllAnswersByPollId(Integer pollId);
    public List<Answer> readAllAnswersByUserId(Long userId);
    public List<Answer> getAllAnswerEachPollEachOption();
}
