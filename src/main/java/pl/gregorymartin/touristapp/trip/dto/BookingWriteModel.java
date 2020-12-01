package pl.gregorymartin.touristapp.trip.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public
class BookingWriteModel {
    private long offerId;

    private long userId;
    private int size;

    public BookingWriteModel(final long offerId, final long userId, final int size) {
        this.offerId = offerId;
        this.userId = userId;
        this.size = size;
    }
}
