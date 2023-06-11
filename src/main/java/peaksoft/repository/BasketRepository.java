package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Basket;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket,Long> {
    Optional<Basket> findByUserId(Long userId);
    void deleteByUserId(Long userId);

}
