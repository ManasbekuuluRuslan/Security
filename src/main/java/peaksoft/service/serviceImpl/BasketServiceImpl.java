package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.entity.Basket;
import peaksoft.entity.Product;
import peaksoft.entity.User;
import peaksoft.repository.BasketRepository;
import peaksoft.service.BasketService;
@Service
@Transactional
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    @Override
    public SimpleResponse addToBasket(Long productId, Long userId) {
        Basket basket = basketRepository.findByUserId(userId).orElse(new Basket());
        User user = new User();
        user.setId(userId);
        basket.setUser(user);
        Product product = new Product();
        product.setId(productId);
        basket.getProducts().add(product);
        basketRepository.save(basket);
        return  SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Product with id %s is successfully" +
                        "added to basket с помощю with User id: "+userId +" !", productId))
                .build();
    }

    @Override
    public SimpleResponse cancelBasket(Long userId) {
         basketRepository.deleteByUserId(userId);
         return  SimpleResponse.builder()
                 .status(HttpStatus.OK)
                 .message(String.format("Deleted "+ userId))
                 .build();
    }
}
