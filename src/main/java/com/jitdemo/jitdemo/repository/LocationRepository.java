package com.jitdemo.jitdemo.repository;

import com.jitdemo.jitdemo.model.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Locations, Integer> {


    @Query(value = "from Locations where user.id = :uId AND  (locationCreatedOn BETWEEN :startDate AND :endDate)")
    List<Locations> getAllBetweenDates(@Param("uId") UUID uId , @Param("startDate")Date startDate, @Param("endDate") Date endDate);
}
