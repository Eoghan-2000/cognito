package ie.app.cognito.FrontController;


import ie.app.cognito.Service.UserSqlService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/makepost/{user}/{post}", method = RequestMethod.POST)
public class MakePost {
    private final UserSqlService userService;

    @Autowired
    public MakePost(UserSqlService userService) {

        this.userService = userService;
    }
    //calls the user service method to get notifications for a particular user
    @GetMapping
    public void makeUserPost(@PathVariable("user")String user,@PathVariable("post")String post){
        userService.makePost(user,post);
    }
    
}
