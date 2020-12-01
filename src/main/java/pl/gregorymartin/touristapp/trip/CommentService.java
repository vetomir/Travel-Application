package pl.gregorymartin.touristapp.trip;

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

@Service
public
class CommentService {
    private final SqlCommentRepository commentRepository;
    private final SqlBookingRepository bookingRepository;
    private final AppUserRepository appUserRepository;

    CommentService(final SqlCommentRepository commentRepository, final SqlBookingRepository bookingRepository, final AppUserRepository appUserRepository) {
        this.commentRepository = commentRepository;
        this.bookingRepository = bookingRepository;
        this.appUserRepository = appUserRepository;
    }

    public List<CommentReadModel> getAllComments(int page, Sort.Direction sort, String sortBy, int items){
        List<Comment> comments = commentRepository.findAll(
                PageRequest.of(page, items,
                        Sort.by(sort, sortBy)
                )).getContent();
        return CommentFactory.toDto(comments);
    }

    public CommentReadModel getCommentById(long id){
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()){
            throw new IllegalArgumentException("Offer is not presents");
        }

        return CommentFactory.toDto(comment.get());
    }
    @Transactional
    public CommentReadModel addComment(CommentWriteModel commentWriteModel){
        Comment comment = CommentFactory.toEntity(commentWriteModel);
        Optional<AppUser> appUser = appUserRepository.findById(commentWriteModel.getUserId());
        Optional<Booking> booking = bookingRepository.findById(commentWriteModel.getBookingId());
        if(booking.isEmpty()){
            throw new IllegalArgumentException("Booking is not present!");
        }
        if (appUser.isEmpty()){
            throw new IllegalArgumentException("User is not present!");
        }
        comment.setAppUser(appUser.get());
        comment.setBooking(booking.get());

        return CommentFactory.toDto(commentRepository.save(comment));
    }
}

