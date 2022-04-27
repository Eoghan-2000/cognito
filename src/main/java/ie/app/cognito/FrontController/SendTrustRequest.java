package ie.app.cognito.FrontController;

import ie.app.cognito.Service.UserSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping(value = "api/sendtrust/{username1}/{username2}", method = RequestMethod.POST)
public class SendTrustRequest{
    private final UserSqlService userService;

    @Autowired
    public SendTrustRequest(UserSqlService userService) {
        this.userService = userService;
    }
    //calls the user service method to send trust to a particular user
    @GetMapping
    public String sendTrust(@PathVariable("username1")String username1, @PathVariable("username2") String username2){
        userService.sendTrust(username1,username2);
        return "Success";
    }
}
