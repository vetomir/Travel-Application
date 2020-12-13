package pl.gregorymartin.touristapp.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface SqlCommentRepository extends CommentRepository, JpaRepository<Comment, Long> {

    @Query("Select r From Comment r where r.appUser.id = ?1 and r.offer.id = ?2")
    Optional<Comment> findCommentByUserAndOffer(long userId, long offerId);

    @Query("Select r From Comment r where r.appUser.id = ?1 order by r.id desc ")
    List<Comment> findCommentsByUser(long userId);

    @Query("Select r From Comment r where r.offer.id = ?1")
    List<Comment> findCommentsByOffer(long offerId);

}
