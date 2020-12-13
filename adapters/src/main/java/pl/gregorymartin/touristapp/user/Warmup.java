package pl.gregorymartin.touristapp.user;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.gregorymartin.touristapp.user.dto.UserWriteModel;

import java.util.ArrayList;
import java.util.List;

@Component("userWarmup")
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final AppUserService appUserService;


    Warmup(final AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();

    }

    private void loadUsers() {
        List<UserWriteModel> users = new ArrayList<>();

        users.add(new UserWriteModel("staszex","Tytus","Kowalski", "abc123","abc123"));
        users.add(new UserWriteModel("romex","Romek","Iksiński", "abc123","abc123"));
        users.add(new UserWriteModel("atomex","Atomek","Oppenheimer", "abc123","abc123"));

        users.forEach(appUserService::createAppUser);

        appUserService.toggleRole(2L);
        appUserService.changePhoto(1 , "https://fwcdn.pl/fph/23/00/32300/11297.2.jpg");
        appUserService.changePhoto(2 , "https://images.pexels.com/photos/614810/pexels-photo-614810.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        appUserService.changePhoto(3 , "https://i.dailymail.co.uk/i/pix/scaled/2012/11/24/article-2237826-1630D732000005DC-376_308x185.jpg");
    }


}
