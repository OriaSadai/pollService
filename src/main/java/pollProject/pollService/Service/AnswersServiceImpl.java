package pollProject.pollService.Service;

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
import pollProject.pollService.Repository.PollRepositoryImpl;
import pollProject.pollService.User.UserService;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Service
public class AnswersServiceImpl implements AnswersService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PollRepository pollRepository;
    private static final Logger logger = LoggerFactory.getLogger(AnswersServiceImpl.class);
    @Override
    public void createAnswers(List<Answer> answers) {
        logger.info(String.format("IN THE ANSWERS SERVICE: CREATING A ROW IN \"ANSWERS\" TABLE"));
        Boolean userToCheck = false;
//        userToCheck = userService.checkUserConfirmed(answer.getUserId());
//        logger.info(String.format("IN THE ANSWERS SERVICE: USER WITH ID:\"%s\". CONFIRMATION:\"%s\".",answer.getUserId(),userToCheck));
        userToCheck = true; //Delete later

        if (userToCheck) {
            for (int i=0; i<answers.size(); i++) {
                answerRepository.createAnswer(answers.get(i), answers.get(i).getUserId());
            }
        }
    }
    @Override
    public void deleteAnswers(Long userId) {
        Boolean userToCheck = null;
//        userToCheck = userService.checkUserConfirmed(userId);
        logger.info(String.format(" USER WITH ID:\"%s\". CONFIRMATION:\"%s\".",userId,userToCheck));
        userToCheck = true;

        if (userToCheck) {
            answerRepository.deleteAnswers(userId);
        }
    }

    @Override
    public Map<AnswerNumber, Integer> getUsersChosenByPoll(Integer pollId) {
        List<Answer> answers = answerRepository.readAllAnswersByPollId(pollId);

        //checking values in "answer" List
        for (int i=0; i<answers.size(); i++) {
            logger.info(String.format("IN THE ANSWERS SERVICE: POLL ID:\"%s\", USER ID:\"%s\", ANSWER NUMBER:\"%s\".",answers.get(i).getPollId(),answers.get(i).getUserId(),answers.get(i).getAnswerNumber()));
        }

        if (answers == null) {
            logger.warn("IN THE ANSWERS SERVICE: ANSWER LIST IS EMPTY");
            return null;
        }
        Map<AnswerNumber, Integer> answerMap = new Hashtable<>();

        //Delete later
        logger.info(String.format("IN THE ANSWERS SERVICE: ANSWER NUMBER LENGTH:\"%s\".",AnswerNumber.values().length));


            Integer counter = 0;
            for (int j=0; j<answers.size(); j++) {
                if (answers.get(j).getAnswerNumber() == AnswerNumber.FIRST_ANSWER) {
                    counter++;
                }
            }
            answerMap.put(AnswerNumber.FIRST_ANSWER, counter);
            logger.info(String.format("IN THE ANSWERS SERVICE:\"%s\": COUNT:\"%s\".",AnswerNumber.FIRST_ANSWER.name(),answerMap.get(AnswerNumber.FIRST_ANSWER)));
            counter = 0;
            for (int j=0; j<answers.size(); j++) {
                if (answers.get(j).getAnswerNumber() == AnswerNumber.SECOND_ANSWER) {
                    counter++;
                }
            }
            answerMap.put(AnswerNumber.SECOND_ANSWER, counter);
            logger.info(String.format("IN THE ANSWERS SERVICE:\"%s\": COUNT:\"%s\".",AnswerNumber.SECOND_ANSWER.name(),answerMap.get(AnswerNumber.SECOND_ANSWER)));
            counter = 0;
            for (int j=0; j<answers.size(); j++) {
                if (answers.get(j).getAnswerNumber() == AnswerNumber.THIRD_ANSWER) {
                    counter++;
                }
            }
            answerMap.put(AnswerNumber.THIRD_ANSWER, counter);
            logger.info(String.format("IN THE ANSWERS SERVICE:\"%s\": COUNT:\"%s\".",AnswerNumber.THIRD_ANSWER.name(),answerMap.get(AnswerNumber.THIRD_ANSWER)));
            counter = 0;
            for (int j=0; j<answers.size(); j++) {
                if (answers.get(j).getAnswerNumber() == AnswerNumber.FOURTH_ANSWER) {
                    counter++;
                }
            }
            answerMap.put(AnswerNumber.FOURTH_ANSWER, counter);
            logger.info(String.format("IN THE ANSWERS SERVICE:\"%s\": COUNT:\"%s\".",AnswerNumber.FOURTH_ANSWER.name(),answerMap.get(AnswerNumber.FOURTH_ANSWER)));

        return answerMap;
    }
    @Override
    public Integer getUsersAnswerByPoll(Integer pollId) {
        List<Answer> answers = answerRepository.readAllAnswersByPollId(pollId);
        if (answers == null) {
            return 0;
        } else {
            return answers.size();
        }
    }
    @Override
    public Map<Integer, AnswerNumber> getAnswerEachPollByUser(Long userId) {

        List<Answer> answers = answerRepository.readAllAnswersByUserId(userId);

        if (answers == null) {
            return null;
        }
        Map<Integer, AnswerNumber> answerMap = new Hashtable<>();
        for (int i=0; i<answers.size(); i++) {
            answerMap.put(answers.get(i).getPollId(), answers.get(i).getAnswerNumber());
        }
        return answerMap;
    }
    @Override
    public Integer getAnswersByUser(Long userId) {
        List<Answer> answers = answerRepository.readAllAnswersByUserId(userId);
        if (answers == null) {
            return 0;
        } else {
            return answers.size();
        }
    }
    @Override
    public List<Map<AnswerNumber, Integer>> getAllAnswerEachPollEachOption() {
        List<Map<AnswerNumber, Integer>> answersMapList = new ArrayList<>();
        List<Poll> pollList = pollRepository.readAllPoll();
        //checking values in "answer" List
        for (int i=0; i<pollList.size(); i++) {
            logger.info(String.format("IN THE ANSWERS SERVICE: POLL LIST ID:\"%s\".",pollList.get(i).getPollId()));
        }
        int pollLength = pollList.size();
        logger.info(String.format("IN THE ANSWERS SERVICE: POLL LIST LENGTH:\"%s\".",pollLength));

        for (int i=0; i<pollLength; i++) {
            Integer pollId = pollList.get(i).getPollId();
            logger.info(String.format("IN THE ANSWERS SERVICE: POLL ID:\"%s\".",pollId));

            answersMapList.add(this.getUsersChosenByPoll(pollId));
            logger.info(String.format("IN THE ANSWERS SERVICE: ANSWERS MAP LIST SIZE:\"%s\".",answersMapList.size()));
        }
        return answersMapList;
    }
}
