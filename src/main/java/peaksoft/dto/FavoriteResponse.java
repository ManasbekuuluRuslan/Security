package peaksoft.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.entity.Product;
import peaksoft.entity.User;

@Data
@NoArgsConstructor
public class FavoriteResponse {
    private Long id;
    private User user;
    private Product product;
    public FavoriteResponse(Long id, User user, Product product) {
        this.id = id;
        this.user = user;
        this.product = product;
    }
}
