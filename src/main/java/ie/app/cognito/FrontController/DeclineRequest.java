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
@RequestMapping(value = "api/denyreq/{username1}/{username2}", method = RequestMethod.POST)
public class DeclineRequest {
    private final UserSqlService uSqlService;

    @Autowired
    public DeclineRequest(UserSqlService uSqlService) {
        this.uSqlService = uSqlService;

    }
    //calls the user service method to search the database for a particular user
    @GetMapping
    public void decline(@PathVariable("username1")String username1, @PathVariable("username2") String username2){
        uSqlService.declineNotification(username1, username2);
    }
}
