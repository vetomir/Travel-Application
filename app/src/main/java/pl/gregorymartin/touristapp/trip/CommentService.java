package pl.gregorymartin.touristapp.trip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gregorymartin.touristapp.trip.dto.CommentReadModel;
import pl.gregorymartin.touristapp.trip.dto.CommentWriteModel;
import pl.gregorymartin.touristapp.user.AppUser;
import pl.gregorymartin.touristapp.user.AppUserRepository;

import java.util.List;
import java.util.Optional;


public
interface CommentService {
    public List<CommentReadModel> getAllComments(int page, Sort.Direction sort, String sortBy, int items);
    public CommentReadModel getCommentById(long id);
    public List<Comment> getCommentByUser(long id);
    public List<Comment> getCommentByOffer(long id);
    public CommentReadModel getCommentByUserAndOffer(long userId, long offerId);
    public CommentReadModel addComment(CommentWriteModel commentWriteModel);
}

