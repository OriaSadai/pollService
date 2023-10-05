package pollProject.pollService.Repository;

import org.springframework.dao.EmptyResultDataAccessException;
import pollProject.pollService.Model.Answer;

import java.util.List;

public interface AnswerRepository {
    public void createAnswer(Answer answer, Long userId);
    public void deleteAnswers(Long userId);
    public List<Answer> readAllAnswersByPollId(Integer pollId) throws EmptyResultDataAccessException;
    public List<Answer> readAllAnswersByUserId(Long userId) throws EmptyResultDataAccessException;
}
