package pollProject.pollService.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pollProject.pollService.Model.Answer;
import pollProject.pollService.Model.AnswerNumber;
import pollProject.pollService.Model.Poll;
import pollProject.pollService.Model.UserAnswers;
import pollProject.pollService.Repository.AnswerRepository;
import pollProject.pollService.Repository.PollRepository;
import pollProject.pollService.User.UserService;

import java.util.*;

import static pollProject.pollService.ConstVariables.ConstVariables.DELETE_TOKEN_FROM_USER_SERVICE;

@Service
public class AnswersServiceImpl implements AnswersService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PollRepository pollRepository;
    private static final Logger logger = LoggerFactory.getLogger(AnswersServiceImpl.class);
    List<Answer> answersList = new ArrayList<>();
    Map<AnswerNumber, Integer> answerMap = new Hashtable<>();
    @Override
    public void createAnswers(UserAnswers userAnswers) throws JsonProcessingException {
        Boolean userToCheck = userService.checkUserConfirmed(userAnswers.getUserId());

        if (userToCheck) {
            logger.info(String.format("USER ID:\"%s\" CONFIRMED! CREATING ANSWERS.",userAnswers.getUserId()));
            for (int i=0; i<userAnswers.getUserAnswers().size(); i++) {
                answerRepository.createAnswer(userAnswers.getUserAnswers().get(i), userAnswers.getUserId());
            }
        } else {
            logger.error(String.format("ERROR!! USER ID:\"%s\" NOT APPROVED!",userAnswers.getUserId()));
        }
    }
    @Override
    public void deleteAnswers(Long userId, String delToken) {
        if (Objects.equals(delToken, DELETE_TOKEN_FROM_USER_SERVICE)) {
            answerRepository.deleteAnswers(userId);
        } else {
            logger.error("THE TOKEN FROM USER SERVICE NOT CORRECT!");
        }
    }
    @Override
    public Map<AnswerNumber, Integer> getUsersChosenByPoll(Integer pollId) {
        answerMap.clear();
        answersList.clear();
        answersList = answerRepository.readAllAnswersByPollId(pollId);
        Integer counter = 0;

        if (answersList.isEmpty()) {
            logger.error(String.format("ANSWER LIST FOR POLL ID:\"%s\" IS EMPTY!",pollId));
            return null;
        }

        for (short i=0; i<answersList.size(); i++) {
            if (answersList.get(i).getAnswerNumber() == AnswerNumber.FIRST_ANSWER) {
                counter++;
            }
        }
        answerMap.put(AnswerNumber.FIRST_ANSWER, counter);
        counter = 0;

        for (short i=0; i<answersList.size(); i++) {
            if (answersList.get(i).getAnswerNumber() == AnswerNumber.FIRST_ANSWER) {
                counter++;
            }
        }
        answerMap.put(AnswerNumber.SECOND_ANSWER, counter);
        counter = 0;

        for (short i=0; i<answersList.size(); i++) {
            if (answersList.get(i).getAnswerNumber() == AnswerNumber.THIRD_ANSWER) {
                counter++;
            }
        }
        answerMap.put(AnswerNumber.THIRD_ANSWER, counter);
        counter = 0;

        for (short i=0; i<answersList.size(); i++) {
            if (answersList.get(i).getAnswerNumber() == AnswerNumber.FOURTH_ANSWER) {
                counter++;
            }
        }
        answerMap.put(AnswerNumber.FOURTH_ANSWER, counter);

        return answerMap;
    }
    @Override
    public Integer getUsersAnswerByPoll(Integer pollId) {
        answersList.clear();
        answersList = answerRepository.readAllAnswersByPollId(pollId);
        if (answersList == null) {
            logger.error(String.format("ANSWER LIST FOR POLL ID:\"%s\" IS EMPTY!",pollId));
            return 0;
        } else {
            return answersList.size();
        }
    }
    @Override
    public Map<Integer, AnswerNumber> getAnswerEachPollByUser(Long userId) {
        Map<Integer, AnswerNumber> anotherAnswerMap = new Hashtable<>();
        answersList.clear();
        answersList = answerRepository.readAllAnswersByUserId(userId);
        if (answersList.isEmpty()) {
            logger.warn(String.format("ANSWER LIST FOR USER ID:\"%s\" IS EMPTY!",userId));
            return null;
        }

        for (short i=0; i<answersList.size(); i++) {
            anotherAnswerMap.put(answersList.get(i).getPollId(), answersList.get(i).getAnswerNumber());
        }
        return anotherAnswerMap;
    }
    @Override
    public Integer getAnswersByUser(Long userId) {
        answersList.clear();
        answersList = answerRepository.readAllAnswersByUserId(userId);
        if (answersList == null) {
            logger.warn(String.format("ANSWER LIST FOR USER ID:\"%s\" IS EMPTY!",userId));
            return 0;
        } else {
            return answersList.size();
        }
    }
    @Override
    public List<Map<AnswerNumber, Integer>> getAllAnswerEachPollEachOption() {
        List<Map<AnswerNumber, Integer>> answersMapList = new ArrayList<>();
        List<Poll> pollList = pollRepository.readAllPoll();
        if (pollList == null) {
            logger.error("POLL LIST IS EMPTY!");
            return null;
        }

        for (Poll poll : pollList) {
            Integer pollId = poll.getPollId();
            answerMap = this.getUsersChosenByPoll(pollId);
            answersMapList.add(new HashMap<>(answerMap));
        }

        return answersMapList;
    }
}
