package com.jitdemo.jitdemo.repository;

import com.jitdemo.jitdemo.controller.dto.userLocationsByDates.UserLocationQuery;
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

    @Query(nativeQuery = true,
            value = "select location_created_on as locationCreatedOn, latitude as latitude, longitude as longitude," +
            " user_id as userId from locations where user_id = :uId and " +
            "location_created_on between STR_TO_DATE(:startDate,'%Y-%m-%dT%H:%i:%s')  and STR_TO_DATE(:endDate,'%Y-%m-%dT%H:%i:%s')" +
                    "order by STR_TO_DATE(locationCreatedOn,'%Y-%m-%dT%H:%i:%s') asc")
    List<UserLocationQuery> getAllBetweenDates(@Param("uId") String uId , @Param("startDate")String startDate, @Param("endDate") String endDate);
}
