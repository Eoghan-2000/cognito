package ie.app.cognito.Business;

//import org.apache.tomcat.jni.Local;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.neo4j.core.schema.*;
//import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node("User")
public class User {
    @Id @GeneratedValue
    private Long id;
    @Property("username")
    private String username;
    @Property("firstname")
    private String firstname;
    @Property("surname")
    private String surname;
    @Property("dateTimeJoined")
    private LocalDate dateTimeJoined;
    @Property("dob")
    private LocalDate dob;
    @Property("location")
    private String location;
    @Property("Email")
    private String email;
    @Property("flagged")
    private Boolean flagged;
    @Transient
    private int age;
    @Relationship
    private List<User> trusts = new ArrayList<>();

    public User(String username, String firstname, String surname, LocalDate dateTimeJoined, LocalDate dob, String location, String email, Boolean flagged, List<User> trusts) {
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.dateTimeJoined = dateTimeJoined;
        this.dob = dob;
        this.location = location;
        this.email = email;
        this.flagged = flagged;
        this.trusts = trusts;
    }
    public User(String username, String firstname, String surname, LocalDate dateTimeJoined, LocalDate dob, String location, String email,Boolean flagged) {
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.dateTimeJoined = dateTimeJoined;
        this.dob = dob;
        this.location = location;
        this.flagged = flagged;
        this.email = email;
    }
    
    public Boolean getFlagged() {
        return flagged;
    }
    public void setFlagged(Boolean flagged) {
        this.flagged = flagged;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateTimeJoined() {
        return dateTimeJoined;
    }

    public void setDateTimeJoined(LocalDate dateTimeJoined) {
        this.dateTimeJoined = dateTimeJoined;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<User> getTrusts() {
        return trusts;
    }

    public void setTrusts(List<User> trusts) {
        this.trusts = trusts;
    }
}
