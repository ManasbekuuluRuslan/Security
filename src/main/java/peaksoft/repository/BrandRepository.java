package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand,Long> {

}
