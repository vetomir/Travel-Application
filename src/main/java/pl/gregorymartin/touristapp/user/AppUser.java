package pl.gregorymartin.touristapp.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.gregorymartin.touristapp.trip.Booking;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "app_users")
public
class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String name;
    private String surname;
    private String photoUrl;

    private String password;

    private boolean isEnabled;

    private Role role;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id",  insertable = false)
    Set<Booking> bookings = new HashSet<>();

    public AppUser() {
        role = Role.ROLE_USER;
        photoUrl = "https://cdn.onlinewebfonts.com/svg/img_569204.png";
    }

    public AppUser(final long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(role.getUserRole()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void toggleEnable() {
        isEnabled = !isEnabled;
    }
}
