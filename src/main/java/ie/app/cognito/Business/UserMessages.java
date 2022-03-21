package ie.app.cognito.Business;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class UserMessages {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name = "From_user")
    private String From;
    @Column(name = "To_user")
    private String To;
    @Column(name = "Message")
    private String Message;
    @Column(name = "Time")
    private LocalDateTime Time;

    public UserMessages(String from, String to, String message) {
        this.From = from;
        this.To = to;
        this.Message = message;
        this.Time = LocalDateTime.now();
    }
    public UserMessages(){

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFrom() {
        return From;
    }
    public void setFrom(String from) {
        From = from;
    }
    public String getTo() {
        return To;
    }
    public void setTo(String to) {
        To = to;
    }
    public String getMessage() {
        return Message;
    }
    public void setMessage(String message) {
        Message = message;
    }
    public LocalDateTime getTime() {
        return Time;
    }
    public void setTime(LocalDateTime time) {
        Time = time;
    }
}
