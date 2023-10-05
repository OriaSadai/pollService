package pollProject.pollService.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    public void createPoll(Poll poll) throws JsonProcessingException {
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
        Boolean isUserConfirmed = userService.checkUserConfirmed(userId);
        if (isUserConfirmed) {
            logger.info(String.format("READING POLL REQUESTED BY USER ID:\"%s\" - CONFIRMED! SENDING POLL :-)",userId));
            return pollRepository.readAllPoll();
        } else {
            logger.warn(String.format("READING POLL REQUESTED BY USER ID:\"%s\" - NOT CONFIRMED! NOT SEND :-(",userId));
            return null;
        }
    }
}
