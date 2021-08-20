package com.topteer.topteer.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    List<Organization> organization;

    @ManyToMany(cascade = CascadeType.ALL)

    @JoinTable(name="eventVolunteer", joinColumns={@JoinColumn(name="user_id")},inverseJoinColumns={@JoinColumn(name="event_id")})
    private List<Events> events;

    @Column(nullable = false, length = 35)
    private String firstName;

    @Column(nullable = false, length = 35)
    private String lastName;

    @Column(nullable = false, length = 35)
    private String username;

    @Column(length = 20, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(User copy) {
        this.id = copy.id;
        this.firstName = copy.firstName;
        this.lastName = copy.lastName;
        this.username = copy.username;
        this.email = copy.email;
        this.password = copy.password;
    }

    public User(long id, String firstName, String lastName, String userName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(List<Events> events) {
        this.events = events;
    }

    public User(long id, List<Events> events) {
        this.id = id;
        this.events = events;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }
}



