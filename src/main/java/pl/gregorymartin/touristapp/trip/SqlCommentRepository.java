package pl.gregorymartin.touristapp.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
interface SqlCommentRepository extends JpaRepository<Comment, Long> {
}
