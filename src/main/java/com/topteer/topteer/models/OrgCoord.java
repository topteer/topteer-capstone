package com.topteer.topteer.models;


import javax.persistence.*;

@Entity
@Table(name = "orgCoord")
public class OrgCoord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long orgID;
    @ManyToOne
    @JoinColumn(name = "orgID")
    private Organization organization;

    @Column(nullable = false)
    private long eCoordId;
    @ManyToOne
    @JoinColumn(name = "eCoordId")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrgCoord() {
    }

    public long getOrgID() {
        return orgID;
    }

    public void setOrgID(long orgID) {
        this.orgID = orgID;
    }

    public long geteCoordId() {
        return eCoordId;
    }

    public void seteCoordId(long eCoordId) {
        this.eCoordId = eCoordId;
    }

    public OrgCoord(long orgID, long eCoordId) {
        this.orgID = orgID;
        this.eCoordId = eCoordId;



    }
}
