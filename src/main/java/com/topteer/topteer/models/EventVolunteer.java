package com.topteer.topteer.models;


import javax.persistence.*;

@Entity
@Table(name="EventVolunteer")

public class EventVolunteer  {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "eventId")
    private Events event;

    @ManyToOne
    @JoinColumn(name = "volunteerId")
    private  User user;

    public EventVolunteer(long id, Events event, User user) {
        this.id = id;
        this.event = event;
        this.user = user;
    }

    public EventVolunteer(Events event, User user) {
        this.event = event;
        this.user = user;
    }

    public EventVolunteer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
