package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.CommentRequest;
import peaksoft.dto.CommentResponse;
import peaksoft.dto.ProductResponse;
import peaksoft.entity.Comment;
import peaksoft.entity.Product;
import peaksoft.repository.CommentRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.service.CommentService;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
//    @Override
//    public CommentResponse addCommentToProduct(Long productId, CommentRequest commentRequest) {
//        Optional<Product> optionalProduct = Optional.ofNullable(productRepository.findById(productId).orElseThrow(() ->
//                new NullPointerException("Product with id: "+productId+" not found")));
//        if (optionalProduct.isPresent()) {
//            Product product = optionalProduct.get();
//            Comment comment = new Comment();
//            comment.setComment(commentRequest.getComment());
//            comment.setProduct(product);
//            Comment addedComment = commentRepository.save(comment);
//            CommentResponse commentResponse = new CommentResponse();
//            commentResponse.setComment(addedComment.getComment());
//            commentResponse.setCreatedDate(addedComment.getCreatedDate());
//            return commentResponse;
//        }
//        return null;
//    }

}
