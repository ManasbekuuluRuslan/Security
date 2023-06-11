package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.*;
import peaksoft.entity.Comment;
import peaksoft.entity.Favorite;
import peaksoft.entity.Product;
import peaksoft.entity.User;
import peaksoft.repository.CommentRepository;
import peaksoft.repository.FavoriteRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.ProductService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    @Override
    public SimpleResponse addCommentToProduct(Long productId,Long userId, String comment) {
        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (product == null || user == null) {
            System.out.println("not not not not not not");
            return null;
        }
        Comment comment1 = new Comment();
        comment1.setUser(user);
        comment1.setProduct(product);
        product.getComments().add(comment1);
        productRepository.save(product);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Comment added to with product id:" +productId))
                .build();
        }
    @Override
    public SimpleResponse addLikeToProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (product == null || user == null) {
            System.out.println("not not not not not not");
            return null;
        }
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);
        product.getFavorites().add(favorite);
        productRepository.save(product);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Like added with product id: "+productId))
                .build();
    }

    @Override
    public SimpleResponse saveProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setCharacteristic(productRequest.getCharacteristic());
        product.setImages(productRequest.getImages());
        product.setPrice(productRequest.getPrice());
        product.setMadeIn(productRequest.getMadeIn());
        productRepository.save(product);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Product with name %s is successfully saved", productRequest.getName()))
                .build();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public ProductResponse getProductById(Long id) {
//        return productRepository.findProductById(id).orElseThrow(() ->
//                new NullPointerException("Product with id:" + id + " not found"));
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NullPointerException("Product with id: " + id + " not found");
        }
        int countLikes = favoriteRepository.countByProductId(id);
//        List<CommentResponse> comments = commentRepository.countCommentByProductId(id);
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setCategory(product.getCategory());
        response.setImages(product.getImages());
        response.setCharacteristic(product.getCharacteristic());
        response.setPrice(product.getPrice());
        response.setMadeIn(product.getMadeIn());
        response.setLikesCount(countLikes);
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCreatedDate(ZonedDateTime.now());

//        response.setComments(comments);
        return response;
    }

    @Override
    public SimpleResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product =  productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("Product with id:" + id + " not found"));
        //product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setCharacteristic(productRequest.getCharacteristic());
        product.setImages(productRequest.getImages());
        product.setPrice(productRequest.getPrice());
        product.setMadeIn(productRequest.getMadeIn());
        productRepository.save(product);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Product with name %s is successfully updated", productRequest.getCategory()))
                .build();
    }

    @Override
    public SimpleResponse deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("Product with id: " + id + " not found"));
        productRepository.delete(product);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Product with id %s is successfully deleted", id))
                .build();
    }
}