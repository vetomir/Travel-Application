package pl.gregorymartin.touristapp.trip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public
class CommentUserPanel {
    private long offerId;
    private String comment;
    private String createdOn;
}
