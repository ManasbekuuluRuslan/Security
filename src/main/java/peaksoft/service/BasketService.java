package peaksoft.service;

import peaksoft.dto.SimpleResponse;

public interface BasketService {
    SimpleResponse addToBasket(Long productId, Long userId);
    SimpleResponse cancelBasket(Long userId);
}
