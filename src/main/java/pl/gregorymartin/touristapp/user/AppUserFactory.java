package pl.gregorymartin.touristapp.user;

import pl.gregorymartin.touristapp.user.dto.UserReadModel;
import pl.gregorymartin.touristapp.user.dto.UserWriteModel;

import java.util.List;
import java.util.stream.Collectors;

public class AppUserFactory {
    //create
    public static List<AppUser> toEntity(List<UserWriteModel> userDaos) {
        return userDaos.stream()
                .map(AppUserFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static AppUser toEntity(UserWriteModel userDao) {
        if(userDao.getPassword2().equals(userDao.getPassword())){
            AppUser user = new AppUser();
            user.setName(userDao.getName());
            user.setUsername(userDao.getUsername());
            user.setName(userDao.getName());
            user.setSurname(userDao.getSurname());
            user.setPassword(userDao.getPassword());

            return user;
        }
        else throw new IllegalArgumentException("Passwords, ale not the same!");

    }

    //read
    public static List<UserReadModel> toDto(List<AppUser> userList){
        return userList.stream()
                .map(AppUserFactory::toDto)
                .collect(Collectors.toList());
    }
    public static UserReadModel toDto(AppUser user) {

        return UserReadModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .photoUrl(user.getPhotoUrl())
                .role(user.getRole().toString())
                .build();
    }
}
