package ie.app.cognito.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ie.app.cognito.Business.UserPosts;

import java.util.List;



public interface UserPostRepo extends CrudRepository<UserPosts,Long>{

    @Query(value = "Select * from user_posts where Username in :user ORDER BY TIME DESC", nativeQuery = true)
    List<UserPosts> getUserPosts(String[] user);
}
