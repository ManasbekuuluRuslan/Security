package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.FavoriteResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.entity.Favorite;
import peaksoft.entity.Product;
import peaksoft.entity.User;
import peaksoft.repository.FavoriteRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.FavoriteService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public SimpleResponse addToFavorites(Long productId, Long userId) {
        if (favoriteRepository.existsByProductIdAndUserId(productId, userId)) {
            throw new RuntimeException("Product is already in favorites");
        }
        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (product == null || user == null) {
            throw new RuntimeException("Product or User not found");
        }
        Favorite favorite = new Favorite();
        favorite.setProduct(product);
        favorite.setUser(user);
        favoriteRepository.save(favorite);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Added favorite product with name: "+product.getName()))
                .build();
    }
    @Override
    public SimpleResponse removeFromFavorites(Long productId, Long userId) {
        Favorite favorite = favoriteRepository.
                findByProductIdAndUserId(productId, userId);
        if (favorite == null) {
            throw new RuntimeException("Favorite not found");
        }
        favoriteRepository.delete(favorite);
        return SimpleResponse.builder()
        .status(HttpStatus.OK)
        .message(String.format("Product with id: "+productId+ " deleted from favorites"))
        .build();
    }
    @Override
    public List<Favorite> getAllFavorites() {
        List<Favorite> favorites = favoriteRepository.findAll();
            Favorite favorite = new Favorite();
            FavoriteResponse favoriteResponse = new FavoriteResponse();
            favoriteResponse.setId(favorite.getId());
            favoriteResponse.setUser(favorite.getUser());
            favoriteResponse.setProduct(favorite.getProduct());
            favorites.add(favorite);
        return favorites;
    }
    @Override
    public List<Product> getFavoriteProductsByUser(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        List<Product> favoriteProducts = new ArrayList<>();
        for (Favorite favorite : favorites) {
            favoriteProducts.add(favorite.getProduct());
        }
        return favoriteProducts;
    }
}
