package pl.gregorymartin.touristapp.user;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gregorymartin.touristapp.user.dto.UserReadModel;
import pl.gregorymartin.touristapp.user.dto.UserWriteModel;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public
class AppUserServiceImpl implements AppUserService{
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSenderService;
    private final VerificationTokenRepository verificationTokenRepository;

    AppUserServiceImpl(final AppUserRepository userRepository, final PasswordEncoder passwordEncoder, final MailSenderService mailSenderService, final VerificationTokenRepository verificationTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public List<UserReadModel> getAllAppUsers(int page, Sort.Direction sort, String sortBy, int items){
        List<AppUser> users = userRepository.findAll(
                PageRequest.of(page, items,
                        Sort.by(sort, sortBy)
                )).getContent();
        return AppUserFactory.toDto(users);
    }

    public UserReadModel getAppUserById(long id){
        Optional<AppUser> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new IllegalArgumentException("Offer is not presents");
        }

        return AppUserFactory.toDto(user.get());
    }

    public UserReadModel registerAppUser(UserWriteModel userWriteModel, HttpServletRequest request){
        AppUser appUser = createAppUser(userWriteModel);
        /*createToken(appUser,request);*/
        return AppUserFactory.toDto(appUser);
    }

    public AppUser createAppUser(UserWriteModel userWriteModel){
        Optional<AppUser> byUsername = Optional.ofNullable(userRepository.findAllByUsername(userWriteModel.getUsername()));
        if(byUsername.isPresent()){
            throw new IllegalArgumentException("User with these username (email address) is already exist");
        }
        else{
            AppUser result = AppUserFactory.toEntity(userWriteModel);
            result.setPassword(passwordEncoder.encode(userWriteModel.getPassword()));

            return userRepository.save(result);
        }
    }

    public AppUser createToken(AppUser appUser, HttpServletRequest request){
        //
        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken(token,appUser);
        verificationTokenRepository.save(verificationToken);

        String url = "http://" +
                request.getServerName() +
                ":" + request.getServerPort() +
                request.getContextPath() +
                "/verify-token?token=" + token;

        try {
            mailSenderService.sendMail(
                    appUser.getUsername(), "Verification Token", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return appUser;
    }

    public void verifyToken(final String token) {
        AppUser appUser = verificationTokenRepository.findByValue(token).getUser();
        appUser.toggleEnable();
        userRepository.save(appUser);
    }


    @Transactional
    public UserReadModel toggleRole(long id){
        Optional<AppUser> appUser = userRepository.findById(id);

        if(appUser.isEmpty()){
            throw new IllegalArgumentException("User is not present");
        }

        if(appUser.get().getRole() == Role.ROLE_USER){
            appUser.get().setRole(Role.ROLE_ADMIN);
        }
        else if(appUser.get().getRole() == Role.ROLE_ADMIN){
            appUser.get().setRole(Role.ROLE_USER);
        }

        return AppUserFactory.toDto(
                userRepository.save(appUser.get())
        );
    }
    @Transactional
    public UserReadModel modifyAppUser(long id, UserWriteModel userWriteModel){
        Optional<AppUser> appUser = userRepository.findById(id);
        if(appUser.isEmpty()){
            throw new IllegalArgumentException("User is not present");
        }
        if(!appUser.get().getUsername().equals(userWriteModel.getUsername())){
            Optional<AppUser> allByUsername =  Optional.ofNullable(userRepository.findAllByUsername(userWriteModel.getUsername()));
            if(allByUsername.isPresent()){
                throw new IllegalArgumentException("User with this email is already exists");
            }

        }
        appUser.get().setUsername(userWriteModel.getUsername());
        appUser.get().setName(userWriteModel.getName());
        appUser.get().setSurname(userWriteModel.getSurname());
        appUser.get().setPassword(userWriteModel.getPassword());

        return AppUserFactory.toDto(
                userRepository.save(appUser.get())
        );
    }
    @Transactional
    public UserReadModel changePhoto(long id, String photoUrl){
        Optional<AppUser> appUser = userRepository.findById(id);
        if(appUser.isEmpty()){
            throw new IllegalArgumentException("User is not present");
        }

        appUser.get().setPhotoUrl(photoUrl);

        return AppUserFactory.toDto(
                userRepository.save(appUser.get())
        );
    }
    public void deleteUser(long id){

        userRepository.deleteById(id);

    }


}
