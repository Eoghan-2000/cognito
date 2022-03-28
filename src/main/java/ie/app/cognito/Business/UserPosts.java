package ie.app.cognito.Business;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
 public class UserPosts {
     @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
     private Long id;
     @Column(name = "Username")
     private String User;
     @Column(name = "Content")
     private String Content;
     @Column(name = "Time")
     private LocalDateTime Time;

    public UserPosts(String user, String content) {
        this.User = user;
        this.Content = content;
        this.Time = LocalDateTime.now();
    }

    public interface UserName {
        String getUser();
    }
    public UserPosts(){}

    public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getUser() {
         return User;
     }

     public void setUser1(String User) {
         this.User = User;
     }

     public String getContent() {
         return Content;
     }

     public void setContent(String Content) {
         this.Content = Content;
     }

     public LocalDateTime getTime(){
         return Time;
     }

     public void setTime(LocalDateTime time){
         this.Time = time;
     }
 }