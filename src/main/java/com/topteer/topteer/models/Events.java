package com.topteer.topteer.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
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

    @ManyToMany(mappedBy="events")
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

    //time column creation
    @NotBlank(message = "You must enter a time for your event")
    @Column(nullable = false, length = 8)
    private String time;

    //location column creation
    @NotBlank(message ="You must enter a location for your event")
    @Column(nullable = false, length = 165)
    private String location;

    //hours column creation
    @Column(nullable = false)
    private double hours;

    //length column creation
    @Column(nullable = false)
    private double length;


    public Events(Organization org, String title, String description, User user, String phone, String date, String time, String location, double hours, double length) {
        this.org = org;
        this.title = title;
        this.description = description;
        this.user = user;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.location = location;
        this.hours = hours;
        this.length = length;
    }

    public Events(long id, Organization org, String title, String description, User user, String phone, String date, String time, String location, double hours, double length) {
        this.id = id;
        this.org = org;
        this.title = title;
        this.description = description;
        this.user = user;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.location = location;
        this.hours = hours;
        this.length = length;
    }

    public Events(List<User> eventvolunteer) {
        this.eventvolunteer = eventvolunteer;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
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
