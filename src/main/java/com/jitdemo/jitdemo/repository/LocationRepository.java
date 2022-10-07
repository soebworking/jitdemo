package com.jitdemo.jitdemo.repository;

import com.jitdemo.jitdemo.model.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Locations, Integer> {

    @Query(value = "from EntityClassTable t where userId = :uId locationCreatedOn BETWEEN :startDate AND :endDate")
    public List<Locations> getAllBetweenDates(@Param("uId") String uId, @Param("startDate")Date startDate, @Param("endDate") Date endDate);
}
