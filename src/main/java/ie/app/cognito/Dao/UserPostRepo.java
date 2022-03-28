package ie.app.cognito.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import ie.app.cognito.Business.UserPosts;

import java.util.List;


public interface UserPostRepo extends CrudRepository<UserPosts,Long>{

    @Query(value = "Select * from user_posts where Username in :user ORDER BY TIME DESC", nativeQuery = true)
    List<UserPosts> getUserPosts(String[] user);

    @Query( value = "Select Username FROM user_posts where Username in :s and CAST(Time AS DATE) = CAST(CURRENT_DATE AS DATE) GROUP BY Username  having Count(*) >40", nativeQuery =  true)
    List<String> getPostSpam(List<String> s);
}
