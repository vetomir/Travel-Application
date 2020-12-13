package pl.gregorymartin.touristapp.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public
class UserReadModel {
    private long id;

    private String username;
    private String role;

    private String name;
    private String surname;
    private String photoUrl;
}
