package pl.gregorymartin.touristapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface SqlAppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAllByUsername(String username);
}
