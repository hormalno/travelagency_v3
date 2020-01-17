package com.travelagency_v2.travelagency_v2.repositories;


import com.travelagency_v2.travelagency_v2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findOneByUsername(String username);
}
