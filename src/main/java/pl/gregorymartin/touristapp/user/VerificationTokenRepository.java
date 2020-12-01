package pl.gregorymartin.touristapp.user;

public interface VerificationTokenRepository {
    VerificationToken save(VerificationToken verificationToken);

    VerificationToken findByValue(String token);
}
