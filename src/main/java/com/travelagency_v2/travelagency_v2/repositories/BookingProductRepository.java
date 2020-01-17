package com.travelagency_v2.travelagency_v2.repositories;

import com.travelagency_v2.travelagency_v2.entities.BookingProduct;
import com.travelagency_v2.travelagency_v2.entities.Cruise;
import com.travelagency_v2.travelagency_v2.entities.Flight;
import com.travelagency_v2.travelagency_v2.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BookingProductRepository extends JpaRepository<BookingProduct, Long> {

    BookingProduct findOneById(Long id);

    BookingProduct findOneByName(String bookingProduct);

    @Query(value = "select h from Hotel as h where h.id = :id")
    Hotel findHotelById(@Param(value = "id") long id);

    @Query(value = "select h from Hotel as h")
    List<Hotel> findAllHotels();

    @Query(value = "select c from Cruise as c where c.id = :id")
    Cruise findCruiseById(@Param(value = "id") long id);

    @Query(value = "select c from Cruise as c")
    List<Cruise> findAllCruises();

    @Query(value = "select f from Flight as f where f.id = :id")
    Flight findFlightById(@Param(value = "id") long id);

    @Query(value = "select f from Flight as f")
    List<Flight> findAllFlights();


}
