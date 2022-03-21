//Eoghan Martin - C18342116 - Final Year Project - Cognito

package ie.app.cognito.Dao;

import ie.app.cognito.Business.User;

import org.springframework.data.neo4j.repository.Neo4jRepository;

//will be used in future for easy deletion and adding of users
public interface UserRepository extends Neo4jRepository<User, Long> {

}
