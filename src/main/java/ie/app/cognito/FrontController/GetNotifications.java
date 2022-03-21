package ie.app.cognito.FrontController;

import ie.app.cognito.Business.UserNotifications;
import ie.app.cognito.Service.UserSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/getnotifications/{user}", method = RequestMethod.GET)
public class GetNotifications {
    private final UserSqlService userService;

    @Autowired
    public GetNotifications(UserSqlService userService) {

        this.userService = userService;
    }
    //calls the user service method to get notifications for a particular user
    @GetMapping
    public List<UserNotifications> getNotifications (@PathVariable("user")String username){
        return userService.getUserNotifications(username);
    }
}
