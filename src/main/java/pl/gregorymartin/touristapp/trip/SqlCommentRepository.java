package pl.gregorymartin.touristapp.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlCommentRepository extends JpaRepository<Comment, Long> {
}
