package pollProject.pollService.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pollProject.pollService.Model.Answer;
import pollProject.pollService.Model.AnswerNumber;
import pollProject.pollService.Model.UserAnswers;
import pollProject.pollService.Service.AnswersService;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(value = "/answers")
public class AnswersController {
    @Autowired
    private AnswersService answersService;
    private static final Logger logger = LoggerFactory.getLogger((AnswersController.class));
    @PostMapping(value = "/create")
    public void createAnswers(@RequestBody List<Answer> answers) {
        logger.info(String.format("IN THE ANSWER CONTROLLER: A REQUEST TO CREATE ANSWERS FROM USER ID:\"%s\". GOT:\"%s\" ANSWERS.", answers.get(0).getUserId(), answers.size()));
        answersService.createAnswers(answers);
    }
    @DeleteMapping(value = "/delete/{id}")
    public void deleteAnswersByUserId(@PathVariable(value = "id") Long userId) {
        logger.info(String.format("IN THE ANSWER CONTROLLER: A REQUEST FROM USER ID:\"%s\" TO DELETE HIS ANSWERS. GOT:\"%s\" ANSWERS.", userId));
        answersService.deleteAnswers(userId);
    }
    @GetMapping(value = "/firstGet")
    public Map<AnswerNumber, Integer> getUsersChosenByPoll(@RequestParam(value = "id") Integer pollId) {
        logger.info(String.format("IN THE ANSWER CONTROLLER: FIRST BUSINESS REQUEST: HOW MANY USERS CHOOSE EACH OF THE QUESTION OPTION FOR POLL:\"%s\".",pollId));
        return answersService.getUsersChosenByPoll(pollId);
    }
    @GetMapping(value = "/secondGet")
    public Integer getUsersAnswerByPoll(@RequestParam(value = "id") Integer pollId) {
        logger.info(String.format("IN THE ANSWER CONTROLLER: SECOND BUSINESS REQUEST: HOW MANY USERS ANSWER TO POLL:\"%s\" IN TOTAL FOR.",pollId));
        return answersService.getUsersAnswerByPoll(pollId);
    }
    @GetMapping(value = "/thirdGet")
    public Map<Integer, AnswerNumber> getAnswerEachPollByUser(@RequestParam(value = "id") Long userId) {
        logger.info(String.format("IN THE ANSWER CONTROLLER: THIRD BUSINESS REQUEST: USER ID:\"%s\" ANSWER TO EACH QUESTION.",userId));
        return answersService.getAnswerEachPollByUser(userId);
    }
    @GetMapping(value = "/fourthGet")
    public Integer getAnswersByUser(@RequestParam(value = "id") Long userId) {
        logger.info(String.format("IN THE ANSWER CONTROLLER: FORTH BUSINESS REQUEST: HOW MANY QUESTIONS USER ID:\"%s\" ANSWERED TO.",userId));
        return answersService.getAnswersByUser(userId);
    }
    @GetMapping(value = "/fifthGet")
    public List<Map<AnswerNumber, Integer>> getAllAnswersEachPollEachOption() {
        logger.info(String.format("IN THE ANSWER CONTROLLER: FIFTH BUSINESS REQUEST: FOR EACH QUESTION HOW MANY USERS CHOOSE EACH OF THE QUESTION OPTIONS."));
        return answersService.getAllAnswerEachPollEachOption();
    }
}
