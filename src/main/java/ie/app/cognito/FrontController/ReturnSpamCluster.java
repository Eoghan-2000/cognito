//Eoghan Martin - C18342116 - Final Year Project - Cognito
package ie.app.cognito.FrontController;

import ie.app.cognito.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Called from front end to find spam clusters
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping(path = "api/spamAccounts")
public class ReturnSpamCluster {
    private final UserService userService;

    @Autowired
    public ReturnSpamCluster(UserService userService) {
        this.userService = userService;
    }

    //calls the user service class method to find spam clusters in the database
    @GetMapping
    public List<String> findSpam(){
        List<String> r = userService.spamCluster();
        return r;
    }

}