package pollProject.pollService.Repository;

import org.springframework.dao.EmptyResultDataAccessException;
import pollProject.pollService.Model.Poll;

import java.util.List;

public interface PollRepository {
    public void createPoll(Poll poll);
    public Poll readPoll(Integer id);
    public void updatePoll(Poll poll) throws EmptyResultDataAccessException;
    public void deletePoll(Integer id);
    public List<Poll> readAllPoll() throws EmptyResultDataAccessException;
}
