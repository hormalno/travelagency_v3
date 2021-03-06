package com.travelagency_v2.travelagency_v2.repositories;

import com.travelagency_v2.travelagency_v2.entities.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long>{
    Airline findByName(String airlineName);
}
