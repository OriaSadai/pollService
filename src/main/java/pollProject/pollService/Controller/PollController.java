package pollProject.pollService.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pollProject.pollService.Model.Poll;
import pollProject.pollService.Service.PollService;

import java.util.List;

@RestController
@RequestMapping(value = "/poll")
public class PollController {
    @Autowired
    PollService pollService;
    @PostMapping(value = "/create")
    public void createPoll(@RequestBody Poll poll) throws JsonProcessingException {
        pollService.createPoll(poll);
    }
    @GetMapping(value = "/read/{id}")
    public Poll readPoll(@PathVariable Integer id) {
        return pollService.readPoll(id);
    }
    @PutMapping(value = "/update")
    public void updatePoll(@RequestBody Poll poll) {
        pollService.updatePoll(poll);
    }
    @DeleteMapping(value = "/delete")
    public void deletePoll(@RequestParam(value = "id") Integer id) {
        pollService.deletePoll(id);
    }
    @GetMapping(value = "/read/all")
    public List<Poll> readAllPoll(@RequestParam(value = "id") Long userIdRequest) {
        return pollService.readAllPoll(userIdRequest);
    }
}
