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

@Service
class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final OfferRepository offerRepository;
    private final AppUserRepository appUserRepository;
    private final Logger  logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    CommentServiceImpl(final CommentRepository commentRepository, final OfferRepository offerRepository, final AppUserRepository appUserRepository) {
        this.commentRepository = commentRepository;
        this.offerRepository = offerRepository;
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
    public List<Comment> getCommentByUser(long id){
        List<Comment> comment = commentRepository.findCommentsByUser(id);
        if (comment.isEmpty()){
            throw new IllegalArgumentException("Comments of this User are not presents");
        }

        return comment;
    }
    public List<Comment> getCommentByOffer(long id){
        List<Comment> comment = commentRepository.findCommentsByOffer(id);
        if (comment.isEmpty()){
            throw new IllegalArgumentException("Comments of this Offer are not presents");
        }

        return comment;
    }
    public CommentReadModel getCommentByUserAndOffer(long userId, long offerId){
        Optional<Comment> comment = commentRepository.findCommentByUserAndOffer(userId, offerId);
        if (comment.isEmpty()){
            throw new IllegalArgumentException("Comment is not presents");
        }

        return CommentFactory.toDto(comment.get());
    }

    @Transactional
    public CommentReadModel addComment(CommentWriteModel commentWriteModel){
        Comment comment = CommentFactory.toEntity(commentWriteModel);
        Optional<AppUser> appUser = appUserRepository.findById(commentWriteModel.getUserId());
        Optional<Offer> offer = offerRepository.findById(commentWriteModel.getOfferId());
        if(offer.isEmpty()){
            throw new IllegalArgumentException("Offer is not present!");
        }
        if (appUser.isEmpty()){
            throw new IllegalArgumentException("User is not present!");
        }
        comment.setAppUser(appUser.get());
        comment.setOffer(offer.get());

        logger.info("Add review of {} {}", commentWriteModel.getUserId() , appUser.get().getId());

        return CommentFactory.toDto(commentRepository.save(comment));
    }
}

