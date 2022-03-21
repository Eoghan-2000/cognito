//Eoghan Martin - C18342116 - Final Year Project - Cognito
package ie.app.cognito.FrontController;


import ie.app.cognito.Service.UserService;
import org.neo4j.driver.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

//Called by the front end to show the degrees of seperation between to particluar users if any.
@RestController
@RequestMapping(path = "/getDos/{user}/{user2}", method = RequestMethod.GET)
public class DegreeOfSeperation {

    private final UserService userService;

    @Autowired
    public DegreeOfSeperation(UserService userService) {
        this.userService = userService;
    }
    //calls the user service method that will query the database
    @GetMapping
    public String getDOS(@PathVariable("user")String user,@PathVariable("use2")String user2){
        String result;
        Record r = userService.RequestDOS(user,user2);
        result = r.toString();
        return result;
    }

}
