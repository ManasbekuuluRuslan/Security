package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.ProductResponse;
import peaksoft.entity.Product;
import peaksoft.enums.Category;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select new peaksoft.dto.ProductResponse (p.id,p.name,p.price,p.images,p.characteristic,p.madeIn,p.category)" +
            " from Product  p order by p.price asc ")
    List<ProductResponse> getAllProducts();
    Optional<ProductResponse> findProductById(Long id);
}

