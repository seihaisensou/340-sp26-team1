package com.csc340.AccessAble.Service;

import com.csc340.AccessAble.Entities.Favorites;
import com.csc340.AccessAble.Repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritesService {
    
    @Autowired
    private FavoritesRepository favoritesRepository;
    
    public Favorites createFavorites(Favorites favorite) {
        return favoritesRepository.save(favorite);
    }
    
    public Optional<Favorites> getFavoritesById(Long id) {
        return favoritesRepository.findById(id);
    }
    
    public List<Favorites> getAllFavorites() {
        return favoritesRepository.findAll();
    }
    
    public List<Favorites> getFavoritesByCustomerId(Long customerId) {
        return favoritesRepository.findByCustomerId(customerId);
    }
    
    public void deleteReview(Long id) {
        favoritesRepository.deleteById(id);
    }
}