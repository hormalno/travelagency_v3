package com.travelagency_v2.travelagency_v2.repositories;

import com.travelagency_v2.travelagency_v2.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByUserUsername(String username);
}
