package com.travelagency_v2.travelagency_v2.repositories;

import com.travelagency_v2.travelagency_v2.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String city);
}
