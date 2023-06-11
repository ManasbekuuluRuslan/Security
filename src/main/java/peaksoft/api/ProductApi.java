package peaksoft.api;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.*;
import peaksoft.service.CommentService;
import peaksoft.service.ProductService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductApi {
    private final ProductService productService;
    private final CommentService commentService;

    @PostMapping("/{id}/comments")
    public  SimpleResponse addCommentToProduct(@PathVariable Long id,
            @RequestParam Long userId,
            @RequestParam String comment) {
        return productService.addCommentToProduct(id,userId, comment);

    }

    @PostMapping("/{productId}/like")
    public SimpleResponse addLikeToProduct(@PathVariable Long productId,
                                           @RequestParam Long userId) {
       return productService.addLikeToProduct(productId, userId);

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveProduct(@RequestBody ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }


    @PermitAll
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @PermitAll
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
}

