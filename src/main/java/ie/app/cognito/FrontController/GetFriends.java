package ie.app.cognito.FrontController;

import ie.app.cognito.Service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ie.app.cognito.Business.User;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/getfriends/{user}", method = RequestMethod.GET)
public class GetFriends {
    private final UserService userService;

    @Autowired
    public GetFriends(UserService userService) {

        this.userService = userService;
    }
    //calls the user service method to get notifications for a particular user
    @GetMapping
    public List<String> getUserFrienfs(@PathVariable("user")String user){
        List<String> uList= userService.getUserFriends(user);
        return uList;
    }
    
}
