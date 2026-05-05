package com.csc340.AccessAble.Repository;

import com.csc340.AccessAble.Entities.*;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    
    @Query("SELECT c FROM Customer c JOIN c.providers p WHERE p.id = :providerId")
    List<Customer> findByProviderId(@Param("providerId") Long providerId);
}