package pl.gregorymartin.touristapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlVerificationTokenRepository extends VerificationTokenRepository, JpaRepository<VerificationToken, Long> {

    VerificationToken findByValue(String value);
}
