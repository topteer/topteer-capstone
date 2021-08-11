//package com.topteer.topteer.models;
//
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "orgCoord", uniqueConstraints = {@UniqueConstraint(columnNames = {"ordID", "eCoordId"})})
//public class OrgCoord {
//
//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "orgID")
//    @JoinColumn(name = "eCoordId")
//    private Organization organization;
//
//    private long orgId;
//    private long eCoordId;
//
//
//    public OrgCoord() {
//    }
//
//    public OrgCoord(long orgId, long eCoordId){
//        this.orgId = orgId;
//        this.eCoordId = eCoordId;
//    }
//
//    public OrgCoord(Organization organization) {
//        this.organization = organization;
//    }
//
//    public long getOrgId() {
//        orgId = organization.getId();
//        return orgId;
//    }
//
//    public void setOrgId(long orgId) {
//        this.orgId = orgId;
//    }
//
//
//
//    public long geteCoordId(){
//        eCoordId = organization.getUserId();
//        return eCoordId;
//    }
//
//    public void seteCoordId() {
//        this.eCoordId = eCoordId;
//    }
//}
