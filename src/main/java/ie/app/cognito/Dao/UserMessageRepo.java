package ie.app.cognito.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ie.app.cognito.Business.UserMessages;

import java.util.List;

public interface UserMessageRepo extends CrudRepository<UserMessages,Long>{
    @Query( value = "Select * from user_messages where to_user = ?1 and from_user = ?2 or from_user = ?1 and to_user = ?2 ORDER BY Time ASC", nativeQuery = true)
    List<UserMessages> gMessages(String id1,String id2);
}
