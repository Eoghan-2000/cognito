package ie.app.cognito.FrontController;

import ie.app.cognito.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/removetrust/{user1}/{user2}", method = RequestMethod.POST)
public class RemoveTrust {
    private final UserService userService;

    @Autowired
    public RemoveTrust(UserService userService) {

        this.userService = userService;
    }
    //calls the user service method to get notifications for a particular user
    @GetMapping
    public void removeTrust(@PathVariable("user1")String user1, @PathVariable("user2")String user2){
        userService.removeTrust(user1, user2);;
    }
}
