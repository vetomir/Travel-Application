package pl.gregorymartin.touristapp.trip;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.touristapp.user.AppUser;
import pl.gregorymartin.touristapp.user.Audit;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comment extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    @Column( length = 1000 )
    private String comment;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id", updatable = false)
    private AppUser appUser;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "offer_id", updatable = false)
    private Offer offer;
}
