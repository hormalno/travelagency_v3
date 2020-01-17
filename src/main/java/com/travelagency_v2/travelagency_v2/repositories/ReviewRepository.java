package com.travelagency_v2.travelagency_v2.repositories;

import com.travelagency_v2.travelagency_v2.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


    /*@Query(value = "select * from reviews as r " +
            "where r.booking_product_id = :id " +
            "order by r.release_date desc " +
            "limit 5",
            nativeQuery = true)*/
    List<Review> findAllByBookingProductId(long id);

    List<Review> findAllByUserUsername(String username);
}
