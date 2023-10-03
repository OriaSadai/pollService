package pollProject.pollService.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (
        name = "userService",
        url = "${userService.url}"
)

public interface UserService {
    @GetMapping(value = "/user/confirm/{id}")
    Boolean checkUserConfirmed(@PathVariable(value = "id") Long userId);
    @GetMapping(value = "/user/register/{id}")
    Boolean checkUserRegistration(@PathVariable(value = "id") Long userId);
}
