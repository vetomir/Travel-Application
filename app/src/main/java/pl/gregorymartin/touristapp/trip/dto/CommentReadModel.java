package pl.gregorymartin.touristapp.trip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
public
class CommentReadModel {
    private long id;
    private String name;
    private String userAvatar;
    private String comment;
    private ZonedDateTime createdOn;


}
