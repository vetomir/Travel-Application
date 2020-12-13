package pl.gregorymartin.touristapp.trip;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.gregorymartin.touristapp.trip.dto.CommentReadModel;

import java.util.List;

@RestController
@RequestMapping("/api/users/comments")
class CommentRestController {
    private final CommentService commentService;

    CommentRestController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/list")
    ResponseEntity<List<CommentReadModel>> readAllComments(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/){
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        return ResponseEntity.ok(commentService.getAllComments(pageNumber, sortDirection, sortByVariable, 25));
    }



}
