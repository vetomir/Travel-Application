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
        putUsers();

    }

    private void putUsers() {
        List<UserWriteModel> users = new ArrayList<>();

        users.add(new UserWriteModel("stach","Stanisław","Kowalski", "abc123","abc123"));
        users.add(new UserWriteModel("stachsaa","Stanisław","Kowalski", "abc123","abc123"));

        users.forEach(appUserService::registerUser);

        appUserService.toggleRole(2L);
    }

}
