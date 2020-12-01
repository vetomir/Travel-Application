package pl.gregorymartin.touristapp.trip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public
class CommentWriteModel {
    private long userId;
    private long bookingId;
    private String comment;

    public CommentWriteModel(final long userId, final long bookingId, final String comment) {
        this.userId = userId;
        this.bookingId = bookingId;
        this.comment = comment;
    }
}
