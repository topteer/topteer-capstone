package com.topteer.topteer.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name="events")

public class Events {

    //ID generation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //orgID column creation
    @Column(nullable = false)
    private long orgID;

    //Foreign Key relationship
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orgID", referencedColumnName = "id")
    private Organization org;

    //eCoordID column creation
    @Column(nullable = false)
    private long eCoordID;

    //Foreign Key relationship
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "eCoordID", referencedColumnName = "id")
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
    @NotBlank(message = "You must enter a minimum time voluteers must contribute")
    @Column(nullable = false)
    private double hours;

    //length column creation
    @NotBlank(message = "You must enter how long the even will be running for")
    @Column(nullable = false)
    private double length;

    public Events() {
    }

    public Events(long id, long orgID, Organization org, long eCoordID, User user, String phone, String date, String time, String location, double hours, double length) {
        this.id = id;
        this.orgID = orgID;
        this.org = org;
        this.eCoordID = eCoordID;
        this.user = user;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.location = location;
        this.hours = hours;
        this.length = length;
    }

    public Events(long orgID, Organization org, long eCoordID, User user, String phone, String date, String time, String location, double hours, double length) {
        this.orgID = orgID;
        this.org = org;
        this.eCoordID = eCoordID;
        this.user = user;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.location = location;
        this.hours = hours;
        this.length = length;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrgID() {
        return orgID;
    }

    public void setOrgID(long orgID) {
        this.orgID = orgID;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public long geteCoordID() {
        return eCoordID;
    }

    public void seteCoordID(long eCoordID) {
        this.eCoordID = eCoordID;
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

    public void save(Events events) {
    }
}
