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

    //Foreign Key relationship
    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization org;

    @NotBlank(message = "You must input a title")
    @Column(nullable = false, length = 80)
    private String title;

    @NotBlank(message = "You must insert a descrtipion")
    @Column(nullable = false, length = 500)
    private String description;

    //Foreign Key relationship
    @ManyToOne
    @JoinColumn (name = "eCoordID")
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

    @ManyToOne
    @JoinColumn(name = "org_id_id")
    private Organization org_id;

    public Organization getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Organization org_id) {
        this.org_id = org_id;
    }

    public Events(long orgId, String title, String description, long eCoordId, String phone, String date, String time, String location, double hours, double length) {
    }

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

    public Events() {
    }
}
