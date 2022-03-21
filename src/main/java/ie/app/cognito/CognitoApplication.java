//Eoghan Martin - C18342116 - Final Year Project - Cognito
//Sping boot application main

package ie.app.cognito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CognitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CognitoApplication.class, args);

	}

}

