package pl.gregorymartin.touristapp.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter @Setter @NoArgsConstructor
class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private AppUser user;


    public VerificationToken(final String value, final AppUser user) {
        this.value = value;
        this.user = user;
    }
}
