package com.topteer.topteer.repositories;

import com.topteer.topteer.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Query("from Organization org where org.org_name like %:term%")
    Organization findByName(String term);

    @Query("from Organization org where org.userId = :num ")
    Organization findByUserId(long num);

}
