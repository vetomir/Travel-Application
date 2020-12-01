package pl.gregorymartin.touristapp.user.dto;

import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.touristapp.trip.dto.BookingReadModel;

import java.util.List;

@Setter
@Getter
public
class UserWriteModel {

    private String username;

    private String name;
    private String surname;

    private String password;
    private String password2;

    public UserWriteModel(final String username, final String name, final String surname, final String password, final String password2) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.password2 = password2;
    }
}
