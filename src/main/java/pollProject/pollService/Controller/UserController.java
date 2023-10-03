package pollProject.pollService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pollProject.pollService.User.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/exist/{userId}")
    public Boolean checkUserExist(@PathVariable Long userId) {
        return userService.checkUserConfirmed(userId);
    }
    @GetMapping(value = "/registration/{userId}")
    public Boolean readUserRegistration(@PathVariable Long userId) {
        return userService.checkUserRegistration(userId);
    }

}
