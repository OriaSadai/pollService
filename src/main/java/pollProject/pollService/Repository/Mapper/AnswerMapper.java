package pollProject.pollService.Repository.Mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pollProject.pollService.Model.Answer;
import pollProject.pollService.Model.AnswerNumber;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AnswerMapper implements RowMapper<Answer> {
    @Override
    public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Answer (
                rs.getLong("user_id"),
                rs.getInt("poll_id"),
                AnswerNumber.valueOf(rs.getString("answer_number"))
        );
    }
}
