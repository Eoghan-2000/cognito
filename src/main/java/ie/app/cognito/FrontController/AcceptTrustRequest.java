package ie.app.cognito.FrontController;

import ie.app.cognito.Service.UserService;
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
@RequestMapping(value = "api/acceptrust/{username1}/{username2}/{trust}/{trustType}", method = RequestMethod.POST)
public class AcceptTrustRequest {
    private final UserService userService;
    private final UserSqlService uSqlService;

    @Autowired
    public AcceptTrustRequest(UserService userService,UserSqlService uSqlService) {
        this.userService = userService;
        this.uSqlService = uSqlService;

    }
    //calls the user service method to search the database for a particular user
    @GetMapping
    public void acceptedTrust(@PathVariable("username1")String username1, @PathVariable("username2") String username2, @PathVariable("trust")int trust,@PathVariable("trustType") String type){
        userService.acceptTrust(username1,username2,trust, type);
        uSqlService.removeNotification(username1, username2);
    }
}
