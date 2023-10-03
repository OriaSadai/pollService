package pollProject.pollService.Service;

import pollProject.pollService.Model.Poll;

import java.util.List;

public interface PollService {
    public void createPoll(Poll poll);
    public Poll readPoll(Integer id);
    public void updatePoll(Poll poll);
    public void deletePoll(Integer id);
    public List<Poll> readAllPoll(Long idRequest);
}
