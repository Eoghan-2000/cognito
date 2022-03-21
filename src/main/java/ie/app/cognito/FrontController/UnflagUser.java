package ie.app.cognito.FrontController;
//Eoghan Martin - C18342116 - Final Year Project - Cognito

import ie.app.cognito.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//This is where the front end will call to receive the results from searching for a particular search
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping(value = "api/unflagUser/{username}", method = RequestMethod.POST)
public class UnflagUser {
    private final UserService userService;

    @Autowired
    public UnflagUser(UserService userService) {
        this.userService = userService;
    }
    //calls the user service method to search the database for a particular user
    @GetMapping
    public void unflagUserReq(@PathVariable("username")String username){
        userService.unflagUser(username);
    }
}
