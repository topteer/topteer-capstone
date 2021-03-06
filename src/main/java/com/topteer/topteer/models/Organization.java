package com.topteer.topteer.models;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "orgs")

public class Organization {

    //id generation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //id relationship
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "org")
   private List<Events> events;

    //org_name column generation
    @NotBlank(message = "You must enter a Organization name")
    @Column(nullable = false, length = 60)
    private String orgName;

    //address column generation
    @NotBlank(message = "You must enter an Address")
    @Column(nullable = false, length = 70)
    private String address;

    //city column generation
    @NotBlank(message = "You must enter a city")
    @Column(nullable = false, length = 80)
    private String city;

    //state column generation
    @NotBlank(message = "You must enter a State")
    @Size(min = 2, message = "Please enter 2 letter State designation")
    @Size(max = 2, message = "Please enter 2 letter State designation")
    @Column(nullable = false, length = 2)
    private String state;

    //zip column generation
    @NotBlank(message = "Please enter a zip code")
    @Size(min = 5, message = "Please enter a 5 digit zip code")
    @Size(max = 5, message = "Please enter a 5 digit zip code")
    @Column(nullable = false, length = 5)
    private String zip;

    //phone column generation
    @NotBlank(message = "You must enter a phone number")
    @Size(min = 10, message = "Phone Number must be at least 10 digits")
    @Size(max = 13, message = "Phone Number must can't be longer then 13 characters")
    @Column(nullable = false, length = 13)
    private String phone;

    //email column generation
    @NotBlank(message = "You must enter an E-mail address")
    @Column(nullable = false, length = 80)
    private String email;


    public Organization(User user, String orgName, String address, String city, String state, String zip, String phone, String email) {
        this.user = user;
        this.orgName = orgName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public Organization(String orgName, String address, String city, String state, String zip, String phone, String email) {
        this.orgName = orgName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public Organization() {

    }

    public Organization(User user) {
        this.user = user;
    }

    public Organization(List<Events> events) {
        this.events = events;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }
}
