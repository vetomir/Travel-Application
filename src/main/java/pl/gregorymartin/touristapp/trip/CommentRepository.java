package pl.gregorymartin.touristapp.trip;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Page<Comment> findAll(Pageable pageable);

    Optional<Comment> findById(long id);

    List<Comment> findCommentsByUser(long id);

    List<Comment> findCommentsByOffer(long id);

    Optional<Comment> findCommentByUserAndOffer(long userId, long offerId);

    Comment save(Comment comment);
}
