package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.FavoriteResponse;
import peaksoft.dto.ProductResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.entity.Favorite;
import peaksoft.entity.Product;
import peaksoft.service.FavoriteService;
import peaksoft.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteApi {
    private final ProductService productService;
    private final FavoriteService favoriteService;

    @GetMapping
    public List<Favorite> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }


    @PostMapping("/add")
    public SimpleResponse addToFavorites(@RequestParam Long productId,
                                         @RequestParam Long userId) {
          return favoriteService.addToFavorites(productId, userId);
    }
    @PostMapping("/remove")
    public SimpleResponse removeFromFavorites(@RequestParam Long productId,
                                              @RequestParam Long userId) {
        return favoriteService.removeFromFavorites(productId, userId);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> getFavoriteProductsByUserId(@PathVariable Long userId) {
        List<Product> favoriteProducts = favoriteService.getFavoriteProductsByUser(userId);
        return ResponseEntity.ok(favoriteProducts);
    }
}
