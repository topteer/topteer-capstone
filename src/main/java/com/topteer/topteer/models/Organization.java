package com.topteer.topteer.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "organization")

public class Organization {

    //id generation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //id relationship
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "orgID")
   private Events event;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "orgID")
   private OrgCoord orgCoord;

    //org_name column generation
    @NotBlank(message = "You must enter a Organization name")
    @Column(nullable = false, length = 60)
    private String org_name;

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

    public Organization() {
    }

    public Organization(OrgCoord orgCoord) {
        this.orgCoord = orgCoord;
    }

    public Organization(long id, String org_name, String address, String city, String state, String zip, String phone, String email) {
        this.id = id;
        this.org_name = org_name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public Organization(String org_name, String address, String city, String state, String zip, String phone, String email) {
        this.org_name = org_name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
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

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }
}
