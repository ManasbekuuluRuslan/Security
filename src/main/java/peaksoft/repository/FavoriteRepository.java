package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.FavoriteResponse;
import peaksoft.dto.ProductResponse;
import peaksoft.entity.Favorite;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    int countByProductId(Long productId);
    boolean existsByProductIdAndUserId(Long productId, Long userId);
    Favorite findByProductIdAndUserId(Long productId, Long userId);
//    @Query("select new peaksoft.dto.FavoriteResponse (f.id,f.user,f.product)" +
//            " from Favorite  f  ")
//    List<FavoriteResponse> getAllPFavorites();
    List<Favorite> findByUserId(Long userId);

}
