package ie.app.cognito.FrontController;


import ie.app.cognito.Service.UserService;
import ie.app.cognito.Service.UserSqlService;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/editprofile/{current}/{newusername}/{newfirst}/{newlast}/{newlocation}/{newdob}", method = RequestMethod.POST)
public class EditProfile {
    private final UserService userService;
    private final UserSqlService userSqlService;

    @Autowired
    public EditProfile(UserService userService,UserSqlService userSqlService) {
        this.userSqlService = userSqlService;
        this.userService = userService;
    }
    //calls the user service method to get notifications for a particular user
    @GetMapping
    public void editProfile(@PathVariable("current")String user,@PathVariable("newusername")String newuser,@PathVariable("newfirst")String newFirstName,@PathVariable("newlast")String newSurname, @PathVariable("newlocation")String location, @PathVariable("newdob")String dob){
        userService.editProfile(user,newuser,newFirstName,newSurname,location,LocalDate.parse(dob));
        userSqlService.editProfile(user, newuser);
    }
    
}
