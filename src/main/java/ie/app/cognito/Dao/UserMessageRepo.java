package ie.app.cognito.Dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import ie.app.cognito.Business.UserMessages;

import java.util.List;

public interface UserMessageRepo extends CrudRepository<UserMessages,Long>{
    @Query( value = "Select * from user_messages where to_user = ?1 and from_user = ?2 or from_user = ?1 and to_user = ?2 ORDER BY Time ASC", nativeQuery = true)
    List<UserMessages> gMessages(String id1,String id2);

    @Query( value = "Select from_user FROM user_messages where from_user in :s and CAST(Time AS DATE) = CAST(CURRENT_DATE AS DATE) and  GROUP BY from_user having Count(DISTINCT to_user) > 7", nativeQuery =  true)
    List<String> getMessageSpam(List<String> s);

    @Transactional
    @Modifying
    @Query(value = "update user_messages set from_user = ?2 where from_user = ?1", nativeQuery = true)
    void editUserNameFrom(String user, String newuser);

    @Transactional
    @Modifying
    @Query(value = "update user_messages set to_user = ?2 where to_user = ?1", nativeQuery = true)
    void editUserNameTo(String user, String newuser);
}
