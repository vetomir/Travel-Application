package pl.gregorymartin.touristapp.trip.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public
class BookingWriteModel {
    @NotNull
    private long offerId;
    @NotNull
    private long userId;
    @Min(1)
    private int size = 1;

    public BookingWriteModel() {
    }

    public BookingWriteModel(final long offerId, final long userId, final int size) {
        this.offerId = offerId;
        this.userId = userId;
        this.size = size;
    }
}
