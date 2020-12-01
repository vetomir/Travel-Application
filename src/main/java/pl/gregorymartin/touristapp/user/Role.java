package pl.gregorymartin.touristapp.user;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String userRole;

    Role(String userRole) {
        this.userRole = userRole;
    }
}
