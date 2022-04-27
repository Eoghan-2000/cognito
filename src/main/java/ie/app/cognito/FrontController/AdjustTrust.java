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
@RequestMapping(value = "api/adjusttrust/{username1}/{username2}/{trust}/{trustType}", method = RequestMethod.POST)
public class AdjustTrust {
    private final UserService userService;

    @Autowired
    public AdjustTrust(UserService userService) {
        this.userService = userService;

    }
    //calls the user service method to search the database for a particular user
    @GetMapping
    public void adjustTrust(@PathVariable("username1")String username1, @PathVariable("username2") String username2, @PathVariable("trust")int trust,@PathVariable("trustType") String type){
        userService.AdjustTrust(username1,username2,trust, type);
    }
}
