package ie.app.cognito.FrontController;


import ie.app.cognito.Service.UserSqlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/sendmessage/{userto}/{userfrom}/{message}", method = RequestMethod.POST)
public class SendMessage {
    private final UserSqlService userService;

    @Autowired
    public SendMessage(UserSqlService userService) {

        this.userService = userService;
    }
    //calls the user service method to get notifications for a particular user
    @GetMapping
    public void makeUserPost(@PathVariable("userto")String userto,@PathVariable("userfrom")String userfrom,@PathVariable("message")String message){
        userService.sendMessage(userto,userfrom,message);
    }
    
}
