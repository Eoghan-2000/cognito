package ie.app.cognito.FrontController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ie.app.cognito.Service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/newuser/{username}", method = RequestMethod.POST)
public class NewUser {
    private final UserService userService;

    @Autowired
    public NewUser(UserService userService) {

        this.userService = userService;
    }
    //calls the user service method to get notifications for a particular user
    @GetMapping
    public void newUser(@PathVariable("username")String username){
        userService.newUser(username,username);
    }
}
