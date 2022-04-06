//Eoghan Martin - C18342116 - Final Year Project - Cognito
package ie.app.cognito.FrontController;

import ie.app.cognito.Business.User;
import ie.app.cognito.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//This is where the front end will call to receive the results from searching for a particular users email
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/searchUserEmail/{email}", method = RequestMethod.GET)
public class SearchByEmail {
    private final UserService userService;

    @Autowired
    public SearchByEmail(UserService userService) {
        this.userService = userService;
    }
    //calls the user service method to search the database for a particular user given their email
    @GetMapping
    public User searchEmail(@PathVariable("email")String email){
        return userService.userSearchbyEmail(email);
    }
}
