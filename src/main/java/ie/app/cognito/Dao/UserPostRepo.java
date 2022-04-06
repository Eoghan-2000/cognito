package ie.app.cognito.Dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import ie.app.cognito.Business.UserPosts;

import java.util.List;


public interface UserPostRepo extends CrudRepository<UserPosts,Long>{

    @Query(value = "Select * from user_posts where Username in :user ORDER BY TIME DESC", nativeQuery = true)
    List<UserPosts> getUserPosts(String[] user);

    @Query( value = "Select Username FROM user_posts where Username in :s and CAST(Time AS DATE) = CAST(CURRENT_DATE AS DATE) GROUP BY Username  having Count(*) >40", nativeQuery =  true)
    List<String> getPostSpam(List<String> s);
    
    @Transactional
    @Modifying
    @Query(value = "update user_posts set username = ?2 where username = ?1", nativeQuery = true)
    void editUserName(String user, String newuser);
}
