package ie.app.cognito.FrontController;


import ie.app.cognito.Service.UserSqlService;
import java.util.List;
import ie.app.cognito.Business.UserMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/getmessages/{id1}/{id2}", method = RequestMethod.POST)
public class GetMessages{
    private final UserSqlService userService;

    @Autowired
    public GetMessages(UserSqlService userService) {

        this.userService = userService;
    }
    //calls the user service method to get notifications for a particular user
    @GetMapping
    public List<UserMessages> getUserMessages(@PathVariable("id1")String possibleid1,@PathVariable("id2")String possibleid2){
        return userService.getMessages(possibleid1,possibleid2);
    }
    
}
