package peaksoft.service;

import peaksoft.dto.FavoriteResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.entity.Favorite;
import peaksoft.entity.Product;

import java.util.List;

public interface FavoriteService {
    SimpleResponse addToFavorites(Long productId, Long userId);
    SimpleResponse removeFromFavorites(Long productId, Long userId);
    List<Favorite> getAllFavorites();
    List<Product> getFavoriteProductsByUser(Long userId);
}
