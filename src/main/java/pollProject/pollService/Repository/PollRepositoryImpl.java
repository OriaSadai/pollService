package pollProject.pollService.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pollProject.pollService.Model.Poll;
import pollProject.pollService.Repository.Mapper.PollMapper;

import java.util.List;

@Repository
public class PollRepositoryImpl implements PollRepository {
    private static final String POLL_TABLE_NAME = "poll";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PollMapper pollMapper;
    private static final Logger logger = LoggerFactory.getLogger(PollRepositoryImpl.class);
    @Override
    public void createPoll(Poll poll) {
        String sql = "INSERT INTO " + POLL_TABLE_NAME + " " + "(question, first_answer, second_answer, third_answer, fourth_answer) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                poll.getQuestion(),
                poll.getFirstAnswer(),
                poll.getSecondAnswer(),
                poll.getThirdAnswer(),
                poll.getFourthAnswer()
        );
    }
    @Override
    public Poll readPoll(Integer id) {
        String sql = "SELECT * FROM " + POLL_TABLE_NAME + " WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, pollMapper, id);
        } catch (EmptyResultDataAccessException e) {
            logger.info(String.format("USER WITH ID %s IS NOT EXIST",id));
            return null;
        }
    }
    @Override
    public void updatePoll(Poll poll) {
        String sql = "UPDATE " + POLL_TABLE_NAME + " SET question=?, first_answer=?, second_answer=?, third_answer=?, fourth_answer=? WHERE id=?";
        try {
            jdbcTemplate.update(
                    sql,
                    poll.getQuestion(),
                    poll.getFirstAnswer(),
                    poll.getSecondAnswer(),
                    poll.getThirdAnswer(),
                    poll.getFourthAnswer(),
                    poll.getPollId()
            );
        } catch (EmptyResultDataAccessException e) {
            logger.warn(String.format("POLL WITH ID %s IS NOT EXIST",poll.getPollId()));
        }
    }
    @Override
    public void deletePoll(Integer id) {
        String sql = "DELETE FROM " + POLL_TABLE_NAME + " WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
    @Override
    public List<Poll> readAllPoll() {
        String sql = "SELECT * FROM " + POLL_TABLE_NAME + "";
        try {
            return jdbcTemplate.query(sql, pollMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.info("POLL LIST IS EMPTY");
            return null;
        }
    }
}
