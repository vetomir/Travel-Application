package pl.gregorymartin.touristapp.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.touristapp.trip.dto.BookingReadModel;

import java.util.List;

@Builder
@Setter
@Getter
class UserReadModel {
    private long id;

    private String username;
    private String role;

    private String name;
    private String surname;

    private List<BookingReadModel> bookings;
}
