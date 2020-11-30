package pl.gregorymartin.touristapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
interface SqlAppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAllByUsername(String username);
}
