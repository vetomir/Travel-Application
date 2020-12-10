package pl.gregorymartin.touristapp.trip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public
class CommentWriteModel {
    private long userId;
    private long offerId;
    private String comment;

    public CommentWriteModel() {
    }

    public CommentWriteModel(final long userId, final long offerId, final String comment) {
        this.userId = userId;
        this.offerId = offerId;
        this.comment = comment;
    }
}
