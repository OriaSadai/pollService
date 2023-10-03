package pollProject.pollService.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pollProject.pollService.Model.Poll;
import pollProject.pollService.Repository.PollRepository;
import pollProject.pollService.User.UserService;

import java.util.List;

@Service
public class PollServiceImpl implements PollService {
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(PollServiceImpl.class);
    @Override
    public void createPoll(Poll poll) {
        pollRepository.createPoll(poll);
    }
    @Override
    public Poll readPoll(Integer id) {
        return pollRepository.readPoll(id);
    }
    @Override
    public void updatePoll(Poll poll) {
        pollRepository.updatePoll(poll);
    }
    @Override
    public void deletePoll(Integer id) {
        pollRepository.deletePoll(id);
    }
    @Override
    public List<Poll> readAllPoll(Long userId) {
        Boolean isUserConfirmed = false;
//        isUserConfirmed = userService.checkUserConfirmed(userId);
        logger.info(String.format("IN THE SERVICE: THE REGISTRATION RECEIVED:\"%s\"",isUserConfirmed));
        isUserConfirmed = true; //Delete later
        if (isUserConfirmed) {
            return pollRepository.readAllPoll();
        } else {
            return null;
        }
    }
}
