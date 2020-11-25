package pl.gregorymartin.touristapp.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlUserRepository extends JpaRepository<User, Long> {
}
