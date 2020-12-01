package pl.gregorymartin.touristapp.trip;

import pl.gregorymartin.touristapp.trip.dto.CommentReadModel;
import pl.gregorymartin.touristapp.trip.dto.CommentWriteModel;

import java.util.List;
import java.util.stream.Collectors;

public class CommentFactory {
    //create
    public static List<Comment> toEntity(List<CommentWriteModel> commentDao) {
        return commentDao.stream()
                .map(CommentFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static Comment toEntity(CommentWriteModel commentDao) {
        Comment comment = new Comment();
        comment.setComment(commentDao.getComment());
        return comment;
    }

    //read
    public static List<CommentReadModel> toDto(List<Comment> comment){
        return comment.stream()
                .map(CommentFactory::toDto)
                .collect(Collectors.toList());
    }
    public static CommentReadModel toDto(Comment comment) {

        return CommentReadModel.builder()
                .id(comment.getId())
                .name(comment.getAppUser().getName())
                .comment(comment.getComment())
                .build();
    }
}
