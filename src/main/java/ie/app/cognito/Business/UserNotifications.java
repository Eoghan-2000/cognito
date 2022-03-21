package ie.app.cognito.Business;

import javax.persistence.*;

@Entity
 public class UserNotifications {
     @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
     private Long id;
     @Column(name = "User1")
     private String User1;
     @Column(name = "User2")
     private String User2;

    public UserNotifications(String user1, String user2) {
        User1 = user1;
        User2 = user2;
    }
    public UserNotifications(){}

    public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getUser1() {
         return User1;
     }

     public void setUser1(String User1) {
         this.User1 = User1;
     }

     public String getUser2() {
         return User2;
     }

     public void setUser2(String User2) {
         this.User2 = User2;
     }
 }