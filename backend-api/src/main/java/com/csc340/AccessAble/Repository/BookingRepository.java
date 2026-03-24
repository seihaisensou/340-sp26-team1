package com.csc340.AccessAble.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.csc340.AccessAble.Entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

  @Query(value = "SELECT b.* FROM booking b WHERE b.customer_id = :customerId", nativeQuery = true)
  List<Booking> findByCustomerId(Long customerId);

}