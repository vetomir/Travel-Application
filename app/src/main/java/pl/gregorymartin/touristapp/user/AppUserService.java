package pl.gregorymartin.touristapp.user;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.gregorymartin.touristapp.user.dto.UserReadModel;
import pl.gregorymartin.touristapp.user.dto.UserWriteModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public
interface AppUserService {

    List<UserReadModel> getAllAppUsers(int page, Sort.Direction sort, String sortBy, int items);

    UserReadModel getAppUserById(long id);

    UserReadModel registerAppUser(UserWriteModel userWriteModel, HttpServletRequest request);

    AppUser createAppUser(UserWriteModel userWriteModel);

    AppUser createToken(AppUser appUser, HttpServletRequest request);

    void verifyToken(final String token);

    UserReadModel toggleRole(long id);

    UserReadModel modifyAppUser(long id, UserWriteModel userWriteModel);

    UserReadModel changePhoto(long id, String photoUrl);

    public void deleteUser(long id);
}
