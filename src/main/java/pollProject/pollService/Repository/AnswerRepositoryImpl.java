package pollProject.pollService.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pollProject.pollService.Model.Answer;
import pollProject.pollService.Repository.Mapper.AnswerMapper;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {
    private static final String ANSWER_TABLE_NAME = "answers";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AnswerMapper answerMapper;
    private static final Logger logger = LoggerFactory.getLogger(AnswerRepositoryImpl.class);
    @Override
    public void createAnswer(Answer answer, Long userId) {
        logger.info(String.format("IN THE ANSWER REPOSITORY: GOING TO CREATE AN ANSWER WITH USER_ID:\"%s\", POLL_ID:\"%s\".",answer.getUserId(),answer.getPollId()));
        String sql = "INSERT INTO " + ANSWER_TABLE_NAME + " " + "(user_id, poll_id, answer_number) VALUES (?, ?, ?)";
        jdbcTemplate.update(
                sql,
                userId,
                answer.getPollId(),
                answer.getAnswerNumber().name()
        );
    }
    @Override
    public void deleteAnswers(Long userId) {
        String sql = "DELETE FROM " + ANSWER_TABLE_NAME + " WHERE user_id=?";
        jdbcTemplate.update(sql, userId);
    }
    @Override
    public List<Answer> readAllAnswersByPollId(Integer pollId) {
        String sql = "SELECT * FROM " + ANSWER_TABLE_NAME + " WHERE poll_id=?";
        try {
            return jdbcTemplate.query(sql, answerMapper, pollId);
        } catch (EmptyResultDataAccessException e) {
            logger.warn(String.format("IN THE ANSWER REPOSITORY: NO ANSWERS FOR POLL NUMBER:\"%s\".",pollId));
            return null;
        }
    }
    @Override
    public List<Answer> readAllAnswersByUserId(Long userId) {
        String sql = "SELECT * FROM " + ANSWER_TABLE_NAME + " WHERE user_id=?";
        try {
            return jdbcTemplate.query(sql, answerMapper, userId);
        } catch (EmptyResultDataAccessException e) {
            logger.warn(String.format("IN THE ANSWER REPOSITORY: NO ANSWERS FOR USER ID:\"%s\".",userId));
            return null;
        }
    }
    @Override
    public List<Answer> getAllAnswerEachPollEachOption() {
        String sql = "SELECT ans.poll_id, COUNT(*) AS QUANTITY FROM " + ANSWER_TABLE_NAME + " AS ans GROUP BY ans.poll_id";
        try {
            return jdbcTemplate.query(sql, answerMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.warn(String.format("IN THE ANSWER REPOSITORY: NO ANSWERS AT ALL."));
            return null;
        }
    }
}
