package ie.app.cognito.Dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ie.app.cognito.Business.UserNotifications;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


public interface SqlRepository extends CrudRepository<UserNotifications,Long>{

    @Query( value = "Select * from user_notifications where user1 = ?1", nativeQuery = true)
    List<UserNotifications> getNotifications(String User);

    @Transactional
    @Modifying
    @Query(value="Delete from user_notifications where user1 = ?1 and user2 = ?2", nativeQuery= true)
    void deleteNotification(String username1, String username2);
}
