package com.jitdemo.jitdemo.repository;

import com.jitdemo.jitdemo.controller.dto.userLocation.LatestUserLocation;
import com.jitdemo.jitdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    String existsByEmail(String email);

    @Query(nativeQuery = true, value = "Select  u.id as userId, l.location_created_on as locationCreatedOn, " +
            "    u.email as email, u.first_name as firstName, u.second_name as secondName, " +
            "    l.latitude as latitude, l.longitude as longitude" +
            "    from user as u inner join locations l " +
            "    on u.id = l.user_id " +
            "    Where " +
            "    u.id = ?1" +
            "    And " +
            "        l.location_id = ( " +
            "    Select l1.location_id " +
            "    From locations l1 " +
            "    where l1.user_id = l.user_id " +
            "    order by l1.location_id desc " +
            "    limit 1 )")
    LatestUserLocation getLatestUserLocationById(String uuid);

}
