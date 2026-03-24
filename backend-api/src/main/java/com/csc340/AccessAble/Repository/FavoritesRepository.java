package com.csc340.AccessAble.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csc340.AccessAble.Entities.Favorites;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

  @Query(value = "SELECT f.* FROM favorites f WHERE f.customer_id = :customerId", nativeQuery = true)
  List<Favorites> findByCustomerId(Long customerId);

}