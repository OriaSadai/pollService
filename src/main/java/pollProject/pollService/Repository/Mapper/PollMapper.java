package pollProject.pollService.Repository.Mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pollProject.pollService.Model.Poll;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PollMapper implements RowMapper<Poll> {
    @Override
    public Poll mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Poll (
                rs.getInt("id"),
                rs.getString("question"),
                rs.getString("first_answer"),
                rs.getString("second_answer"),
                rs.getString("third_answer"),
                rs.getString("fourth_answer")
        );
    }
}
