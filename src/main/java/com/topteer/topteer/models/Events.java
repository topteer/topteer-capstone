package com.topteer.topteer.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name="events")

public class Events {

    //ID generation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Foreign Key relationship
    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization org;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="eventVolunteer", joinColumns={@JoinColumn(name="event_id")},inverseJoinColumns={@JoinColumn(name="user_id")})
    List<User> eventvolunteer;

    @NotBlank(message = "You must input a title")
    @Column(nullable = false, length = 80)
    private String title;

    @NotBlank(message = "You must insert a description")
    @Column(nullable = false, length = 500)
    private String description;

    //Foreign Key relationship
    @ManyToOne
    @JoinColumn (name = "e_coordid")
    private User user;

    //phone column creation
    @NotBlank(message = "You must enter a phone number")
    @Size(min = 10, message = "Phone Number must be at least 10 digits")
    @Size(max = 13, message = "Phone Number must can't be longer then 13 characters")
    @Column(nullable = false, length = 13)
    private String phone;

    //date column creation
    @NotBlank(message = "You must enter a date for the event")
    @Column(nullable = false, length = 13)
    private String date;

    //Start time column creation
    @NotBlank(message = "You must enter a time for your event")
    @Column(nullable = false, length = 8)
    private String startTime;

    //End time column creation
    @NotBlank(message = "You must enter a end time for your event")
    @Column(nullable = false, length = 8)
    private String endTime;

    //location column creation
    @NotBlank(message ="You must enter a location for your event")
    @Column(nullable = false, length = 165)
    private String location;

    //hours column creation
    @Column(nullable = false)
    private double hours;

    public Events(long id, Organization org, List<User> eventvolunteer, String title, String description, User user, String phone, String date, String startTime, String endTime, String location, double hours) {
        this.id = id;
        this.org = org;
        this.eventvolunteer = eventvolunteer;
        this.title = title;
        this.description = description;
        this.user = user;
        this.phone = phone;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.hours = hours;
    }

    public Events(List<User> eventvolunteer) {
        this.eventvolunteer = eventvolunteer;
    }

    public Events(Organization org, String title, String description, User user, String phone, String date, String startTime, String endTime, String location, double hours) {
        this.org = org;
        this.title = title;
        this.description = description;
        this.user = user;
        this.phone = phone;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.hours = hours;
    }

    public Events() {

    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public List<User> getEventvolunteer() {
        return eventvolunteer;
    }

    public void setEventvolunteer(List<User> eventvolunteer) {
        this.eventvolunteer = eventvolunteer;
    }
}
