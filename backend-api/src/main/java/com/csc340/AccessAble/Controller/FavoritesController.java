package com.csc340.AccessAble.Controller;

import com.csc340.AccessAble.Entities.Favorites;
import com.csc340.AccessAble.Service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @PostMapping
    public ResponseEntity<Favorites> createFavorites(@RequestBody Favorites favorites) {
        Favorites createdFavorites = favoritesService.createFavorites(favorites);
        return new ResponseEntity<>(createdFavorites, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Favorites>> getAllFavorites() {
        List<Favorites> favorites = favoritesService.getAllFavorites();
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorites> getFavoritesById(@PathVariable Long id) {
        Optional<Favorites> favorites = favoritesService.getFavoritesById(id);
        return favorites.map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Favorites>> getFavoritesByCustomerId(@PathVariable Long customerId) {
        List<Favorites> favorites = favoritesService.getFavoritesByCustomerId(customerId);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorites(@PathVariable Long id) {
        favoritesService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}