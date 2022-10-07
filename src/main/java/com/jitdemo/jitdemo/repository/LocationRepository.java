package com.jitdemo.jitdemo.repository;

import com.jitdemo.jitdemo.model.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Locations, Integer> {
}
