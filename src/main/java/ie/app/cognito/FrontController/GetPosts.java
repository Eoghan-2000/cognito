package ie.app.cognito.FrontController;


import ie.app.cognito.Service.UserSqlService;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ie.app.cognito.Business.UserPosts;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/getposts/{user}", method = RequestMethod.GET)
public class GetPosts {
    private final UserSqlService userService;

    @Autowired
    public GetPosts(UserSqlService userService) {

        this.userService = userService;
    }
    //calls the user service method to get notifications for a particular user
    @GetMapping
    public List<UserPosts> getUserPosts(@PathVariable("user")String[] user){ 
        List<UserPosts> uList=userService.getPosts(user);
        return uList;
    }
    
}
