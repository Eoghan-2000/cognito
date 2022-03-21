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

import java.util.List;
//This is where the front end will call to receive the results from searching for a particular search
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping(value = "api/searchUser/{searchString}", method = RequestMethod.GET)
public class SearchUsers {
    private final UserService userService;

    @Autowired
    public SearchUsers(UserService userService) {
        this.userService = userService;
    }
    //calls the user service method to search the database for a particular user
    @GetMapping
    public List<User> SearchUsersreq(@PathVariable("searchString")String searcString){
        return userService.userSearch(searcString);
    }
}
