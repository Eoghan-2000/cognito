package ie.app.cognito.FrontController;

import ie.app.cognito.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/isFriend/{user1}/{user2}", method = RequestMethod.GET)
public class IsFriend {
    private final UserService userService;

    @Autowired
    public IsFriend(UserService userService) {

        this.userService = userService;
    }
    //calls the user service method to get notifications for a particular user
    @GetMapping
    public boolean isFriend(@PathVariable("user1")String user1, @PathVariable("user2")String user2){
        boolean result = userService.isFriend(user1,user2);
        return result;
    }
}
