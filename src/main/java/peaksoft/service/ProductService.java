package peaksoft.service;

import peaksoft.dto.*;

import java.time.ZonedDateTime;
import java.util.List;

public interface ProductService {
    SimpleResponse addCommentToProduct(Long productId,Long userId, String comment);
    SimpleResponse addLikeToProduct(Long productId, Long userId);
    SimpleResponse saveProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    SimpleResponse updateProduct(Long id, ProductRequest productRequest);

    SimpleResponse deleteProductById(Long id);

}
