//package com.topteer.topteer.repositories;
//
//import com.topteer.topteer.models.OrgCoord;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//
//public interface OrgCoordRepository extends JpaRepository<OrgCoord, Long> {
//    @Query("from Organization org where org.userId = %:term%")
//    OrgCoord findByUserId(long term);
//}
