package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.SimpleResponse;
import peaksoft.service.BasketService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/baskets")
public class BasketApi {
    private final BasketService basketService;
    @PostMapping("/add")
    public SimpleResponse addToBasket(@RequestParam Long productId,
                                    @RequestParam Long userId) {
      return   basketService.addToBasket(productId, userId);

    }
    @PostMapping("/cancel")
    public SimpleResponse cancelBasket(@RequestParam Long userId) {
       return basketService.cancelBasket(userId);
    }
}
