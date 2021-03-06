package com.topteer.topteer.repositories;

import com.topteer.topteer.models.Events;
import com.topteer.topteer.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Query("from Organization org where org.orgName like %:term%")
    Organization findByName(String term);

    @Query("from Organization org where org.user.id = :num ")
    Organization findByUserId(long num);

    @Query("from Organization org where org.orgName like %:query%")
    List<Organization> findAllByOrgNameContaining(String query);


    @Query("from Organization org where org.orgName like %:query% or org.city like %:query% or org.zip like %:query%")
    List<Organization> findAllByOrgsContainingQuery(String query);

    Organization getById(long orgId);
}
