package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.dto.CommentResponse;
import peaksoft.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<CommentResponse> countCommentByProductId(Long productId);

}
