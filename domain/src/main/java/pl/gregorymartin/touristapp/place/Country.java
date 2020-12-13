package pl.gregorymartin.touristapp.place;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "countries")
class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String photoUrl;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id",  insertable = false)
    private List<City> cities;

    Country() {
    }

    Country(final String name) {
        this.name = name;
    }
}
